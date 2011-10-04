package jp.nextep.android.vtextviewer.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

public class VTextView extends TextView {

	// private static final String FONT_PATH =
	// Environment.getExternalStorageDirectory().getAbsolutePath() +
	// File.separator + "ipam.ttf";

	// private static final int TOP_SPACE = 12;

	// private static final int BOTTOM_SPACE = 12;
	// private static final int FONT_SIZE = 18;
	// private Typeface mFace;

	private Paint mPaint;

	private String text = "";

	private int width;
	// private int height;
	private int dispLineNum;

	public VTextView(Context context, int fontSize, int lineNum, String backAndChar) {
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(fontSize);
		if (backAndChar.equals("0")) {
			mPaint.setColor(Color.WHITE);
		} else if (backAndChar.equals("1")) {
			mPaint.setColor(Color.BLACK);
		}
		dispLineNum = lineNum;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		width = getWidth();
		// height = getHeight();
	}

	@Override
	public void onDraw(Canvas canvas) {
		float fontSpacing = mPaint.getFontSpacing();
		float lineSpacing = fontSpacing * 1.5f;
		float x = width - lineSpacing;
		float y = DisplayConfig.ARTICLE_TOP_SPACE + fontSpacing * 1;
		String[] s = text.split("");
		boolean newLine = false;

		int charCount = 0;
		for (int i = 1; i <= text.length(); i++) {
			newLine = false;

			if (s[i].equals("")) {
				continue;
			}

			CharSetting setting = CharSetting.getSetting(s[i]);
			if (setting == null) {
				// 文字設定がない場合、そのまま描画
				canvas.drawText(s[i], x, y, mPaint);
			} else {
				// 文字設定が見つかったので、設定に従い描画
				canvas.save();
				canvas.rotate(setting.angle, x, y);
				canvas.drawText(s[i], x + fontSpacing * setting.x, y + fontSpacing * setting.y, mPaint);
				canvas.restore();
			}

			charCount++;
			if (charCount == dispLineNum) {
				newLine = true;
				charCount = 0;
			} else {
				newLine = false;
			}

			if (newLine) {
				// 改行処理
				x -= lineSpacing;
				// y = TOP_SPACE;
				y = DisplayConfig.ARTICLE_TOP_SPACE + fontSpacing;
			} else {
				// 文字を送る
				y += fontSpacing;
			}
		}
	}
}
