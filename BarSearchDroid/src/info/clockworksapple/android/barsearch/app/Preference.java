package info.clockworksapple.android.barsearch.app;

import info.clockworksapple.android.barsearch.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Preference extends PreferenceActivity implements OnPreferenceChangeListener {

	// private ListPreference searchTypeListPref = null;
	// private ListPreference barTypeListPref = null;
	// private ListPreference prefectureListPref = null;

	private String[] searchTypeArray = null;
	private String[] barTypeArray = null;
	private String[] prefectureArray = null;

	// private final int MENU_ID1 = Menu.FIRST;

	@Override
	public boolean onPreferenceChange(android.preference.Preference preference, Object newValue) {
		if (newValue != null) {
			String key = preference.getKey();
			if (key.equals(getString(R.string.pref_search_type_key))) {
				preference.setSummary(searchTypeArray[Integer.parseInt((String) newValue)]);
			} else if (key.equals(getString(R.string.pref_bar_type_key))) {
				preference.setSummary(barTypeArray[Integer.parseInt((String) newValue)]);
			} else if (key.equals(getString(R.string.pref_prefecture_key))) {
				preference.setSummary(prefectureArray[Integer.parseInt((String) newValue)]);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);

		CharSequence csSearchType = getText(R.string.pref_search_type_key);
		final ListPreference searchTypeListPref = (ListPreference) findPreference(csSearchType);
		searchTypeListPref.setOnPreferenceChangeListener(this);

		CharSequence csBarType = getText(R.string.pref_bar_type_key);
		final ListPreference barTypeListPref = (ListPreference) findPreference(csBarType);
		barTypeListPref.setOnPreferenceChangeListener(this);

		CharSequence csPrefecture = getText(R.string.pref_prefecture_key);
		final ListPreference prefectureListPref = (ListPreference) findPreference(csPrefecture);
		prefectureListPref.setOnPreferenceChangeListener(this);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String defaultSearchType = prefs.getString(getString(R.string.pref_search_type_key), "0");
		String defaultBarType = prefs.getString(getString(R.string.pref_bar_type_key), "0");
		String defaultPrefecture = prefs.getString(getString(R.string.pref_prefecture_key), "0");

		searchTypeArray = getResources().getStringArray((R.array.pref_search_type_entries));
		barTypeArray = getResources().getStringArray((R.array.pref_bar_type_entries));
		prefectureArray = getResources().getStringArray((R.array.pref_prefecture_entries));
		searchTypeListPref.setSummary(searchTypeArray[Integer.parseInt(defaultSearchType)]);
		barTypeListPref.setSummary(barTypeArray[Integer.parseInt(defaultBarType)]);
		prefectureListPref.setSummary(prefectureArray[Integer.parseInt(defaultPrefecture)]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.options_menu_03, menu);
		// menu.add(0, MENU_ID1, 0,
		// getString(R.string.option_back)).setIcon(R.drawable.ic_menu_back);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.back) {
			finish();
			Intent intent = new Intent();
			intent.setClassName(getString(R.string.package_name), getString(R.string.class_name_mainmenu));
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
