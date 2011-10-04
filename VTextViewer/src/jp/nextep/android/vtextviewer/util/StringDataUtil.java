package jp.nextep.android.vtextviewer.util;

import jp.nextep.android.vtextviewer.component.CharSetting;

public class StringDataUtil {

	/**
	 *
	 * @param text
	 * @param lineNum
	 * @return
	 */
	public static String addSpace(String text, int lineNum) {

		int amari = text.length() % lineNum;

		StringBuilder sb = new StringBuilder(text);

		for (int i = 0; i < lineNum - amari; i++) {
			sb.append("　");
		}
		return sb.toString();
	}

	/**
	 *
	 * @param text
	 * @param lineNum
	 * @return
	 */
	public static String addSpaceAndLine(String text, int lineNum) {

		int amari = text.length() % lineNum;

		StringBuilder sb = new StringBuilder(text);

		for (int i = 0; i < lineNum - amari; i++) {
			sb.append("　");
		}
		sb.append(addLine(lineNum));

		return sb.toString();
	}

	/**
	 *
	 * @param sb
	 * @param lineNum
	 * @return
	 */
	public static String addLine(int lineNum) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lineNum; i++) {
			sb.append("　");
		}
		return sb.toString();
	}

	/**
	 *
	 * @param text
	 * @param lineNum
	 * @return
	 */
	public static String editHeder(String text, int lineNum) {

		if (text == null) {
			return "";
		}

		text = trim(text);

		StringBuilder sb = new StringBuilder();

		char[] ca = text.toCharArray();

		int charCnt = 0;
		int roopCnt = 1;
		String s = "";
		try {
			while (true) {
				s = String.valueOf(text.charAt(charCnt));
				if (charCnt + 1 == ca.length) {
					sb.append(s);
					break;
				}
				if (roopCnt % lineNum == 0) {
					if (CharSetting.isEndPunctuationMark(s) || CharSetting.isPunctuationMark(String.valueOf(text.charAt(charCnt + 1)))) {
						sb.append("　");
					} else {
						sb.append(s);
						charCnt++;
					}
					roopCnt = 1;
				} else {
					sb.append(s);
					charCnt++;
					roopCnt++;
				}
			}
		} catch (Exception ex) {
			System.err.println("[" + s + "]:" + String.valueOf(charCnt) + ":" + sb.toString());
			ex.printStackTrace();
		}
		return sb.toString();
	}

	// 左トリム
	public static String trimL(String s) {
		return _trim(s, 1);
	}

	// 右トリム
	public static String trimR(String s) {
		return _trim(s, 2);
	}

	// 両方トリム
	public static String trim(String s) {
		return _trim(s, 3);
	}

	// プライベートメソッド　トリム
	private static String _trim(String s, int mode) {

		if (s == null)
			return s;
		int len = s.length();
		int st = 0, ed = len;
		if (mode == 1 || mode == 3) {
			for (int i = 0; i < len; i++) {
				char ch = s.charAt(i);
				if (ch != ' ' && /* 半角 */
				ch != '　') { /* 全角 */
					st = i;
					break;
				}
			}
		}

		if (mode == 2 || mode == 3) {
			for (int i = len - 1; i >= 0; i--) {
				char ch = s.charAt(i);
				if (ch != ' ' && /* 半角 */
				ch != '　') { /* 全角 */
					ed = i + 1;
					break;
				}
			}
		}

		return s.substring(st, ed);
	}

	public static boolean checkSpace(String s) {
		boolean bValue = false;
		if (s.replaceAll(" ", "").replaceAll("　", "").equals("")) {
			bValue = true;
		}
		return bValue;
	}
}
