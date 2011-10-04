package jp.nextep.android.vtextviewer.app;

import java.util.Iterator;
import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.adapter.ContentListAdapter;
import jp.nextep.android.vtextviewer.bean.ContentItemBean;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.util.MathDataUtil;
import android.R.id;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContentListActivity extends ListActivity implements OnGestureListener {

	private String mMediaId = null;
	private String mMediaName = null;
	private String mMediaListIndex = null;
	private String mContentListIndex = null;
	private int mPageCount = 0;

	private GestureDetector mGDetector;

	private boolean onFiling = false;

	private float dispWidth;
	private float dispHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.content_list);

		this.mGDetector = new GestureDetector(this, this);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();

		dispWidth = new Float(disp.getWidth());
		dispHeight = new Float(disp.getHeight());

		Intent intent = getIntent();
		mMediaId = intent.getStringExtra(getString(R.string.intent_media_id));
		mMediaName = intent.getStringExtra(getString(R.string.intent_media_name));
		mMediaListIndex = intent.getStringExtra(getString(R.string.intent_media_list_index));
		mContentListIndex = intent.getStringExtra(getString(R.string.intent_content_list_index));

		DBHelper dbHelper = DBHelper.getInstance(this);
		final List<ContentItemBean> itemList = dbHelper.selectContents(mMediaId);

		for (Iterator<ContentItemBean> i = itemList.iterator(); i.hasNext();) {
			if (!i.next().getId().equals("-")) {
				mPageCount++;
			}
		}

		ContentListAdapter adapter = new ContentListAdapter(this, itemList);
		this.setListAdapter(adapter);

		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int index, long arg3) {
				if (onFiling) {
					onFiling = false;
					return;
				}

				if (itemList.get(index).getId().equals("-")) {
					return;
				}

				System.out.println(String.format("onItemClick!![%s]", String.valueOf(index)));
				Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
				intent.putExtra(getString(R.string.intent_media_id), mMediaId);
				intent.putExtra(getString(R.string.intent_media_name), mMediaName);
				intent.putExtra(getString(R.string.intent_content_index), String.valueOf(itemList.get(index).getContent_index()));
				intent.putExtra(getString(R.string.intent_next_posit), "0");
				intent.putExtra(getString(R.string.intent_current_page_num), "1");
				intent.putExtra(getString(R.string.intent_general_page_num), String.valueOf(mPageCount));
				intent.putExtra(getString(R.string.intent_content_list_index), String.valueOf(index));
				intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
				// finish();
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});
		if (null != mContentListIndex && !mContentListIndex.equals("")) {
			listView.setSelection(Integer.parseInt(mContentListIndex));
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		return onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mGDetector.onTouchEvent(event)) {
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		Intent intent = null;

		onFiling = true;
		float moveX = e2.getRawX() - e1.getRawX();
		float moveY = e2.getRawY() - e1.getRawY();

		int action = MathDataUtil.getFlickAction(moveX, moveY, dispWidth, dispHeight);
		switch (action) {
			case MathDataUtil.FLICK_RIGHT :
				System.out.println("right flic now！！");

				intent = new Intent(getApplicationContext(), MediaListActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
				break;
			case MathDataUtil.FLICK_LEFT :
				System.out.println("left flic now！！");
				break;
			case MathDataUtil.FLICK_DOWN :
				System.out.println("down flic now！！");
				break;
			case MathDataUtil.FLICK_UP :
				System.out.println("up flic now！！");
				break;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.content_list_option, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.main_menu :
				intent = new Intent(getApplicationContext(), MediaListActivity.class);
				intent.putExtra(getString(R.string.intent_media_list_index), mMediaListIndex);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
				break;
			case R.id.setting :
				intent = new Intent();
				intent.setClassName("jp.nextep.android.vtextviewer", "jp.nextep.android.vtextviewer.app.Preference");
				startActivity(intent);
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
				break;
			default :
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
