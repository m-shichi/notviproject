package info.clockworksapple.android.barsearch.twitter.task;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.app.ShowBinCodeActivity;
import twitter4j.http.RequestToken;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

public class TwitterGetPinTask extends AsyncTask<Void, Integer, String> {

	private Activity mActivity;
	private RequestToken mRequestToken;
	private ProgressDialog mProgressDialog;

	public TwitterGetPinTask(Activity activity, RequestToken requestToken) {
		this.mActivity = activity;
		this.mRequestToken = requestToken;
	}

	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mActivity);
		// mProgressDialog.setTitle("load URL...");
		mProgressDialog.setMessage(mActivity.getString(R.string.dialog_msg_get_url));
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected String doInBackground(Void... params) {
		return getBinUrl();
	}

	@Override
	protected void onPostExecute(String result) {
		// super.onPostExecute(result);

		mProgressDialog.dismiss();

		Intent intent = new Intent(mActivity.getApplicationContext(), ShowBinCodeActivity.class);
		intent.putExtra("requestURL", result);
		intent.setAction(Intent.ACTION_VIEW);
		mActivity.startActivity(intent);
	}

	/**
	 *
	 * @return
	 */
	private String getBinUrl() {
		String url = null;
		// Twitter twitter = new TwitterFactory().getInstance();
		// twitter.setOAuthConsumer(mOAuth.getConsumerKey(),
		// mOAuth.getConsumerSecret());
		// RequestToken requestToken = null;
		// try {
		// requestToken = twitter.getOAuthRequestToken();
		// } catch (TwitterException e) {
		// e.printStackTrace();
		// }
		if (null != mRequestToken) {
			url = mRequestToken.getAuthorizationURL();
		}
		return url;
	}
}
