package jp.nextep.android.vtextviewer.app;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.component.DisplayConfig;
import jp.nextep.android.vtextviewer.dialog.VersionDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Preference extends PreferenceActivity implements OnPreferenceChangeListener {

	private String[] fontSizeEntries = null;
	private String[] blockNumEntries = null;
	// private String[] pageSendingEntries = null;
	private String[] backAndCharEntries = null;
	private String[] fontSizeEntryValues = null;
	private String[] blockNumEntryValues = null;
	// private String[] pageSendingEntryValues = null;
	private String[] backAndCharValues = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

		PackageManager pm = getPackageManager();
		PackageInfo info = null;
		try {
			info = pm.getPackageInfo("jp.nextep.android.vtextviewer", 0);
		} catch (Exception ex) {

		}
		if (DisplayConfig.checkModel()) {
			fontSizeEntries = new String[]{"大", "中", "小"};
			fontSizeEntryValues = new String[]{getString(R.string.font_size_large), getString(R.string.font_size_medium), getString(R.string.font_size_small)};
			blockNumEntries = new String[]{"1", "2", "3", "4", "5"};
			blockNumEntryValues = new String[]{"1", "2", "3", "4", "5"};
		} else {
			fontSizeEntries = new String[]{"大", "小"};
			fontSizeEntryValues = new String[]{getString(R.string.font_size_medium), getString(R.string.font_size_small)};
			blockNumEntries = new String[]{"1", "2", "3"};
			blockNumEntryValues = new String[]{"1", "2", "3"};
		}
		backAndCharEntries = getResources().getStringArray((R.array.preference_background_and_char_color_entries));
		backAndCharValues = getResources().getStringArray((R.array.preference_background_and_char_color_values));

		CharSequence csFontSize = getText(R.string.preference_font_size);
		final ListPreference lpFontSize = (ListPreference) findPreference(csFontSize);
		lpFontSize.setOnPreferenceChangeListener(this);
		lpFontSize.setEntries(fontSizeEntries);
		lpFontSize.setEntryValues(fontSizeEntryValues);

		CharSequence csBlockNum = getText(R.string.preference_block_num);
		final ListPreference lpBlockNum = (ListPreference) findPreference(csBlockNum);
		lpBlockNum.setOnPreferenceChangeListener(this);
		lpBlockNum.setEntries(blockNumEntries);
		lpBlockNum.setEntryValues(blockNumEntryValues);

		CharSequence csBackAndChar = getText(R.string.preference_back_and_char_color);
		final ListPreference lpBackAndChar = (ListPreference) findPreference(csBackAndChar);
		lpBackAndChar.setOnPreferenceChangeListener(this);
		lpBackAndChar.setEntries(backAndCharEntries);
		lpBackAndChar.setEntryValues(backAndCharValues);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String defaultFontSize = prefs.getString(getString(R.string.preference_font_size), fontSizeEntries[0]);
		String defaultBlockNum = prefs.getString(getString(R.string.preference_block_num), blockNumEntries[0]);
		String defaultBackAndChar = prefs.getString(getString(R.string.preference_back_and_char_color), backAndCharEntries[0]);

		lpFontSize.setSummary(fontSizeEntries[getArrayIndex(fontSizeEntryValues, defaultFontSize)]);
		lpBlockNum.setSummary(blockNumEntries[getArrayIndex(blockNumEntryValues, defaultBlockNum)]);
		lpBackAndChar.setSummary(backAndCharEntries[getArrayIndex(backAndCharValues, defaultBackAndChar)]);

		CharSequence csVersionDialog = getText(R.string.preference_version_dialog);
		final VersionDialog vDialog = (VersionDialog) findPreference(csVersionDialog);
		// vDialog.setOnPreferenceClickListener(this);
		vDialog.setDialogMessage(getString(R.string.msg_preference_version_dialog) + " " + info.versionName);
		vDialog.setNegativeButtonText("");
		vDialog.setPositiveButtonText("OK");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.preference_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.preference_close :
				finish();
				break;
			default :
				break;
		}
		overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
		/**
		 * if (item.getTitle().equals(DroidConstant.MENU_CLOSE)) { finish();
		 * Intent intent = new Intent();
		 * intent.setClassName(getString(R.string.package_name),
		 * getString(R.string.main_class_name)); startActivity(intent); }
		 */
		// switch (item.getItemId()) {
		// case MENU_ID1:
		// finish();
		// return true;
		// default:
		// break;
		// }
		return super.onOptionsItemSelected(item);
	}

	/**
	 *
	 * @param array
	 * @param value
	 * @return
	 */
	private int getArrayIndex(String[] array, String value) {

		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				index = i;
				break;
			}
		}
		return index;

	}

	@Override
	public boolean onPreferenceChange(android.preference.Preference preference, Object newValue) {
		if (newValue != null) {
			String key = preference.getKey();
			if (key.equals(getString(R.string.preference_font_size))) {
				preference.setSummary(fontSizeEntries[getArrayIndex(fontSizeEntryValues, (String) newValue)]);
			} else if (key.equals(getString(R.string.preference_block_num))) {
				preference.setSummary(blockNumEntries[getArrayIndex(blockNumEntryValues, (String) newValue)]);
			} else if (key.equals(getString(R.string.preference_back_and_char_color))) {
				preference.setSummary(backAndCharEntries[getArrayIndex(backAndCharValues, (String) newValue)]);
			}
			// else if (key.equals(getString(R.string.preference_page_sending)))
			// {
			// preference.setSummary(pageSendingEntries[getArrayIndex(pageSendingEntryValues,
			// (String) newValue)]);
			// //
			// preference.setSummary(pageSendingEntries[Integer.parseInt((String)
			// // newValue)]);
			// }
			return true;
		}
		return false;
	}
}
