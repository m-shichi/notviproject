package jp.nextep.android.vtextviewer.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.bean.MediaItemBean;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.util.DomDataUtil;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import jp.nextep.android.vtextviewer.util.ZipDataUtil;
import jp.nextep.epub.xml.ncx.Head;
import jp.nextep.epub.xml.ncx.Meta;
import jp.nextep.epub.xml.ncx.NavPoint;
import jp.nextep.epub.xml.ncx.Ncx;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;

public class LaodingEpubTask extends AsyncTask<Void, Integer, Boolean> {

	private static final String TAG = "LaodingEpubTask";

	private Activity mActivity;
	private String mTempPath;
	private String mTempDirPath;
	private String mPackageName;
	private String mUuid;
	private String mEpubDirPath;
	// private File mEpubDir;
	private ProgressDialog mProgressDialog;

	public LaodingEpubTask(Activity activity, String tempPath, String packageName) {
		this.mActivity = activity;
		this.mTempPath = tempPath;
		this.mPackageName = packageName;
	}

	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(mActivity.getString(R.string.msg_progress_load));
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {

		boolean result = false;

		try {
			mTempDirPath = ZipDataUtil.uncompress(mTempPath, true);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		String ncxPath = FileDataUtil.getNcxPath(mTempDirPath);

		if (result) {
			// DBに登録
			setEpubData(ncxPath);
		}

		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		System.out.println("[hogehoge]" + String.valueOf(values));
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// super.onPostExecute(result);
		mProgressDialog.dismiss();

		if (result) {
			File tempDir = new File(mTempDirPath);
			File epubDir = new File(mEpubDirPath);
			if (!epubDir.exists()) {
				epubDir.mkdirs();
			}
			try {
				// コピー
				FileUtils.copyDirectory(tempDir, epubDir, true);
			} catch (IOException ex) {
				Log.e(TAG, String.format("directory copy failed !! [%s]", epubDir.getName()), ex.getCause());
			} finally {
				try {
					FileUtils.cleanDirectory(tempDir.getParentFile());
				} catch (IOException ex) {
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean setEpubData(String ncxPath) {

		boolean result = false;
		FileInputStream fis = null;
		Ncx ncx = null;
		try {
			fis = new FileInputStream(ncxPath);
			ncx = new Ncx(fis);
		} catch (FileNotFoundException ex) {
			Log.e(TAG, String.format("file not found !! [%s]", ncxPath), ex.getCause());
		} catch (IOException ex) {
			Log.e(TAG, String.format("IO error !! [%s]", ncxPath), ex.getCause());
		} catch (SAXException ex) {
			Log.e(TAG, String.format("xml read error !! [%s]", ncxPath), ex.getCause());
		} catch (ParserConfigurationException ex) {
			Log.e(TAG, String.format("xml parse error !! [%s]", ncxPath), ex.getCause());
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}

		// DBに登録
		Head head = ncx.getHead();
		Meta[] metaArray = head.getMeta();
		for (int i = 0; i < metaArray.length; i++) {
			Meta meta = metaArray[i];
			if (meta.getNameAsString().equals("dtb:uid")) {
				mUuid = meta.getContentAsString();
				break;
			}
		}

		mEpubDirPath = FileDataUtil.getEpubDirPath(mPackageName, mUuid);

		String docTitle = ncx.getDocTitle_Text();

		DBHelper dbHelper = DBHelper.getInstance(mActivity);

		List<MediaItemBean> list = dbHelper.selectMediaById(mUuid);
		if (list.size() > 0) {
			// 削除処理
			// レコード削除
			dbHelper.deleteMediaById(mUuid);
			dbHelper.deleteContentsById(mUuid);

			File epubDir = null;
			try {
				// ファイル削除
				epubDir = new File(mEpubDirPath);
				FileUtils.deleteDirectory(epubDir);
			} catch (IOException ex) {
				Log.e(TAG, String.format("file delete failed !! [%s]", epubDir.getName()), ex.getCause());
			}
		}

		try {
			dbHelper.insertMedia(mUuid, docTitle);
		} catch (SQLException ex) {
			Log.e(TAG, String.format("media insert failed !! [%s]", mUuid), ex.getCause());
		}

		int cnt = 1;
		NavPoint[] navPointArray = ncx.getNavMap().getNavPoint();
		for (int j = 0; j < navPointArray.length; j++) {
			NavPoint navPoint = navPointArray[j];
			String text = navPoint.getNavLabel_Text();
			if (text.equals("目次")) {
				// skip
				continue;
			}
			String[] contentArray = text.split("\\Q" + Ncx.TITLE_SPLIT + "\\E");
			String page = "";
			String contents = "";
			if (contentArray.length == 1) {
				page = "-";
				contents = contentArray[0];
			} else if (contentArray.length == 2) {
				page = contentArray[0];
				contents = contentArray[1];
			}

			try {
				// イメージの有無
				String path = navPoint.getContent_Src();
				String xhtmlPath = FileDataUtil.getOebpsDirPath(mTempDirPath) + path;

				DomDataUtil dom = new DomDataUtil(new FileInputStream(xhtmlPath));
				dom.init();

				String[] images = dom.getImagePath();

				int image_flag = images.length > 0 ? 1 : 0;
				String imagePath = images.length > 0 ? images[0] : "";

				dbHelper.insertContents(mUuid, cnt, page, contents, path, image_flag, imagePath);

				cnt++;

				result = true;

			} catch (FileNotFoundException ex) {
				Log.e(TAG, String.format("xhtml read failed !! [%s][%s]", mUuid, contents), ex.getCause());
				result = false;
				return result;
			} catch (SQLException ex) {
				Log.e(TAG, String.format("contents insert failed !! [%s][%s]", mUuid, contents), ex.getCause());
				result = false;
				return result;
			}
		}
		return result;
	}
}
