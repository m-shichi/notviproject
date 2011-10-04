package jp.nextep.android.vtextviewer.util;

import java.math.BigDecimal;

public class MathDataUtil {

	public static final int FLICK_RIGHT = 0;
	public static final int FLICK_LEFT = 1;
	public static final int FLICK_DOWN = 2;
	public static final int FLICK_UP = 3;
	public static final int FLICK_CANCEL = 99;

	private MathDataUtil() {
	}

	/**
	 *
	 * @param moveX
	 * @param moveY
	 * @return 0：右／1：左／2：下／3：上
	 */
	public static int getFlickAction(float moveX, float moveY, float dispWidth, float dispHeight) {
		int action = FLICK_CANCEL;
		if (Math.abs(moveX) >= Math.abs(moveY) && Math.abs(moveX) > dispWidth / 3) {
			// よこ
			action = moveX > 0 ? FLICK_RIGHT : FLICK_LEFT;
		} else if (Math.abs(moveY) > Math.abs(moveX) && Math.abs(moveY) > dispHeight / 5) {
			// たて
			action = moveY > 0 ? FLICK_DOWN : FLICK_UP;
		}
		return action;
	}

	/**
	 *
	 * @param rawX
	 * @param rawY
	 * @param dispWidth
	 * @param dispHeight
	 * @return
	 */
	public static int getPosit(float rawX, float rawY, float dispWidth, float dispHeight) {

		float widthRate = dispWidth / 3;
		float heightRate = dispHeight / 5;

		int xPosit = 0;
		int yPosit = 0;

		int positon = 0;

		if (rawX <= widthRate * 1) {
			xPosit = 1;
		} else if (widthRate * 1 < rawX && rawX <= widthRate * 2) {
			xPosit = 2;
		} else if (widthRate * 2 < rawX && rawX <= widthRate * 3) {
			xPosit = 3;
		}

		if (rawY <= heightRate * 1) {
			yPosit = 1;
		} else if (heightRate * 1 < rawY && rawY <= heightRate * 2) {
			yPosit = 2;
		} else if (heightRate * 2 < rawY && rawY <= heightRate * 3) {
			yPosit = 3;
		} else if (heightRate * 3 < rawY && rawY <= heightRate * 4) {
			yPosit = 4;
		} else if (heightRate * 4 < rawY && rawY <= heightRate * 5) {
			yPosit = 5;
		}

		switch (yPosit) {
			case 1 :
				if (xPosit == 1) {
					positon = 1;
				} else if (xPosit == 2) {
					positon = 2;
				} else if (xPosit == 3) {
					positon = 3;
				}
				break;
			case 2 :
				if (xPosit == 1) {
					positon = 4;
				} else if (xPosit == 2) {
					positon = 5;
				} else if (xPosit == 3) {
					positon = 6;
				}
				break;
			case 3 :
				if (xPosit == 1) {
					positon = 7;
				} else if (xPosit == 2) {
					positon = 8;
				} else if (xPosit == 3) {
					positon = 9;
				}
				break;
			case 4 :
				if (xPosit == 1) {
					positon = 10;
				} else if (xPosit == 2) {
					positon = 11;
				} else if (xPosit == 3) {
					positon = 12;
				}
				break;
			case 5 :
				if (xPosit == 1) {
					positon = 13;
				} else if (xPosit == 2) {
					positon = 14;
				} else if (xPosit == 3) {
					positon = 15;
				}
				break;
		}

		return positon;
	}

	/**
	 *
	 * @param dispHeight
	 * @param articleTopSpace
	 * @param fontSpace
	 * @return
	 */
	public static int calcLineNum(float dispHeight, int articleTopSpace, float fontSpace) {

		BigDecimal bdHeight = new BigDecimal(dispHeight);
		BigDecimal bdSpace = new BigDecimal(articleTopSpace);
		BigDecimal bdFont = new BigDecimal(fontSpace);

		BigDecimal bdNum1 = bdHeight.subtract(bdSpace);
		BigDecimal bdLineNum = bdNum1.divide(bdFont, 0, BigDecimal.ROUND_DOWN);

		return bdLineNum.intValue() - 1;
	}

	/**
	 *
	 * @param dispWidth
	 * @param lineSpace
	 * @return
	 */
	public static int calcRowNum(float dispWidth, float lineSpace) {

		BigDecimal bdWidth = new BigDecimal(dispWidth);
		BigDecimal bdSpace = new BigDecimal(lineSpace);

		BigDecimal bdRowNum = bdWidth.divide(bdSpace, 0, BigDecimal.ROUND_DOWN);

		return bdRowNum.intValue();
	}
}
