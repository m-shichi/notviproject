package jp.nextep.android.vtextviewer.util;

import java.io.File;
import java.io.FileFilter;

public class NcxFileFilter implements FileFilter {

	private static final String FILTER_KEYWORD = ".ncx";

	@Override
	public boolean accept(File file) {

		if (file.isDirectory()) {
			return false;
		}
		return (file.getName().endsWith(FILTER_KEYWORD));
	}
}
