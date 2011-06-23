package net.londatiga.android;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.common.StringUtil;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Popup window, shows action list as icon and text like the one in Gallery3D
 * app.
 *
 * @author Lorensius. W. T
 */
public class QuickAction extends CustomPopupWindow {
	private final View root;
	private final ImageView mArrowUp;
	private final ImageView mArrowDown;
	private final LayoutInflater inflater;
	private final Context context;
	// private final MapView mMapView;

	public static final int ANIM_GROW_FROM_LEFT = 1;
	public static final int ANIM_GROW_FROM_RIGHT = 2;
	public static final int ANIM_GROW_FROM_CENTER = 3;
	public static final int ANIM_REFLECT = 4;
	public static final int ANIM_AUTO = 5;

	private int animStyle;
	private ViewGroup mTrack;
	// private ScrollView scroller;
	private ArrayList<ActionItem> actionList;
	private int[] mLocation;
	private int layoutHeight;

	/**
	 * Constructor
	 *
	 * @param anchor
	 *            {@link View} on where the popup window should be displayed
	 */
	public QuickAction(View anchor) {
		super(anchor);

		actionList = new ArrayList<ActionItem>();
		context = anchor.getContext();
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// mMapView = (MapView) anchor;
		root = inflater.inflate(R.layout.popup, null);

		mArrowDown = (ImageView) root.findViewById(R.id.arrow_down);
		mArrowUp = (ImageView) root.findViewById(R.id.arrow_up);

		setContentView(root);

		mTrack = (ViewGroup) root.findViewById(R.id.tracks);
		// scroller = (ScrollView) root.findViewById(R.id.scroller);
		animStyle = ANIM_AUTO;
	}

	/**
	 * Set animation style
	 *
	 * @param animStyle
	 *            animation style, default is set to ANIM_AUTO
	 */
	public void setAnimStyle(int animStyle) {
		this.animStyle = animStyle;
	}

	/**
	 * Add action item
	 *
	 * @param action
	 *            {@link ActionItem} object
	 */
	public void addActionItem(ActionItem action) {
		actionList.add(action);
	}

	/**
	 * @param location
	 */
	public void setLocation(int[] location) {
		this.mLocation = location;
	}

	public void setViewHeight(int measuredHeight) {
		this.layoutHeight = measuredHeight;
	}

	/**
	 * Show popup window. Popup is automatically positioned, on top or bottom of
	 * anchor view.
	 *
	 */
	public void show() {
		preShow();

		int xPos, yPos;

		// int[] location = new int[2];
		// anchor.getLocationOnScreen(location);

		Rect anchorRect = new Rect(mLocation[0] - 16, mLocation[1] - 16, mLocation[0] + 16, mLocation[1] + 16);
		// Rect anchorRect = new Rect(location[0], location[1], location[0] +
		// anchor.getWidth(), location[1] + anchor.getHeight());

		createActionList();

		root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		int rootHeight = root.getMeasuredHeight();
		int rootWidth = root.getMeasuredWidth();

		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		// int screenHeight = windowManager.getDefaultDisplay().getHeight();
		// int viewHeight = mMapView.getMeasuredHeight();

		// automatically get X coord of popup (top left)
		if (anchorRect.centerX() >= rootWidth / 2) {
			if (anchorRect.centerX() + rootWidth / 2 > screenWidth) {
				xPos = screenWidth - rootWidth;
			} else {
				xPos = anchorRect.centerX() - (rootWidth / 2);
			}
		} else {
			xPos = 0;
		}
		// if (rootWidth > screenWidth) {
		// xPos = 0;
		// } else {
		// if ((anchorRect.left + rootWidth) > screenWidth) {
		// if (anchorRect.centerX() < rootWidth / 2) {
		// xPos = 0;
		// } else {
		// xPos = screenWidth - 6 - rootWidth;
		// }
		// } else {
		// xPos = anchorRect.centerX() - rootWidth / 2;
		// }
		// }
		xPos = xPos > 0 ? xPos : 0;

		int dyTop = anchorRect.top;
		// int dyBottom = screenHeight - anchorRect.bottom;

		boolean onTop = (dyTop - rootHeight > 0) ? true : false;
		if (onTop) {
			yPos = anchorRect.top + layoutHeight - (rootHeight / 2);
		} else {
			yPos = anchorRect.bottom + layoutHeight;
		}
		yPos = yPos > 0 ? yPos : 0;

		showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up), anchorRect.centerX() - xPos);

		setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

		mPopup.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}

	/**
	 * Set animation style
	 *
	 * @param screenWidth
	 *            screen width
	 * @param requestedX
	 *            distance from left edge
	 * @param onTop
	 *            flag to indicate where the popup should be displayed. Set TRUE
	 *            if displayed on top of anchor view and vice versa
	 */
	private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
		int arrowPos = requestedX - mArrowUp.getMeasuredWidth() / 2;

		switch (animStyle) {
			case ANIM_GROW_FROM_LEFT :
				mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
				break;

			case ANIM_GROW_FROM_RIGHT :
				mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
				break;

			case ANIM_GROW_FROM_CENTER :
				mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
				break;

			case ANIM_REFLECT :
				mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Reflect : R.style.Animations_PopDownMenu_Reflect);
				break;

			case ANIM_AUTO :
				if (arrowPos <= screenWidth / 4) {
					mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
				} else if (arrowPos > screenWidth / 4 && arrowPos < 3 * (screenWidth / 4)) {
					mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
				} else {
					mPopup.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
				}

				break;
		}
	}

	/**
	 * Create action list
	 */
	private void createActionList() {
		View view;
		String title;
		Drawable icon = null;
		OnClickListener listener;

		for (int i = 0; i < actionList.size(); i++) {
			title = actionList.get(i).getTitle();
			// icon = actionList.get(i).getIcon();
			listener = actionList.get(i).getListener();

			view = getActionItem(title, icon, listener);

			view.setFocusable(true);
			view.setClickable(true);

			mTrack.addView(view);
		}
	}

	/**
	 * Get action item {@link View}
	 *
	 * @param title
	 *            action item title
	 * @param icon
	 *            {@link Drawable} action item icon
	 * @param listener
	 *            {@link View.OnClickListener} action item listener
	 * @return action item {@link View}
	 */
	private View getActionItem(String title, Drawable icon, OnClickListener listener) {
		LinearLayout container = (LinearLayout) inflater.inflate(R.layout.action_item, null);

		// ImageView img = (ImageView)
		// container.findViewById(R.id.action_item_icon);
		TextView text = (TextView) container.findViewById(R.id.action_item_title);

		// if (icon != null) {
		// img.setImageDrawable(icon);
		// }

		if (title != null) {
			title = StringUtil.zenkakuAlphabetToHankaku(title.replaceAll("　", " "));
			SpannableString spannableString = new SpannableString(title);
			spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
			text.setText(spannableString);
		}

		if (listener != null) {
			container.setOnClickListener(listener);
		}

		return container;
	}

	/**
	 * Show arrow
	 *
	 * @param whichArrow
	 *            arrow type resource id
	 * @param requestedX
	 *            distance from left screen
	 */
	private void showArrow(int whichArrow, int requestedX) {
		final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp : mArrowDown;
		final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown : mArrowUp;

		final int arrowWidth = mArrowUp.getMeasuredWidth();

		showArrow.setVisibility(View.VISIBLE);

		ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow.getLayoutParams();

		param.leftMargin = requestedX - (arrowWidth / 2);

		hideArrow.setVisibility(View.INVISIBLE);
	}
}