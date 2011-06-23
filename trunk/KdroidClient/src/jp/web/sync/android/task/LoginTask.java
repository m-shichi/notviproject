/**
 *
 */
package jp.web.sync.android.task;

import jp.web.sync.android.R;
import jp.web.sync.android.app.GroupActivity;
import jp.web.sync.android.app.RaderActivity;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.util.Constant;
import jp.web.sync.android.util.HttpUtils;
import jp.web.sync.relax.response.ResponseXML;
import jp.web.sync.relax.response.UserInfo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * @author m-shichi
 *
 */
public class LoginTask extends AsyncTask<Void, Integer, ResponseXML>
{
	// -----------------------------------
	// ユーザ情報
	// -----------------------------------
	private SelfInfoBean mSelfInfo = null;

	// -----------------------------------
	// 画面部品
	// -----------------------------------
	private Activity mActivity = null;

	// -----------------------------------
	// 制御変数
	// -----------------------------------
	private int mOpType;
	private int mFirstFlag;
	private boolean isSuccess = false;
	private String txtMsg = "";

	// -----------------------------------
	// 進捗ダイアログ
	// -----------------------------------
	private ProgressDialog pDialog;

	/**
	 *
	 * @param activity
	 * @param opType
	 * @param firstFlag
	 * @param selfInfo
	 */
	public LoginTask(Activity activity, int opType, int firstFlag, SelfInfoBean selfInfo)
	{
		this.mActivity = activity;
		this.mOpType = opType;
		this.mFirstFlag = firstFlag;
		this.mSelfInfo = selfInfo;
	}

	/*
	 * (非 Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute()
	{
		// -----------------------------------
		// 進捗ダイアログ表示
		// -----------------------------------
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
		// -----------------------------------
		// ユーザ情報取得
		// -----------------------------------
		HttpUtils httpUtils = new HttpUtils();
		return httpUtils.requestUserInfo(mOpType, mSelfInfo);
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

		// -----------------------------------
		// ユーザ情報取得
		// -----------------------------------
		UserInfo userInfo = result.getData().getUserInfo();

		// -----------------------------------
		// Preference生成
		// -----------------------------------
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
		SharedPreferences.Editor editor = prefs.edit();

		switch (Integer.parseInt(result.getCode()))
		{
			case Constant.CODE_SIGNIN_SUCCESS :
				txtMsg = "ログイン成功";

				// Preference登録
				editor.putString(mActivity.getString(R.string.pref_key_id), userInfo.getIdAsString());
				editor.putString(mActivity.getString(R.string.pref_key_account), userInfo.getMailAddrAsString());
				editor.putString(mActivity.getString(R.string.pref_key_password), mSelfInfo.getPassword());
				editor.putString(mActivity.getString(R.string.pref_key_terminal_id), userInfo.getTerminalIdAsString());

				isSuccess = true;
				break;
			case Constant.CODE_SIGNIN_FAILED :
				txtMsg = "ログイン失敗";
				break;

			case Constant.CODE_SIGNUP_SUCCESS :
				txtMsg = "ユーザ登録成功";

				// SQLite登録
				DBHelper db = DBHelper.getInstance(mActivity);
				db.insertSelfInfo(result, mSelfInfo.getPassword());

				// Preference登録
				editor.putString(mActivity.getString(R.string.pref_key_id), userInfo.getIdAsString()); // ユーザID
				editor.putString(mActivity.getString(R.string.pref_key_account), userInfo.getMailAddrAsString()); // メールアドレス
				editor.putString(mActivity.getString(R.string.pref_key_password), mSelfInfo.getPassword()); // 暗号化前パスワード
				editor.putString(mActivity.getString(R.string.pref_key_terminal_id), userInfo.getTerminalIdAsString()); // 端末ID

				isSuccess = true;
				break;
			case Constant.CODE_SIGNUP_FAILED :
				txtMsg = "ユーザ登録失敗";
				break;
		}
		Toast.makeText(mActivity, txtMsg, Toast.LENGTH_LONG).show();

		if (!isSuccess)
		{
			return;
		}
		else
		{
			// 次画面設定
			Intent intent = null;
			// 初回起動
			if (mFirstFlag == 1)
			{
				intent = new Intent(mActivity, GroupActivity.class);
			}
			// 初回以降
			else
			{
				intent = new Intent(mActivity, RaderActivity.class);
			}
			intent.setAction(Intent.ACTION_VIEW);
			intent.putExtra(Constant.INTENT_FIRST_FLAG, mFirstFlag);
			intent.putExtra(Constant.INTENT_USER_ID, result.getData_UserInfo_Id());
			mActivity.startActivity(intent);
			mActivity.finish();
		}
	}
}
