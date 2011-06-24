/**
 *
 */
package jp.web.sync.android.task;

import jp.web.sync.android.R;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.util.HttpUtils;
import jp.web.sync.relax.response.ResponseXML;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * @author m-shichi
 *
 */
public class EditUserTask extends AsyncTask<Void, Integer, ResponseXML>
{

	private SelfInfoBean mSelfInfo = null;
	private Activity mActivity = null;
	private int mFirstFlag;
	private ProgressDialog pDialog = null;

	public EditUserTask(SelfInfoBean selfInfo, Activity activity, int firstFlag)
	{
		this.mSelfInfo = selfInfo;
		this.mActivity = activity;
		this.mFirstFlag = firstFlag;
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute()
	{
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
	protected ResponseXML doInBackground(Void... params)
	{
		HttpUtils httpUtils = new HttpUtils();
		return httpUtils.requestUserInfo(3, mSelfInfo);
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(ResponseXML result)
	{
		super.onPostExecute(result);
	}
}
