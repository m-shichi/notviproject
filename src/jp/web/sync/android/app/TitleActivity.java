package jp.web.sync.android.app;

import jp.web.sync.android.R;
import jp.web.sync.android.bean.SelfGroupInfoBean;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.task.LoginTask;
import jp.web.sync.android.util.CustomStringUtils;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TitleActivity extends Activity {

	private SelfInfoBean mSelfInfo = null;
	private SelfGroupInfoBean[] mSelfGroupInfos = null;
	private Activity mActivity = null;

	private int firstFlag = 0;

	public TitleActivity() {
		mActivity = this;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title);

		final DBHelper dbHelper = DBHelper.getInstance(this);

		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String defAccount = prefs.getString(getString(R.string.pref_key_account), "");
		String defPassword = prefs.getString(getString(R.string.pref_key_password), "");

		if (defAccount.equals("")) {
			firstFlag = 1;
		}

		final EditText txtMailAddr = (EditText) findViewById(R.id.text_mail_addr);
		final EditText txtPassword = (EditText) findViewById(R.id.text_password);

		txtMailAddr.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				txtMailAddr.setError(null);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
		txtPassword.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				txtPassword.setError(null);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});

		if (!CustomStringUtils.nullValue(defAccount).equals("")) {
			txtMailAddr.setText(defAccount);
			mSelfInfo = dbHelper.selectSelfInfoByAddr(defAccount);
		}
		if (!CustomStringUtils.nullValue(defPassword).equals("")) {
			txtPassword.setText(defPassword);
		}

		Button btnStart = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String mailAddr = txtMailAddr.getText().toString();
				String password = txtPassword.getText().toString();

				// 入力チェック
				if (mailAddr.length() == 0) {
					txtMailAddr.setError(getString(R.string.err_msg_no_input));
					return;
				}
				if (!CustomStringUtils.checkMailAddr(mailAddr)) {
					txtMailAddr.setError(getString(R.string.err_msg_mail_addr));
					return;
				}
				if (password.length() == 0) {
					txtPassword.setError(getString(R.string.err_msg_no_input));
					return;
				}

				SelfInfoBean selfInfo = dbHelper.selectSelfInfoByAddr(mailAddr);
				int opType;
				if (null == selfInfo) {
					opType = 1;
					selfInfo = new SelfInfoBean();
					selfInfo.setMailAddr(mailAddr);
					selfInfo.setPassword(password);
				} else {
					opType = 2;
				}

				LoginTask task = new LoginTask(mActivity, opType, firstFlag, selfInfo);
				task.execute();
			}
		});
	}
}