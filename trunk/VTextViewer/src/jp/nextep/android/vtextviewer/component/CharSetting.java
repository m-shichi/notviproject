package jp.nextep.android.vtextviewer.component;

public class CharSetting {
	/**
	 * 文字
	 */
	public final String charcter;

	/**
	 * 回転角度
	 */
	public final float angle;

	/**
	 * xの差分<br />
	 * Paint#getFontSpacing() * xが足される<br />
	 * -0.5fが設定された場合、1/2文字分左にずれる
	 */
	public final float x;

	/**
	 * xの差分<br />
	 * Paint#getFontSpacing() * yが足される<br />
	 * -0.5fが設定された場合、1/2文字分上にずれる
	 */
	public final float y;

	public CharSetting(String charcter, float angle, float x, float y) {
		super();
		this.charcter = charcter;
		this.angle = angle;
		this.x = x;
		this.y = y;
	}

	public static final CharSetting[] settings = {new CharSetting("、", 0.0f, 0.7f, -0.6f), new CharSetting("。", 0.0f, 0.7f, -0.6f), new CharSetting("「", 90.0f, -0.7f, -0.3f),
			new CharSetting("」", 90.0f, -0.8f, 0.0f), new CharSetting("ぁ", 0.0f, 0.1f, -0.1f), new CharSetting("ぃ", 0.0f, 0.1f, -0.1f), new CharSetting("ぅ", 0.0f, 0.1f, -0.1f),
			new CharSetting("ぇ", 0.0f, 0.1f, -0.1f), new CharSetting("ぉ", 0.0f, 0.1f, -0.1f), new CharSetting("っ", 0.0f, 0.1f, -0.1f), new CharSetting("ゃ", 0.0f, 0.1f, -0.1f),
			new CharSetting("ゅ", 0.0f, 0.1f, -0.1f), new CharSetting("ょ", 0.0f, 0.1f, -0.1f), new CharSetting("ァ", 0.0f, 0.1f, -0.1f), new CharSetting("ィ", 0.0f, 0.1f, -0.1f),
			new CharSetting("ゥ", 0.0f, 0.1f, -0.1f), new CharSetting("ェ", 0.0f, 0.1f, -0.1f), new CharSetting("ォ", 0.0f, 0.1f, -0.1f), new CharSetting("ッ", 0.0f, 0.1f, -0.1f),
			new CharSetting("ャ", 0.0f, 0.1f, -0.1f), new CharSetting("ュ", 0.0f, 0.1f, -0.1f), new CharSetting("ョ", 0.0f, 0.1f, -0.1f), new CharSetting("ー", -90.0f, 0.0f, 0.9f),
			new CharSetting("a", 0.0f, 0.0f, -0.1f), new CharSetting("b", 0.0f, 0.0f, -0.1f), new CharSetting("c", 0.0f, 0.0f, -0.1f), new CharSetting("d", 0.0f, 0.0f, -0.1f),
			new CharSetting("e", 0.0f, 0.0f, -0.1f), new CharSetting("f", 0.0f, 0.0f, -0.1f), new CharSetting("g", 0.0f, 0.0f, -0.1f), new CharSetting("h", 0.0f, 0.0f, -0.1f),
			new CharSetting("i", 0.0f, 0.0f, -0.1f), new CharSetting("j", 0.0f, 0.0f, -0.1f), new CharSetting("k", 0.0f, 0.0f, -0.1f), new CharSetting("l", 0.0f, 0.0f, -0.1f),
			new CharSetting("m", 0.0f, 0.0f, -0.1f), new CharSetting("n", 0.0f, 0.0f, -0.1f), new CharSetting("o", 0.0f, 0.0f, -0.1f), new CharSetting("p", 0.0f, 0.0f, -0.1f),
			new CharSetting("q", 0.0f, 0.0f, -0.1f), new CharSetting("r", 0.0f, 0.0f, -0.1f), new CharSetting("s", 0.0f, 0.0f, -0.1f), new CharSetting("t", 0.0f, 0.0f, -0.1f),
			new CharSetting("u", 0.0f, 0.0f, -0.1f), new CharSetting("v", 0.0f, 0.0f, -0.1f), new CharSetting("w", 0.0f, 0.0f, -0.1f), new CharSetting("x", 0.0f, 0.0f, -0.1f),
			new CharSetting("y", 0.0f, 0.0f, -0.1f), new CharSetting("z", 0.0f, 0.0f, -0.1f), new CharSetting("A", 0.0f, 0.0f, -0.1f), new CharSetting("B", 0.0f, 0.0f, -0.1f),
			new CharSetting("C", 0.0f, 0.0f, -0.1f), new CharSetting("D", 0.0f, 0.0f, -0.1f), new CharSetting("E", 0.0f, 0.0f, -0.1f), new CharSetting("F", 0.0f, 0.0f, -0.1f),
			new CharSetting("G", 0.0f, 0.0f, -0.1f), new CharSetting("H", 0.0f, 0.0f, -0.1f), new CharSetting("I", 0.0f, 0.0f, -0.1f), new CharSetting("J", 0.0f, 0.0f, -0.1f),
			new CharSetting("K", 0.0f, 0.0f, -0.1f), new CharSetting("L", 0.0f, 0.0f, -0.1f), new CharSetting("M", 0.0f, 0.0f, -0.1f), new CharSetting("N", 0.0f, 0.0f, -0.1f),
			new CharSetting("O", 0.0f, 0.0f, -0.1f), new CharSetting("P", 0.0f, 0.0f, -0.1f), new CharSetting("Q", 0.0f, 0.0f, -0.1f), new CharSetting("R", 0.0f, 0.0f, -0.1f),
			new CharSetting("S", 0.0f, 0.0f, -0.1f), new CharSetting("T", 0.0f, 0.0f, -0.1f), new CharSetting("U", 0.0f, 0.0f, -0.1f), new CharSetting("V", 0.0f, 0.0f, -0.1f),
			new CharSetting("W", 0.0f, 0.0f, -0.1f), new CharSetting("X", 0.0f, 0.0f, -0.1f), new CharSetting("Y", 0.0f, 0.0f, -0.1f), new CharSetting("Z", 0.0f, 0.0f, -0.1f),
			new CharSetting("ａ", 0.0f, 0.0f, -0.1f), new CharSetting("ｂ", 0.0f, 0.0f, -0.1f), new CharSetting("ｃ", 0.0f, 0.0f, -0.1f), new CharSetting("ｄ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｅ", 0.0f, 0.0f, -0.1f), new CharSetting("ｆ", 0.0f, 0.0f, -0.1f), new CharSetting("ｇ", 0.0f, 0.0f, -0.1f), new CharSetting("ｈ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｉ", 0.0f, 0.0f, -0.1f), new CharSetting("ｊ", 0.0f, 0.0f, -0.1f), new CharSetting("ｋ", 0.0f, 0.0f, -0.1f), new CharSetting("ｌ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｍ", 0.0f, 0.0f, -0.1f), new CharSetting("ｎ", 0.0f, 0.0f, -0.1f), new CharSetting("ｏ", 0.0f, 0.0f, -0.1f), new CharSetting("ｐ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｑ", 0.0f, 0.0f, -0.1f), new CharSetting("ｒ", 0.0f, 0.0f, -0.1f), new CharSetting("ｓ", 0.0f, 0.0f, -0.1f), new CharSetting("ｔ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｕ", 0.0f, 0.0f, -0.1f), new CharSetting("ｖ", 0.0f, 0.0f, -0.1f), new CharSetting("ｗ", 0.0f, 0.0f, -0.1f), new CharSetting("ｘ", 0.0f, 0.0f, -0.1f),
			new CharSetting("ｙ", 0.0f, 0.0f, -0.1f), new CharSetting("ｚ", 0.0f, 0.0f, -0.1f), new CharSetting("Ａ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｂ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｃ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｄ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｅ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｆ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｇ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｈ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｉ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｊ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｋ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｌ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｍ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｎ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｏ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｐ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｑ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｒ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｓ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｔ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｕ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｖ", 0.0f, 0.0f, -0.1f),
			new CharSetting("Ｗ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｘ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｙ", 0.0f, 0.0f, -0.1f), new CharSetting("Ｚ", 0.0f, -0.8f, -0.1f),
			new CharSetting("：", 90.0f, 0.0f, -0.1f), new CharSetting("；", 90.0f, 0.0f, -0.1f), new CharSetting("／", 90.0f, 0.0f, -0.1f), new CharSetting("", 90.0f, 0.0f, -0.1f),
			new CharSetting(":", 90.0f, 0.0f, -0.1f), new CharSetting(";", 90.0f, 0.0f, -0.1f), new CharSetting("/", 90.0f, 0.0f, -0.1f), new CharSetting(".", 90.0f, 0.0f, -0.1f),
			new CharSetting("（", 90.0f, -0.8f, -0.2f), new CharSetting("）", 90.0f, -0.8f, -0.2f), new CharSetting("【", 90.0f, -0.8f, -0.1f), new CharSetting("】", 90.0f, -0.8f, -0.1f),
			new CharSetting("―", 90.0f, -0.8f, -0.2f), new CharSetting("〝", 0.0f, -0.5f, 0.4f), new CharSetting("〟", 0.0f, 0.5f, -0.3f),new CharSetting("～", 90.0f, -0.7f, -0.2f)};

	public static CharSetting getSetting(String character) {
		for (int i = 0; i < settings.length; i++) {
			if (settings[i].charcter.equals(character)) {
				return settings[i];
			}
		}
		return null;
	}

	private static final String[] PUNCTUATION_MARK = {"、", "。", "「", "」", "（", "）", "【", "】", "【", "｝", "［", "］"};

	public static boolean isPunctuationMark(String s) {
		for (String functuantionMark : PUNCTUATION_MARK) {
			if (functuantionMark.equals(s)) {
				return true;
			}
		}
		return false;
	}

	private static final String[] END_PUNCTUATION_MARK = {"「", "（", "【", "［"};

	public static boolean isEndPunctuationMark(String s) {
		for (String endFunctuantionMark : END_PUNCTUATION_MARK) {
			if (endFunctuantionMark.equals(s)) {
				return true;
			}
		}
		return false;
	}
}
