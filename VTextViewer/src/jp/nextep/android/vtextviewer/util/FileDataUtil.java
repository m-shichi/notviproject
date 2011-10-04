package jp.nextep.android.vtextviewer.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileDataUtil {

	public static final String ROOT_PATH = "/data/data/";
	public static final String EXT_ZIP = ".zip";

	private FileDataUtil() {

	}

	/**
	 *
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static Bitmap loadBitmap(InputStream is) throws IOException {
		Bitmap result = null;

		try {
			result = BitmapFactory.decodeStream(is);
		} finally {
			// if (is != null) {
			// try {
			// is.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
		}
		return result;
	}

	/**
	 * 画像を指定された形式で外部ストレージにファイルに保存。
	 *
	 * @param context
	 *            アプリケーションコンテキスト
	 * @param fileName
	 *            ファイル名
	 * @return ビットマップオブジェクト (存在しなければnull)
	 * @throws IOException
	 *             ファイルが存在しない(FileNotFoundException)、読み取りエラー
	 */
	public static Bitmap loadBitmap(String fileName) throws IOException {
		Bitmap result = null;

		FileInputStream fileInput = null;
		BufferedInputStream bufInput = null;
		try {
			fileInput = new FileInputStream(fileName);
			bufInput = new BufferedInputStream(fileInput);
			result = BitmapFactory.decodeStream(bufInput);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
				}
			}
			if (bufInput != null) {
				try {
					bufInput.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	/**
	 * アプリケーション保存ディレクトリに存在する画像ファイルを取得する。
	 *
	 * @return 画像ファイルリスト (存在しない場合は空のリスト)
	 */
	public static List<File> getApplicationBitmapFileList(Context context) throws IOException {
		List<File> result = new ArrayList<File>();

		File sdcardRoot = getSdCardRootDirectory();
		File loadDir = new File(sdcardRoot, context.getPackageName());
		if (!loadDir.exists()) {
			throw new IOException(String.format("アプリケーションの保存先が存在しません(%s)", loadDir));
		}
		if (!loadDir.canRead()) {
			throw new IOException(String.format("ファイルを読み出しできません(%s)", loadDir));
		}

		// アプリケーションディレクトリの画像ファイルを取得
		File[] files = loadDir.listFiles(new FilenameFilter() {

			// 画像ファイルのみを対象とする(*.PNG(*.png) or *.JPEG(*.jpeg))
			public boolean accept(File loadDir, String fileName) {
				// CompressFormat
				// JPEG = new CompressFormat("JPEG", 0);
				// PNG = new CompressFormat("PNG", 1);
				return fileName.matches(".+\\.(PNG|png|JPEG|jpeg)$");
			}
		});
		// メイン側で扱いやすいようにリストに変換しています
		if (files != null && files.length > 0) {
			for (File item : files) {
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * 外部メディアディレクトリを取得する。
	 *
	 * @return 外部メディアディレクトリ (/sdcard/)
	 * @throws IOException
	 *             メディアエラー
	 */
	public static File getSdCardRootDirectory() throws IOException {
		// SDカードがマウントされているか
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			throw new IOException(String.format("メディアがセットされていません(%s)", Environment.getExternalStorageState()));
		}
		File root = Environment.getExternalStorageDirectory();
		if (root == null) {
			throw new IOException("メディアが利用できません");
		}
		return root;
	}

	/**
	 *
	 * @param packName
	 * @return
	 */
	public static String getTempFilePath(String packName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ROOT_PATH);
		sb.append(packName);
		sb.append("/temp/");

		File dir = new File(sb.toString());
		if (!dir.exists()) {
			dir.mkdirs();
		}

		Calendar cal = Calendar.getInstance();
		String uniqueStr = String.valueOf(cal.getTimeInMillis());

		sb.append(uniqueStr);
		sb.append(EXT_ZIP);

		return sb.toString();
	}

	/**
	 *
	 * @param packName
	 * @param uuid
	 * @return
	 */
	public static String getEpubDirPath(String packName, String uuid) {
		StringBuilder sb = new StringBuilder();
		sb.append(ROOT_PATH);
		sb.append(packName);
		sb.append("/epub/");
		sb.append(uuid);
		return sb.toString();
	}

	/**
	 *
	 * @param packName
	 * @param mediaId
	 * @return
	 */
	public static String getContentDirPath(String packName, String mediaId) {
		StringBuilder sb = new StringBuilder();
		sb.append(ROOT_PATH);
		sb.append(packName);
		sb.append("/epub/");
		sb.append(mediaId);
		sb.append("/OEBPS/");
		return sb.toString();
	}

	/**
	 *
	 * @param dirPath
	 * @return
	 */
	public static String getOebpsDirPath(String dirPath) {
		return dirPath + "OEBPS/";
	}

	/**
	 *
	 * @param dirPath
	 * @return
	 */
	public static String getNcxPath(String dirPath) {
		File dir = new File(getOebpsDirPath(dirPath));
		File[] ncx = dir.listFiles(new NcxFileFilter());
		return ncx[0].getAbsolutePath();
	}

	/**
	 *
	 * @param uri
	 * @param outPath
	 * @return
	 * @throws Exception
	 */
	public static boolean downloadFile(String uri, String outPath) throws Exception {

		boolean result = false;

		int responseCode = 0;
		int BUFFER_SIZE = 10240;

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		httpClient.getParams().setParameter("http.connection.timeout", new Integer(15000));

		URI url = null;
		try {
			url = new URI(uri);
		} catch (URISyntaxException e) {
			throw new Exception("URLのエラーが発生しました。", e);
		}

		try {
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(url);

			httpResponse = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {
			throw new Exception("プロトコル通信でエラーが発生しました。", e);
		} catch (IOException e) {
			throw new Exception("通信中にエラーが発生しました。", e);
		}

		try {
			responseCode = httpResponse.getStatusLine().getStatusCode();

			if (responseCode == HttpStatus.SC_OK) {
				File file = new File(outPath);

				in = new BufferedInputStream(httpResponse.getEntity().getContent(), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(file, false), BUFFER_SIZE);

				byte buf[] = new byte[BUFFER_SIZE];
				int size = -1;
				while ((size = in.read(buf)) != -1) {
					out.write(buf, 0, size);
				}
				out.flush();

				result = true;

			} else if (responseCode == HttpStatus.SC_NOT_FOUND) {
				/* ダウンロードファイルが見つからない */
			} else if (responseCode == HttpStatus.SC_REQUEST_TIMEOUT) {
				/* コネクションタイムアウト */
			}
		} catch (IllegalStateException e) {
			throw new Exception("受信データの状態が不正です。", e);
		} catch (IOException e) {
			throw new Exception("受信データの解析中にIOエラーが発生しました。", e);
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
}
