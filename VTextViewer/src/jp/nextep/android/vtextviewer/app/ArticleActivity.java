package jp.nextep.android.vtextviewer.app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.bean.ContentItemBean;
import jp.nextep.android.vtextviewer.component.DisplayConfig;
import jp.nextep.android.vtextviewer.component.VTextView;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.util.DomDataUtil;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import jp.nextep.android.vtextviewer.util.MathDataUtil;
import jp.nextep.android.vtextviewer.util.StringDataUtil;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ArticleActivity extends Activity implements OnGestureListener, OnDoubleTapListener {

	private static final String TAG = "VTextViewer";

	private static final int BUFFER_SIZE = 256 * 1024;
	private static final long VIBRATE_TIME = 100L;

	private static int charCount;

	public static boolean isDebug = false;

	private String mMediaId = null;
	private String mMediaName = null;
	private String mContentIndex = null;
	private int mPageNum;
	private int mCurrentPageNum;
	private int mGeneralPageNum;

	private String mMediaListIndex = null;
	private String mContentListIndex = null;

	private int sPosit = 0;
	private int ePosit = 0;
	private GestureDetector mGDetector;
	private Toast mToast;

	private ContentItemBean itemBean = null;

	private DisplayConfig mDisp;

	private float dispWidth;
	private float dispHeight;

	private boolean isDoubleTap = false;
	private boolean isDispImage = false;

	private String[] mImgPath;

	private Vibrator mVibrator;

	private ViewFlipper mViewFlipper;
	private View mCurrentView;
	private View mNextView;
	private View mPrevView;
	// private float lastTouchY;

	private TranslateAnimation mPageUpAnim;
	private TranslateAnimation mPageDownAnim;

	// private float mPositionX = 0;
	private float mPositionY = 0;

	/* ページのIDと順序を管理 */
	private int mViewOrder[] = null;
	private int activeId = 0;
	private int curIdx = -1;
	private int preIdx = -1;
	private int nxtIdx = -1;

	@Override
	protected void onStart() {

		if (itemBean.getImage_flag() == 1) {
			mToast = Toast.makeText(getApplicationContext(), getString(R.string.msg_exist_image) + itemBean.getContents(), Toast.LENGTH_SHORT);
		} else {
			mToast = Toast.makeText(getApplicationContext(), itemBean.getContents(), Toast.LENGTH_SHORT);
		}
		mToast.setGravity(Gravity.TOP, 0, 0);
		mToast.show();

		activeId = mViewFlipper.getCurrentView().getId();

		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.article);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();

		// float tmpDensity = getResources().getDisplayMetrics().density;

		dispWidth = new Float(disp.getWidth());
		dispHeight = new Float(disp.getHeight());

		mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		// 各種設定
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		int fontSize = Integer.parseInt(prefs.getString(getString(R.string.preference_font_size), DisplayConfig.getDefaultFontSize()));
		int blockNum = Integer.parseInt(prefs.getString(getString(R.string.preference_block_num), DisplayConfig.getDefaultBlockNum()));
		String backAndChar = prefs.getString(getString(R.string.preference_back_and_char_color), "0");

		mDisp = new DisplayConfig(fontSize, blockNum, dispWidth, dispHeight);
		if (DisplayConfig.checkModel()) {
			mDisp.setTablet();
		} else {
			mDisp.setSmartphone();
		}

		charCount = mDisp.getRowNum() * mDisp.getLineNum();

		String textArticle = "";

		this.mGDetector = new GestureDetector(this, this);

		Intent intent = getIntent();
		mMediaId = intent.getStringExtra(getString(R.string.intent_media_id));
		mMediaName = intent.getStringExtra(getString(R.string.intent_media_name));
		mContentIndex = intent.getStringExtra(getString(R.string.intent_content_index));
		mCurrentPageNum = Integer.parseInt(intent.getStringExtra(getString(R.string.intent_current_page_num)));
		mGeneralPageNum = Integer.parseInt(intent.getStringExtra(getString(R.string.intent_general_page_num)));
		mMediaListIndex = intent.getStringExtra(getString(R.string.intent_media_list_index));
		mContentListIndex = intent.getStringExtra(getString(R.string.intent_content_list_index));

		DBHelper dbHelper = DBHelper.getInstance(this);
		itemBean = dbHelper.selectContentsByContentIndex(mMediaId, mContentIndex);

		textArticle = getContentText();
		mPageNum = getPageNum(textArticle.length());

		mViewFlipper = (ViewFlipper) findViewById(R.id.layout_switcher);

		mViewOrder = new int[mPageNum];

		for (int j = 0; j < mPageNum; j++) {

			LinearLayout linerLayout = new LinearLayout(this);
			linerLayout.setId(100 + j);
			linerLayout.setOrientation(LinearLayout.VERTICAL);
			linerLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

			for (int i = 0; i < mDisp.getmBlockNum(); i++) {

				if (j == 0 && i == 0) {
					sPosit = 0;
				} else {
					sPosit = ePosit;
				}
				ePosit = sPosit + charCount;
				if (ePosit > textArticle.length()) {
					ePosit = textArticle.length();
					// isTextEnd = true;
				}
				String bText = textArticle.substring(sPosit, ePosit);

				VTextView vtv = new VTextView(this, mDisp.getFontSize(), mDisp.getLineNum(), backAndChar);
				vtv.setId(i);
				vtv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1));
				vtv.setText(bText);
				if (backAndChar.equals("0")) {
					vtv.setBackgroundColor(Color.BLACK);
				} else if (backAndChar.equals("1")) {
					vtv.setBackgroundColor(Color.WHITE);
				}

				linerLayout.addView(vtv);

				if (i < mDisp.getmBlockNum() - 1) {
					ImageView iv = new ImageView(this);

					LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					layoutParams.setMargins(10, 0, 10, 0);
					iv.setLayoutParams(layoutParams);

					iv.setAdjustViewBounds(false);
					iv.setBackgroundColor(Color.GRAY);
					iv.setMaxHeight(2);
					iv.setMinimumHeight(2);
					linerLayout.addView(iv);
				}
			}
			TextView textPgNum = new TextView(this);
			LayoutParams layoutParamsPgNum = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			textPgNum.setLayoutParams(layoutParamsPgNum);
			textPgNum.setText(String.format("%s/%s", String.valueOf(j + 1), mPageNum));
			textPgNum.setTextSize(14);
			if (backAndChar.equals("0")) {
				textPgNum.setBackgroundColor(Color.BLACK);
				textPgNum.setTextColor(Color.WHITE);
			} else if (backAndChar.equals("1")) {
				textPgNum.setBackgroundColor(Color.WHITE);
				textPgNum.setTextColor(Color.BLACK);
			}
			linerLayout.addView(textPgNum);

			mViewFlipper.addView(linerLayout);
			mViewOrder[j] = linerLayout.getId();
		}

		curIdx = 0;
		nxtIdx = 1;

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		return onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGDetector.onTouchEvent(event);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {

		Log.d(TAG, String.format("onDown !![%s]", String.valueOf(e.getY())));

		if (mViewOrder.length <= 1) {
			return false;
		}

		if (isDoubleTap) {
			return false;
		}

		if (mViewOrder.length <= 1) {

		} else if (mViewOrder.length == 2) {
			if (curIdx == 0) {
				preIdx = 1;
				nxtIdx = 1;
			} else if (curIdx == 1) {
				preIdx = 0;
				nxtIdx = 0;
			}
		} else {
			if (curIdx == 0) {
				preIdx = mViewOrder.length - 1;
				nxtIdx = curIdx + 1;
			} else if (curIdx < mViewOrder.length - 1) {
				preIdx = curIdx - 1;
				nxtIdx = curIdx + 1;
			} else if (curIdx == mViewOrder.length - 1) {
				preIdx = mViewOrder.length - 2;
				nxtIdx = 0;
			}
		}

		mCurrentView = mViewFlipper.findViewById(mViewOrder[curIdx]);
		mNextView = mViewFlipper.findViewById(mViewOrder[nxtIdx]);
		mPrevView = mViewFlipper.findViewById(mViewOrder[preIdx]);

		mViewFlipper.bringChildToFront(mCurrentView);

		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		// velocityX > 0 右フリック
		Log.d(TAG, String.format("ON FLING !! %s", String.valueOf(velocityX)));

		Intent intent = null;
		try {
			float moveX = e2.getRawX() - e1.getRawX();
			float moveY = e2.getRawY() - e1.getRawY();

			int index = 0;
			int action = MathDataUtil.getFlickAction(moveX, moveY, dispWidth, dispHeight);
			switch (action) {
				case MathDataUtil.FLICK_RIGHT :

					Log.d(TAG, "right flic now !!");

					intent = new Intent(getApplicationContext(), ContentListActivity.class);
					intent.setAction(Intent.ACTION_VIEW);
					intent.putExtra(getString(R.string.intent_media_id), mMediaId);
					intent.putExtra(getString(R.string.intent_media_name), mMediaName);
					intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
					intent.putExtra(getString(R.string.intent_content_list_index), mContentListIndex);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
					break;
				case MathDataUtil.FLICK_LEFT :

					Log.d(TAG, "left flic now !!");

					// 次記事へ
					if (Integer.parseInt(mContentIndex) < mGeneralPageNum) {
						intent = new Intent(getApplicationContext(), ArticleActivity.class);
						intent.setAction(Intent.ACTION_VIEW);
						intent.putExtra(getString(R.string.intent_media_id), mMediaId);
						intent.putExtra(getString(R.string.intent_media_name), mMediaName);
						intent.putExtra(getString(R.string.intent_content_index), String.valueOf(Integer.parseInt(mContentIndex) + 1));
						intent.putExtra(getString(R.string.intent_next_posit), "0");
						intent.putExtra(getString(R.string.intent_current_page_num), "1");
						intent.putExtra(getString(R.string.intent_general_page_num), String.valueOf(mGeneralPageNum));
						intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
						intent.putExtra(getString(R.string.intent_content_list_index), String.valueOf(Integer.parseInt(mContentListIndex) + 1));
						startActivity(intent);
						finish();
						overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
					}
					break;
				case MathDataUtil.FLICK_DOWN :

					Log.d(TAG, "down flic now !!");

					if (mViewOrder.length <= 1) {
						return false;
					} else if (mViewOrder.length == 2) {
						if (curIdx == 0) {
							preIdx = 0;
							curIdx = 1;
							nxtIdx = 0;
						} else if (curIdx == 1) {
							preIdx = 1;
							curIdx = 0;
							nxtIdx = 1;
						}
					} else {
						if (curIdx == 0) {
							preIdx = mViewOrder.length - 2;
							curIdx = mViewOrder.length - 1;
							nxtIdx = 0;
						} else if (curIdx < mViewOrder.length - 1) {
							index = curIdx;
							preIdx = index - 2;
							curIdx = index - 1;
							nxtIdx = index;
						} else if (curIdx == mViewOrder.length - 1) {
							preIdx = mViewOrder.length - 3;
							curIdx = mViewOrder.length - 2;
							nxtIdx = mViewOrder.length - 1;
						}
					}

					mPageDownAnim = new TranslateAnimation(0, 0, mPositionY, mCurrentView.getHeight());
					mPageDownAnim.setDuration(300L);
					mCurrentView.startAnimation(mPageDownAnim);

					activeId = mPrevView.getId();

					for (int i = 0; i < mViewFlipper.getChildCount(); i++) {
						if (mViewFlipper.getChildAt(i).getId() == activeId) {
							mViewFlipper.setDisplayedChild(i);
							break;
						}
					}

					break;
				case MathDataUtil.FLICK_UP :

					Log.d(TAG, "up flic now !!");

					if (mViewOrder.length <= 1) {
						return false;
					} else if (mViewOrder.length == 2) {
						if (curIdx == 0) {
							preIdx = 0;
							curIdx = 1;
							nxtIdx = 0;
						} else if (curIdx == 1) {
							preIdx = 1;
							curIdx = 0;
							nxtIdx = 1;
						}
					} else {
						if (curIdx == 0) {
							preIdx = 0;
							curIdx = 1;
							nxtIdx = 2;
						} else if (curIdx < mViewOrder.length - 1) {
							index = curIdx;
							preIdx = index;
							curIdx = index + 1;
							nxtIdx = index + 2;
						} else if (curIdx == mViewOrder.length - 1) {
							preIdx = mViewOrder.length - 1;
							curIdx = 0;
							nxtIdx = 1;
						}
					}

					mPageUpAnim = new TranslateAnimation(0, 0, mPositionY, -mCurrentView.getHeight());
					mPageUpAnim.setDuration(300L);
					mCurrentView.startAnimation(mPageUpAnim);

					activeId = mNextView.getId();

					for (int i = 0; i < mViewFlipper.getChildCount(); i++) {
						if (mViewFlipper.getChildAt(i).getId() == activeId) {
							mViewFlipper.setDisplayedChild(i);
							break;
						}
					}

					break;
				case MathDataUtil.FLICK_CANCEL :

					mPageUpAnim = new TranslateAnimation(0, 0, mPositionY, 0);
					mPageUpAnim.setDuration(300L);
					mCurrentView.startAnimation(mPageUpAnim);

					activeId = mCurrentView.getId();
					for (int i = 0; i < mViewFlipper.getChildCount(); i++) {
						if (mViewFlipper.getChildAt(i).getId() == activeId) {
							mViewFlipper.setDisplayedChild(i);
							break;
						}
					}

					break;
			}
		} catch (Exception ex) {
			Log.e(TAG, "", ex);
		} finally {
			mPageUpAnim = null;
			mPageDownAnim = null;
		}
		return true;
	}
	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

		if (mViewOrder.length <= 1) {
			return false;
		}

		mPositionY = e2.getY() - e1.getY();

		mCurrentView.layout(mCurrentView.getLeft(), (int) mPositionY, mCurrentView.getRight(), mCurrentView.getHeight() + (int) mPositionY);

		if (mPositionY < 0) {
			mNextView.setVisibility(View.VISIBLE);
		} else {
			mPrevView.setVisibility(View.VISIBLE);
		}
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.article_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.main_menu :
				intent = new Intent(getApplicationContext(), MediaListActivity.class);
				intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
				break;
			case R.id.sub_menu :
				intent = new Intent(getApplicationContext(), ContentListActivity.class);
				intent.putExtra(getString(R.string.intent_media_id), mMediaId);
				intent.putExtra(getString(R.string.intent_media_name), mMediaName);
				intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
				intent.putExtra(getString(R.string.intent_content_list_index), mContentListIndex);
				break;
			default :
				intent = new Intent(getApplicationContext(), MediaListActivity.class);
				intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
				break;
		}
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {

		Log.d(TAG, "onSingleTapConfirmed !!");

		int index = 0;
		int tapPosit = MathDataUtil.getPosit(e.getRawX(), e.getRawY(), dispWidth, dispHeight);
		try {
			if (isDoubleTap) {
				isDoubleTap = false;
				return true;
			} else {
				if (tapPosit == 7) {
					// 前記事へ
					if (Integer.parseInt(mContentIndex) <= 1) {
						return true;
					}

					mVibrator.vibrate(VIBRATE_TIME);

					Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
					intent.setAction(Intent.ACTION_VIEW);
					intent.putExtra(getString(R.string.intent_media_id), mMediaId);
					intent.putExtra(getString(R.string.intent_media_name), mMediaName);
					intent.putExtra(getString(R.string.intent_content_index), String.valueOf(Integer.parseInt(mContentIndex) - 1));
					intent.putExtra(getString(R.string.intent_next_posit), "0");
					intent.putExtra(getString(R.string.intent_current_page_num), "1");
					intent.putExtra(getString(R.string.intent_general_page_num), String.valueOf(mGeneralPageNum));
					intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
					intent.putExtra(getString(R.string.intent_content_list_index), String.valueOf(Integer.parseInt(mContentListIndex) - 1));
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

				} else if (tapPosit == 9) {
					// 次記事へ
					if (Integer.parseInt(mContentIndex) >= mGeneralPageNum) {
						return true;
					}

					mVibrator.vibrate(VIBRATE_TIME);

					Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
					intent.setAction(Intent.ACTION_VIEW);
					intent.putExtra(getString(R.string.intent_media_id), mMediaId);
					intent.putExtra(getString(R.string.intent_media_name), mMediaName);
					intent.putExtra(getString(R.string.intent_content_index), String.valueOf(Integer.parseInt(mContentIndex) + 1));
					intent.putExtra(getString(R.string.intent_next_posit), "0");
					intent.putExtra(getString(R.string.intent_current_page_num), "1");
					intent.putExtra(getString(R.string.intent_general_page_num), String.valueOf(mGeneralPageNum));
					intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
					intent.putExtra(getString(R.string.intent_content_list_index), String.valueOf(Integer.parseInt(mContentListIndex) + 1));
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

				} else if (tapPosit == 14) {

					// if (mCurrentPageNum == mPageNum) {
					// return false;
					// }

					if (mViewOrder.length <= 1) {
						return false;
					} else if (mViewOrder.length == 2) {
						if (curIdx == 0) {
							preIdx = 0;
							curIdx = 1;
							nxtIdx = 0;
						} else if (curIdx == 1) {
							preIdx = 1;
							curIdx = 0;
							nxtIdx = 1;
						}
					} else {
						if (curIdx == 0) {
							preIdx = 0;
							curIdx = 1;
							nxtIdx = 2;
						} else if (curIdx < mViewOrder.length - 1) {
							index = curIdx;
							preIdx = index;
							curIdx = index + 1;
							nxtIdx = index + 2;
						} else if (curIdx == mViewOrder.length - 1) {
							preIdx = mViewOrder.length - 1;
							curIdx = 0;
							nxtIdx = 1;
						}
					}

					mVibrator.vibrate(VIBRATE_TIME);

					mPageUpAnim = new TranslateAnimation(0, 0, 0, -mCurrentView.getHeight());
					mPageUpAnim.setDuration(600L);
					mCurrentView.startAnimation(mPageUpAnim);

					activeId = mNextView.getId();

					for (int i = 0; i < mViewFlipper.getChildCount(); i++) {
						if (mViewFlipper.getChildAt(i).getId() == activeId) {
							mViewFlipper.setDisplayedChild(i);
							break;
						}
					}

				} else if (tapPosit == 1 || tapPosit == 2 || tapPosit == 3) {
					if (itemBean.getImage_flag() == 1) {
						mToast = Toast.makeText(getApplicationContext(), getString(R.string.msg_exist_image) + itemBean.getContents(), Toast.LENGTH_SHORT);
					} else {
						mToast = Toast.makeText(getApplicationContext(), itemBean.getContents(), Toast.LENGTH_SHORT);
					}
					mToast.setGravity(Gravity.TOP, 0, 0);
					mToast.show();
				}
			}
		} catch (Exception ex) {

		} finally {
			mPageUpAnim = null;
		}
		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {

		Log.d(TAG, "onDoubleTap !!");

		isDoubleTap = true;

		int index = 0;
		int posit = MathDataUtil.getPosit(e.getRawX(), e.getRawY(), dispWidth, dispHeight);

		try {
			if (posit == 14) {

				if (mViewOrder.length <= 1) {
					return false;
				} else if (mViewOrder.length == 2) {
					if (curIdx == 0) {
						preIdx = 0;
						curIdx = 1;
						nxtIdx = 0;
					} else if (curIdx == 1) {
						preIdx = 1;
						curIdx = 0;
						nxtIdx = 1;
					}
				} else {
					if (curIdx == 0) {
						preIdx = 0;
						curIdx = 1;
						nxtIdx = 2;
					} else if (curIdx < mViewOrder.length - 1) {
						index = curIdx;
						preIdx = index;
						curIdx = index + 1;
						nxtIdx = index + 2;
					} else if (curIdx == mViewOrder.length - 1) {
						preIdx = mViewOrder.length - 1;
						curIdx = 0;
						nxtIdx = 1;
					}
				}

				mVibrator.vibrate(VIBRATE_TIME);

				mPageDownAnim = new TranslateAnimation(0, 0, 0, mCurrentView.getHeight());
				mPageDownAnim.setDuration(600L);
				mPageDownAnim.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						isDoubleTap = false;
					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}
				});
				mCurrentView.startAnimation(mPageDownAnim);

				activeId = mPrevView.getId();

				for (int i = 0; i < mViewFlipper.getChildCount(); i++) {
					if (mViewFlipper.getChildAt(i).getId() == activeId) {
						mViewFlipper.setDisplayedChild(i);
						break;
					}
				}

			} else if (posit == 8) {

				isDoubleTap = false;

				if (mImgPath.length > 0) {

					isDispImage = true;

					Intent intent = new Intent(getApplicationContext(), ArticleImageActivity.class);
					intent.putExtra(getString(R.string.intent_media_id), mMediaId);
					intent.putExtra(getString(R.string.intent_image_path), mImgPath);
					startActivity(intent);
					overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
				} else {
					mToast = Toast.makeText(getApplicationContext(), R.string.msg_no_image, Toast.LENGTH_SHORT);
					mToast.show();
				}
			}
		} catch (Exception ex) {

		} finally {
			mPageDownAnim = null;
		}
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// System.out.println("onDoubleTapEvent");
		return false;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		if (event.getAction() == KeyEvent.ACTION_UP) { // キーが離された時
			switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_DPAD_CENTER : // 十字中央キー
					break;
				case KeyEvent.KEYCODE_HOME :
				case KeyEvent.KEYCODE_BACK :
					return true;
				default :
					break;
			}
		}

		return super.dispatchKeyEvent(event);
	}

	/**
	 *
	 * @return
	 */
	private String getContentText() {

		StringBuilder sb = new StringBuilder();
		BufferedInputStream bis = null;
		try {
			if (isDebug) {
				bis = new BufferedInputStream(getAssets().open(itemBean.getId() + "/OEBPS/" + itemBean.getPath()), BUFFER_SIZE);
			} else {
				bis = new BufferedInputStream(new FileInputStream(FileDataUtil.getContentDirPath(this.getPackageName(), mMediaId) + itemBean.getPath()), BUFFER_SIZE);
			}

			DomDataUtil dom = new DomDataUtil(bis);
			dom.init();

			NodeList pList = dom.getParagraph();

			mImgPath = dom.getImagePath();

			String text = "";
			String[] tArray = null;
			for (int i = 0; i < pList.getLength(); i++) {

				Node node = pList.item(i);

				tArray = dom.getText(node);

				if (tArray.length == 0) {
					continue;
				}

				if (tArray.length < 2) {
					// 禁則処理
					text = StringDataUtil.editHeder(tArray[0], mDisp.getLineNum());

					if (i < pList.getLength() - 1) {
						// スペース埋め
						text = StringDataUtil.addSpaceAndLine(text, mDisp.getLineNum());
					}
					sb.append(text);
				} else {
					for (int j = 0; j < tArray.length; j++) {

						if (StringDataUtil.checkSpace(tArray[j])) {
							continue;
						}

						// 禁則処理
						text = StringDataUtil.editHeder(tArray[j], mDisp.getLineNum());

						// スペース埋め
						text = StringDataUtil.addSpace(text, mDisp.getLineNum());

						sb.append(text);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * @param length
	 * @return
	 */
	private int getPageNum(int length) {

		double dTextLength = new Double(length);
		double dDivisor = new Double(mDisp.getRowNum() * mDisp.getLineNum() * mDisp.getmBlockNum());

		double dDivide;
		if (dTextLength % dDivisor == 0.0) {
			dDivide = dTextLength / dDivisor;
		} else {
			dDivide = Math.ceil(dTextLength / dDivisor);
		}

		return (int) dDivide;
	}

}
