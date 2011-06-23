/**
 *
 */
package jp.web.sync.android.app;

import java.util.List;

import jp.web.sync.android.R;
import jp.web.sync.android.adapter.GroupListAdapter;
import jp.web.sync.android.bean.SelfGroupInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.util.Constant;
import android.R.id;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author m-shichi
 *
 */
public class GroupActivity extends ListActivity {

	private int selectedIndex;
	private List<SelfGroupInfoBean> mItemList = null;
	private GroupListAdapter mAdapter;

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.group);

		Intent intent = getIntent();
		final int userId = intent.getIntExtra(Constant.INTENT_USER_ID, 0);

		EditText editTexGroupName = (EditText) findViewById(R.id.text_group_name);

		Button postButton = (Button) findViewById(R.id.btn_group_post);
		postButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		DBHelper dbHelper = DBHelper.getInstance(this);
		mItemList = dbHelper.selectSelfGroupInfo(userId);

		mAdapter = new GroupListAdapter(this, mItemList);
		this.setListAdapter(mAdapter);

		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				System.out.println("ItemClick Now !!");

				SelfGroupInfoBean bean = mItemList.get(index);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				System.out.println("ItemLongClick Now !!");
				selectedIndex = index;
				return false;
			}
		});
	}
}
