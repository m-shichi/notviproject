/**
 *
 */
package jp.web.sync.android.app;

import java.util.List;

import jp.web.sync.android.R;
import jp.web.sync.android.adapter.GroupListAdapter;
import jp.web.sync.android.bean.SelfGroupInfoBean;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.task.GroupTask;
import jp.web.sync.android.util.Constant;
import jp.web.sync.android.util.CustomStringUtils;
import android.R.id;
import android.app.Activity;
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
public class GroupActivity extends ListActivity
{
	private Activity mActivity;
	private GroupListAdapter mAdapter;
	private int selectedIndex;
	private List<SelfGroupInfoBean> mItemList = null;
	private SelfInfoBean mSelfInfo = null;
	private SelfGroupInfoBean mSelfGroupInfo = null;
	/*
	 * (非 Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.group);

		DBHelper dbHelper = DBHelper.getInstance(this);

		Intent intent = getIntent();
		final int userId = intent.getIntExtra(Constant.INTENT_USER_ID, 0);
		final int firstFlag = intent.getIntExtra(Constant.INTENT_FIRST_FLAG, 0);
		final EditText editTexGroupName = (EditText) findViewById(R.id.text_group_name);

		// ユーザ情報
		mSelfInfo = dbHelper.selectSelfInfoById(userId);
		mActivity = this;

		// -----------------------------------
		// ListView設定
		// -----------------------------------
		mItemList = dbHelper.selectSelfGroupInfo(userId);
		mAdapter = new GroupListAdapter(this, mItemList);
		this.setListAdapter(mAdapter);

		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3)
			{
				SelfGroupInfoBean bean = mItemList.get(index);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3)
			{
				selectedIndex = index;
				return false;
			}
		});

		// -----------------------------------
		// 送信ボタン
		// -----------------------------------
		Button postButton = (Button) findViewById(R.id.btn_group_post);
		postButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String groupName = CustomStringUtils.nullValue(editTexGroupName.getText().toString());
				if (groupName.length() == 0)
				{
					editTexGroupName.setError(getString(R.string.err_msg_no_input));
					return;
				}
				mSelfGroupInfo = new SelfGroupInfoBean();
				mSelfGroupInfo.setGroupName(groupName);

				// -----------------------------------
				// 非同期処理
				// -----------------------------------
				GroupTask task = new GroupTask(mActivity, mAdapter, 1, firstFlag, mSelfInfo, mSelfGroupInfo, mItemList);
				task.execute();
			}
		});
	}
}
