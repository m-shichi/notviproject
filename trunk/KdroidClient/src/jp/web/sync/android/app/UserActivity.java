/**
 *
 */
package jp.web.sync.android.app;

import jp.web.sync.android.R;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.android.db.DBHelper;
import jp.web.sync.android.task.EditUserTask;
import jp.web.sync.android.util.Constant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author m-shichi
 *
 */
public class UserActivity extends Activity {

	private Activity mActivity = null;

	public UserActivity() {
		mActivity = this;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);

		final Button btnNext = (Button) findViewById(R.id.btn_next);

		Intent intent = getIntent();
		final int firstFlag = intent.getIntExtra(Constant.INTENT_FIRST_FLAG, 0);
		if (firstFlag == 1) {
			btnNext.setText(getString(R.string.jp_next));
		} else {
			btnNext.setText(getString(R.string.jp_close));
		}
		final int userId = intent.getIntExtra(Constant.INTENT_USER_ID, 0);

		DBHelper db = DBHelper.getInstance(this);
		SelfInfoBean bean = db.selectSelfInfoById(userId);

		final EditText editTextMailAddr = (EditText) findViewById(R.id.edit_text_mail_addr);
		final EditText editTextPassword = (EditText) findViewById(R.id.edit_text_password);
		final EditText editTextHandleName = (EditText) findViewById(R.id.edit_text_handle_name);
		final EditText editTextMessage = (EditText) findViewById(R.id.edit_text_message);

		final String defMailAddr = bean.getMailAddr();
		final String defPassword = bean.getPassword();
		final String defHandleName = bean.getHandleName();
		final String defMessage = bean.getMessage();

		editTextMailAddr.setText(defMailAddr);
		editTextPassword.setText(defPassword);
		editTextHandleName.setText(defHandleName);
		editTextMessage.setText(defMessage);
		editTextMessage.setHeight(100);

		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {



				SelfInfoBean selfInfo = new SelfInfoBean();
				selfInfo.setId(userId);
				selfInfo.setMailAddr(editTextMailAddr.getText().toString());
				selfInfo.setPassword(editTextPassword.getText().toString());
				selfInfo.setHandleName(editTextHandleName.getText().toString());
				selfInfo.setMessage(editTextMessage.getText().toString());

				EditUserTask task = new EditUserTask(selfInfo, mActivity, firstFlag);
				task.execute();
			}
		});
	}
}
