/**
 *
 */
package info.clockworksapple.android.barsearch.app;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.twitter.task.TwitterSearchTask;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author ibmpck62u
 *
 */
public class TweetListActivity extends ListActivity {

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tweet_list);

		Button postButton = (Button) findViewById(R.id.button_tweet_post);
		postButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), TweetPostActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				intent.putExtra("backFlag", "1");
				startActivity(intent);
				finish();
			}
		});

		Button closeButton = (Button) findViewById(R.id.button_close);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		TwitterSearchTask task = new TwitterSearchTask(this);
		task.execute();
	}
}
