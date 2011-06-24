/**
 *
 */
package jp.web.sync.android.task;

import java.util.Iterator;
import java.util.List;

import jp.web.sync.android.R;
import jp.web.sync.android.adapter.GroupListAdapter;
import jp.web.sync.android.app.RaderActivity;
import jp.web.sync.android.bean.SelfGroupInfoBean;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.util.Constant;
import jp.web.sync.android.util.HttpUtils;
import jp.web.sync.relax.response.GroupInfo;
import jp.web.sync.relax.response.ResponseXML;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * @author m-shichi@SYNC Co., Ltd.
 *
 */
public class GroupTask extends AsyncTask<Void, Integer, ResponseXML>
{
	// -----------------------------------
	// ユーザ情報
	// -----------------------------------
	private SelfInfoBean mSelfInfo = null;
	private SelfGroupInfoBean mSelfGroupInfo = null;

	// -----------------------------------
	// 画面部品
	// -----------------------------------
	private Activity mActivity = null;
	private GroupListAdapter mAdapter = null;
	private Context mContext = null;
	private List<SelfGroupInfoBean> mItemList = null;
	private ProgressDialog pDialog = null;

	private int mOpType;
	private int mFirstFlag;
	private String message = null;
	private DBHelper mDbHelper = null;
	private GroupInfo[] mGroupInfos = null;

	/**
	 *
	 * @param activity
	 * @param adapter
	 * @param opType
	 * @param firstFlag
	 * @param selfInfo
	 * @param selfGroupInfo
	 * @param itemList
	 */
	public GroupTask(Activity activity, GroupListAdapter adapter, int opType, int firstFlag, SelfInfoBean selfInfo, SelfGroupInfoBean selfGroupInfo, List<SelfGroupInfoBean> itemList)
	{
		this.mActivity = activity;
		this.mAdapter = adapter;
		this.mOpType = opType;
		this.mFirstFlag = firstFlag;
		this.mSelfInfo = selfInfo;
		this.mSelfGroupInfo = selfGroupInfo;
		this.mItemList = itemList;
		this.mContext = activity.getApplicationContext();
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute()
	{
		// super.onPreExecute();
		pDialog = new ProgressDialog(mActivity);
		pDialog.setMessage(mActivity.getString(R.string.jp_loading));
		pDialog.setIndeterminate(true);
		pDialog.show();
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values)
	{
		super.onProgressUpdate(values);
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected ResponseXML doInBackground(Void... arg0)
	{
		HttpUtils httpUtils = new HttpUtils();
		return httpUtils.requestGroupInfo(mOpType, mSelfInfo, mSelfGroupInfo);
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(ResponseXML result)
	{
		// -----------------------------------
		// 進捗ダイアログ終了
		// -----------------------------------
		pDialog.dismiss();

		mDbHelper = DBHelper.getInstance(mActivity);

		int code = Integer.parseInt(result.getCode());
		mGroupInfos = result.getData().getGroupInfo();

		// ダイアログ
		AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);

		switch (code)
		{
			case 200 :
			case 210 :
				if (code == 200)
				{
					message = String.format(mContext.getString(R.string.msg_group_new), mSelfGroupInfo.getGroupName());
				}
				else
				{
					message = String.format(mContext.getString(R.string.msg_group_exists), mSelfGroupInfo.getGroupName());
				}
				adb.setMessage(message);
				adb.setPositiveButton(mContext.getString(R.string.button_ok), new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();

						mDbHelper.deleteSelfGroupInfoByUserId(mSelfInfo.getId());

						for (GroupInfo obj : mGroupInfos)
						{
							mDbHelper.insertSelfGroupInfo(obj.getId(), mSelfInfo.getId(), obj.getGroupNameAsString());
						}
						List<SelfGroupInfoBean> itemList = mDbHelper.selectSelfGroupInfo(mSelfInfo.getId());
						if (!mItemList.equals(itemList))
						{
							mItemList.clear();
							for (Iterator<SelfGroupInfoBean> intent = itemList.iterator(); intent.hasNext();)
							{
								SelfGroupInfoBean bean = intent.next();
								mItemList.add(bean);
							}
							mAdapter.notifyDataSetChanged();
						}

						// -----------------------------------
						// 次画面設定
						// -----------------------------------
						SelfGroupInfoBean newBean = itemList.get(itemList.size() - 1);
						Intent intent = new Intent(mActivity, RaderActivity.class);
						intent.setAction(Intent.ACTION_VIEW);
						intent.putExtra(Constant.INTENT_USER_ID, mSelfInfo.getId());
						intent.putExtra(Constant.INTENT_GROUP_ID, newBean.getId());
						mActivity.startActivity(intent);
						mActivity.finish();
					}
				});
				adb.setNegativeButton(mContext.getString(R.string.button_cancel), new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
					}
				});
				break;
			case 201 :
			case 211 :
				if (code == 201)
				{
					SelfGroupInfoBean selfGroupInfo = new SelfGroupInfoBean();
					selfGroupInfo.setId(mGroupInfos[0].getIdAsInteger());
					selfGroupInfo.setUserId(mSelfInfo.getId());
					selfGroupInfo.setGroupName(mGroupInfos[0].getGroupNameAsString());

					// -----------------------------------
					// 非同期処理
					// -----------------------------------
					GroupTask task = new GroupTask(mActivity, mAdapter, 2, mFirstFlag, mSelfInfo, selfGroupInfo, mItemList);
					task.execute();
				}
				else
				{
				}
				break;
		}
	}
}
