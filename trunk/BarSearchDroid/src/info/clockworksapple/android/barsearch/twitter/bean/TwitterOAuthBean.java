/**
 *
 */
package info.clockworksapple.android.barsearch.twitter.bean;

import info.clockworksapple.android.barsearch.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @author ibmpck62u
 *
 */
public class TwitterOAuthBean {
	/** 呼び元 */
	private Activity mActivity;
	/**  */
	private SharedPreferences mPreferences;
	/** APP KEY */
	private String consumerKey = null;
	/** APP PASS */
	private String consumerSecret = null;
	/** USER KEY */
	private String accessToken = null;
	/** USER PASS */
	private String accessTokenSecret = null;
	/** 認証済 */
	private boolean isOAuth = false;

	/**
     *
     */
	public TwitterOAuthBean(Activity activity) {
		this.mActivity = activity;
		init();
	}

	/**
	 *
	 */
	private void init() {

		mPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
		consumerKey = mPreferences.getString(mActivity.getString(R.string.pref_consumer_key), "Pte8VFRb0RrdgHAjH32Ng");
		consumerSecret = mPreferences.getString(mActivity.getString(R.string.pref_consumer_secret), "wR1ixL7F5JOnIiNV51mvif2hf6NBnVmP0iMPzN5vc");
		accessToken = mPreferences.getString(mActivity.getString(R.string.pref_access_token), "");
		accessTokenSecret = mPreferences.getString(mActivity.getString(R.string.pref_access_token_secret), "");
		// accessToken =
		// mPreferences.getString(mActivity.getString(R.string.pref_access_token),
		// "246826030-ReKGQxeuUBkFG25QtXRIKS44HnxYM4VkDgkSl2aW");
		// accessTokenSecret =
		// mPreferences.getString(mActivity.getString(R.string.pref_access_token_secret),
		// "QhpUC4PqUMyO1lq9thCBqqgidlKJZ3uSz7LsOXNA2k");

		if (consumerKey.equals("") || consumerSecret.equals("") || accessToken.equals("") || accessTokenSecret.equals("")) {
			return;
		} else {
			isOAuth = true;
		}
	}

	/**
	 * @return consumerSecret
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @return consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 *
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		if (null == mPreferences) {
			mPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
		}
		Editor editor = mPreferences.edit();
		editor.putString(mActivity.getString(R.string.pref_access_token), accessToken);
		editor.commit();
	}

	/**
	 * @return accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 *
	 * @param accessTokenSecret
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
		if (null == mPreferences) {
			mPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
		}
		Editor editor = mPreferences.edit();
		editor.putString(mActivity.getString(R.string.pref_access_token_secret), accessTokenSecret);
		editor.commit();
	}

	/**
	 * @return accessTokenSecret
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 *
	 * @param isOAuth
	 */
	public void setOAuth(boolean isOAuth) {
		this.isOAuth = isOAuth;
	}

	/**
	 *
	 * @return
	 */
	public boolean isOAuth() {
		return this.isOAuth;
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	private String getNullValue(Object o) {
		String str = null;
		if (null == o) {
			str = "";
		} else {
			str = o.toString();
		}
		return str;
	}
}
