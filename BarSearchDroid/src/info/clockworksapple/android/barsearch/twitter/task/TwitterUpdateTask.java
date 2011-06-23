package info.clockworksapple.android.barsearch.twitter.task;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.app.TweetListActivity;
import info.clockworksapple.android.barsearch.app.TweetPostActivity;
import info.clockworksapple.android.barsearch.twitter.bean.TaskResultBean;
import info.clockworksapple.android.barsearch.twitter.bean.TwitterOAuthBean;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

public class TwitterUpdateTask extends AsyncTask<String, Integer, TaskResultBean> {

	// private static final Object LOCK = new Object();
	private TweetPostActivity mActivity;
	private TwitterOAuthBean mOAuth;
	private String mStatus;
	private String mBackFlag;
	private ProgressDialog mProgressDialog;

	public TwitterUpdateTask(Activity activity, TwitterOAuthBean OAuth, String status, String backFflag) {
		this.mActivity = (TweetPostActivity) activity;
		this.mOAuth = OAuth;
		this.mStatus = status;
		this.mBackFlag = backFflag;
	}

	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mActivity);
		// mProgressDialog.setTitle("Update Tweets...");
		mProgressDialog.setMessage(mActivity.getString(R.string.dialog_msg_post_tweets));
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}

	@Override
	protected TaskResultBean doInBackground(String... arg0) {
		return updateStatus(mStatus);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(TaskResultBean result) {
		// super.onPostExecute(result);
		mProgressDialog.dismiss();

		final AlertDialog.Builder adb = new AlertDialog.Builder(mActivity);
		if (result.isResult()) {
			adb.setTitle(mActivity.getString(R.string.dialog_title_tweet_post));
			adb.setMessage(mActivity.getString(R.string.dialog_msg_tweet_post_success));
			adb.setIcon(android.R.drawable.ic_dialog_info);
		} else {
			adb.setTitle(mActivity.getString(R.string.dialog_title_tweet_post));
			adb.setMessage(String.format("%s[%s]", mActivity.getString(R.string.dialog_msg_tweet_post_failure), result.getStatus()));
			adb.setIcon(android.R.drawable.ic_dialog_alert);
		}
		adb.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mActivity.finish();
				if (mBackFlag.equals("1")) {
					Intent intent = new Intent(mActivity.getApplicationContext(), TweetListActivity.class);
					intent.setAction(Intent.ACTION_VIEW);
					mActivity.startActivity(intent);
				}
			}
		});
		adb.create().show();
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	private TaskResultBean updateStatus(String tweet) {

		TaskResultBean bean = new TaskResultBean();
		Twitter twitter = null;
		try {
			twitter = new TwitterFactory().getInstance();
			// アクセスキー
			AccessToken accessToken = new AccessToken(mOAuth.getAccessToken(), mOAuth.getAccessTokenSecret());
			// コンシューマキー
			twitter.setOAuthConsumer(mOAuth.getConsumerKey(), mOAuth.getConsumerSecret());
			twitter.setOAuthAccessToken(accessToken);

			twitter4j.Status status = twitter.updateStatus(tweet);

			bean.setResult(true);
			bean.setStatus(status.getText());
			bean.setMessage("tweet post success");

		} catch (TwitterException e) {
			e.printStackTrace();
			bean.setResult(false);
			bean.setStatus(String.valueOf(e.getStatusCode()));
			bean.setMessage(e.getMessage());
		}
		return bean;
	}

}
