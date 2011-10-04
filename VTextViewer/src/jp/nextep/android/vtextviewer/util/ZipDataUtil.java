package jp.nextep.android.vtextviewer.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class ZipDataUtil {

	private ZipDataUtil() {
	}

	public static String uncompress(String readPath, boolean mkdir) throws Exception {

		// InputStreamの生成
		ZipArchiveInputStream arcInputStream = new ZipArchiveInputStream(new FileInputStream(readPath));

		// バッファリングしないと処理時間が数十倍になるので、
		// BufferedInputStreamでラップ
		BufferedInputStream bis = new BufferedInputStream(arcInputStream);

		// ディレクトリを生成する場合は、ファイル名から拡張子を除いた名前の
		// ディレクトリを生成しておく
		File baseDir = new File(readPath).getParentFile();
		String basePath = baseDir.getPath() + "/";
		// String basePath = "./";
		if (mkdir) {
			// String baseName = FilenameUtils.getBaseName(readPath);
			String[] schema = readPath.split("/");
			String baseName = schema[schema.length - 1].replaceAll(".zip", "");
			FileUtils.forceMkdir(new File(basePath + baseName));
			basePath = basePath + baseName + "/";
		}

		// Entryを1つずつ取得し、ファイル出力していく
		ArchiveEntry entry;
		while ((entry = arcInputStream.getNextEntry()) != null) {
			// ディレクトリの場合は、ディレクトリ生成
			if (entry.isDirectory()) {
				FileUtils.forceMkdir(new File(basePath + entry.getName()));
			}
			// ファイルの場合は、ファイル出力
			else {
				String writePath = basePath + entry.getName();

				// 指定パスのディレクトリが存在しない場合は生成
				String dirName = FilenameUtils.getPath(writePath);
				FileUtils.forceMkdir(new File(dirName));

				// OutputStreamの生成
				BufferedOutputStream bos = new BufferedOutputStream(FileUtils.openOutputStream(new File(writePath)));

				// 出力
				int i = 0;
				while ((i = bis.read()) != -1) {
					bos.write(i);
				}
				// OutputStreamを閉じる
				IOUtils.closeQuietly(bos);
			}
		}
		// Streamを閉じる
		IOUtils.closeQuietly(bis);

		return basePath;
	}
}
