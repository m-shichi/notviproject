package info.clockworksapple.android.barsearch.twitter.task;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.twitter.adapter.ListAdapter;
import info.clockworksapple.android.barsearch.twitter.bean.TweetItemBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class TwitterSearchTask extends AsyncTask<Void, Integer, List<Tweet>> {

	// private final String SEARCH_QUERY = "#barsearchdroid";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
	private ListActivity mListActivity;
	private ProgressDialog mProgressDialog;

	public TwitterSearchTask(Activity activity) {
		this.mListActivity = (ListActivity) activity;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mListActivity);
		// mProgressDialog.setTitle("load Tweets...");
		mProgressDialog.setMessage(mListActivity.getString(R.string.dialog_msg_load_tweets));
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected List<Tweet> doInBackground(Void... params) {
		List<Tweet> result = doSearchTweets();
		return result;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(List<Tweet> result) {
		// super.onPostExecute(result);
		mProgressDialog.dismiss();

		List<TweetItemBean> list = new ArrayList<TweetItemBean>();
		for (Tweet tweet : result) {
			TweetItemBean item = new TweetItemBean();
			item.setId("@" + tweet.getFromUser() + "さん");
			item.setTime(dateFormat.format(tweet.getCreatedAt()));
			item.setContents(tweet.getText().substring(16));
			list.add(item);
		}
		ListAdapter adapter = new ListAdapter(mListActivity, list);
		mListActivity.setListAdapter(adapter);
		// listView.setAdapter(adapter);
	}

	/**
	 *
	 * @return
	 */
	public List<Tweet> doSearchTweets() {

		List<Tweet> tweets = null;
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			QueryResult result = twitter.search(new Query(mListActivity.getString(R.string.tweet_hash_tag)));
			tweets = result.getTweets();
			// for (Tweet tweet : tweets) {
			// System.out.println("@" + tweet.getFromUser() + " - " +
			// tweet.getText());
			// }
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return tweets;
	}
}
