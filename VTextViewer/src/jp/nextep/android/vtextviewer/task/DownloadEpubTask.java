package jp.nextep.android.vtextviewer.task;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class DownloadEpubTask extends AsyncTask<Void, Integer, Boolean> {

	private Activity mActivity;
	private String mUrl;
	private String mOutPath;
	private String mPackageName;
	private ProgressDialog mProgressDialog;

	public DownloadEpubTask(Activity activity, String url, String outPath, String packageName) {
		this.mActivity = activity;
		this.mUrl = url;
		this.mOutPath = outPath;
		this.mPackageName = packageName;
	}

	@Override
	protected void onPreExecute() {
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage(mActivity.getString(R.string.msg_progress_download));
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.show();
		// super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(Void... arg0) {

		boolean result = false;
		try {
			if (FileDataUtil.downloadFile(mUrl, mOutPath)) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// super.onPostExecute(result);
		mProgressDialog.dismiss();

		if (result) {
			System.out.println("Download Success !!");

			LaodingEpubTask task = new LaodingEpubTask(mActivity, mOutPath, mPackageName);
			task.execute();

		} else {
			System.out.println("Download Failure !!");
		}
	}

}
