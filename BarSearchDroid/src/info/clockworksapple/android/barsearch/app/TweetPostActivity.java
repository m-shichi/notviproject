package info.clockworksapple.android.barsearch.app;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.common.StringUtil;
import info.clockworksapple.android.barsearch.dialog.OAuthEntryDialog;
import info.clockworksapple.android.barsearch.twitter.bean.TwitterOAuthBean;
import info.clockworksapple.android.barsearch.twitter.task.TwitterEntryPinTask;
import info.clockworksapple.android.barsearch.twitter.task.TwitterGetPinTask;
import info.clockworksapple.android.barsearch.twitter.task.TwitterUpdateTask;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TweetPostActivity extends Activity {

	private static final int TWEET_MAX_LENGTH = 140;

	private Activity mActivity;
	private List<InputFilter> mFilterList = new ArrayList<InputFilter>();
	private String mHashTag;
	private TwitterOAuthBean mOAuth;
	private Twitter mTwitter;
	private RequestToken mRequestToken;

	public TweetPostActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tweet_post);

		this.mHashTag = getString(R.string.tweet_hash_tag);
		InputFilter filter1 = new InputFilter.LengthFilter(TWEET_MAX_LENGTH - mHashTag.length());
		this.mFilterList.add(filter1);

		Intent intent = getIntent();
		final String shopName = StringUtil.getNullValue(intent.getStringExtra("shopName"));
		final String urlPc = StringUtil.getNullValue(intent.getStringExtra("urlPc"));
		final String backFlag = StringUtil.getNullValue(intent.getStringExtra("backFlag"));

		final EditText editText = (EditText) findViewById(R.id.edit_text);
		editText.setHeight(250);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		editText.setGravity(Gravity.TOP);
		editText.setFilters(mFilterList.toArray(new InputFilter[0]));
		editText.setHint(String.format(getString(R.string.tweet_post_hint), String.valueOf(TWEET_MAX_LENGTH - (mHashTag.length() + 1))));
		if (!shopName.equals("")) {
			editText.append(String.format("[%s]%s", shopName, System.getProperty("line.separator")));
		}
		if (!urlPc.equals("")) {
			editText.append(String.format("%s%s", urlPc, System.getProperty("line.separator")));
		}

		mActivity = this;
		mOAuth = new TwitterOAuthBean(this);

		// final AlertDialog.Builder dialogBuilder = new
		// AlertDialog.Builder(this);

		try {
			mTwitter = new TwitterFactory().getInstance();
			mTwitter.setOAuthConsumer(mOAuth.getConsumerKey(), mOAuth.getConsumerSecret());
			mRequestToken = mTwitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		Button postButton = (Button) findViewById(R.id.button_tweet_post);
		postButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOAuth.isOAuth()) {
					// POST処理
					String status = String.format("%s %s", mHashTag, editText.getText().toString());
					TwitterUpdateTask updateTask = new TwitterUpdateTask(mActivity, mOAuth, status, backFlag);
					updateTask.execute();
				} else {
					// OAuth認証処理
					final OAuthEntryDialog dialog = new OAuthEntryDialog(mActivity);
					dialog.setContentView(R.layout.oauth_entry_dialog);

					dialog.setTitle(R.string.dialog_title_oauth_entry);
					final Button getButton = (Button) dialog.findViewById(R.id.button_get_bin_code);
					getButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View view) {
							// 認証ページURL取得
							TwitterGetPinTask getBinTask = new TwitterGetPinTask(mActivity, mRequestToken);
							getBinTask.execute();
						}
					});

					final Button entryButton = (Button) dialog.findViewById(R.id.button_entry_bin_code);
					entryButton.setEnabled(false);
					entryButton.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View view) {
							TwitterEntryPinTask entryBinTask = new TwitterEntryPinTask(mActivity, dialog, mOAuth, mTwitter, mRequestToken);
							entryBinTask.execute();
						}
					});

					final EditText editText = (EditText) dialog.findViewById(R.id.edit_text_bin_code);
					TextWatcher watcher = new TextWatcher() {
						@Override
						public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
							if (editText.getText().toString().equals("")) {
								getButton.setEnabled(true);
								entryButton.setEnabled(false);
							} else {
								getButton.setEnabled(false);
								entryButton.setEnabled(true);
							}
						}

						@Override
						public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {
						}

						@Override
						public void afterTextChanged(Editable editable) {
						}
					};
					editText.addTextChangedListener(watcher);

					final Button closeButton = (Button) dialog.findViewById(R.id.button_input_oauth_close);
					closeButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View view) {
							dialog.dismiss();
						}
					});

					dialog.show();
				}
			}
		});

		Button cancelButton = (Button) findViewById(R.id.button_cancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), TweetListActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		});
	}
}
