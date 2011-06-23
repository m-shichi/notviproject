package info.clockworksapple.android.barsearch.twitter.task;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.dialog.OAuthEntryDialog;
import info.clockworksapple.android.barsearch.twitter.bean.TaskResultBean;
import info.clockworksapple.android.barsearch.twitter.bean.TwitterOAuthBean;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.EditText;

public class TwitterEntryPinTask extends AsyncTask<Void, Integer, TaskResultBean> {

	private Activity mActivity;
	private OAuthEntryDialog mDialog;
	private TwitterOAuthBean mOAuth;
	private Twitter mTwitter;
	private RequestToken mRequestToken;
	private ProgressDialog mProgressDialog;

	public TwitterEntryPinTask(Activity activity, OAuthEntryDialog dialog, TwitterOAuthBean oAuth, Twitter twitter, RequestToken requestToken) {
		this.mActivity = activity;
		this.mDialog = dialog;
		this.mOAuth = oAuth;
		this.mTwitter = twitter;
		this.mRequestToken = requestToken;
	}

	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mActivity);
		// mProgressDialog.setTitle("OAuth Approving ...");
		mProgressDialog.setMessage(mActivity.getString(R.string.dialog_msg_post_pincode));
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected TaskResultBean doInBackground(Void... params) {
		return entryOAuth();
	}

	@Override
	protected void onPostExecute(TaskResultBean result) {
		// super.onPostExecute(result);
		mProgressDialog.dismiss();

		final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity);
		if (result.isResult()) {
			dialogBuilder.setTitle(mActivity.getString(R.string.dialog_title_oauth_entry));
			dialogBuilder.setIcon(android.R.drawable.ic_dialog_info);
			dialogBuilder.setMessage(R.string.dialog_msg_oauth_entry_success);
		} else {
			dialogBuilder.setTitle(mActivity.getString(R.string.dialog_title_oauth_entry));
			dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
			dialogBuilder.setMessage(R.string.dialog_msg_oauth_entry_failure);
		}
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				mDialog.dismiss();
			}
		});
		dialogBuilder.create().show();

	}

	/**
	 *
	 * @return
	 */
	private TaskResultBean entryOAuth() {

		String pin = null;
		TaskResultBean bean = new TaskResultBean();

		EditText editText = (EditText) mDialog.findViewById(R.id.edit_text_bin_code);
		pin = editText.getText().toString().trim();
		if (pin.equals("")) {
			bean.setResult(false);
			bean.setMessage("");
		}

		AccessToken accessToken = null;
		while (null == accessToken) {
			// requestToken = twitter.getOAuthRequestToken();
			System.out.println("requestToken:" + mRequestToken.getToken());
			System.out.println("requestTokenSecret:" + mRequestToken.getTokenSecret());
			try {
				accessToken = mTwitter.getOAuthAccessToken(mRequestToken, pin);

				mOAuth.setOAuth(true);
				mOAuth.setAccessToken(accessToken.getToken());
				mOAuth.setAccessTokenSecret(accessToken.getTokenSecret());
				bean.setResult(true);

			} catch (TwitterException e) {
				bean.setResult(false);
				bean.setStatus(String.valueOf(e.getStatusCode()));
				if (401 == e.getStatusCode()) {
					bean.setMessage("Unable to get the access token.");
				} else {
					bean.setMessage("");
				}
				return bean;
			}
		}
		return bean;
	}
}
