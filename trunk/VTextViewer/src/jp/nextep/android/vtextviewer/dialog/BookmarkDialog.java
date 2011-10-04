package jp.nextep.android.vtextviewer.dialog;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.db.DBHelper;
import android.app.Dialog;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookmarkDialog extends Dialog implements OnClickListener {

	private static final String TAG = "BookmarkDialog";

	private String mIndex = "";;
	private String mTitle = "";;
	private String mUrl = "";;
	private Context mContext;
	private int mDBFlag;

	public BookmarkDialog(Context context) {
		super(context, R.style.Theme_CustomDialog);
	}

	public BookmarkDialog(Context context, String url, int dbFlag) {
		super(context, R.style.Theme_CustomDialog);
		this.mContext = context;
		this.mUrl = url;
		this.mDBFlag = dbFlag;
	}

	public BookmarkDialog(Context context, String index, String title, String url, int dbFlag) {
		super(context, R.style.Theme_CustomDialog);
		this.mContext = context;

		this.mIndex = index;
		this.mTitle = title;
		this.mUrl = url;
		this.mDBFlag = dbFlag;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_LEFT_ICON);

		setContentView(R.layout.bookmark_dialog);

		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_info);
		setTitle(R.string.title_bookmark);

		final Button btnOk = (Button) findViewById(R.id.btn_ok);
		Button btnCancel = (Button) findViewById(R.id.btn_cancel);

		btnOk.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		final EditText etBookmarkName = (EditText) findViewById(R.id.edittext_bookmark_name);
		final EditText etBookmarkPlace = (EditText) findViewById(R.id.edittext_bookmark_place);

		TextWatcher watcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {
			}
			@Override
			public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
				if (etBookmarkName.getText().toString().equals("") || etBookmarkPlace.getText().toString().equals("")) {
					btnOk.setEnabled(false);
				} else {
					btnOk.setEnabled(true);
				}
			}
			@Override
			public void afterTextChanged(Editable editable) {
			}
		};
		etBookmarkName.addTextChangedListener(watcher);
		etBookmarkPlace.addTextChangedListener(watcher);

		etBookmarkName.setText(mTitle);
		etBookmarkPlace.setText(mUrl);
	}
	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_ok :
				// bookmark登録
				DBHelper dbHelper = DBHelper.getInstance(mContext);

				String bookmarkIndex = null;
				String bookmarkTitle = ((EditText) findViewById(R.id.edittext_bookmark_name)).getText().toString();
				String bookmarkPlace = ((EditText) findViewById(R.id.edittext_bookmark_place)).getText().toString();

				String msg = "";
				if (mDBFlag == DBHelper.SQL_INSERT) {
					try {
						dbHelper.insertBookmark(bookmarkTitle, bookmarkPlace);
					} catch (SQLException ex) {
						msg = String.format("ブックマーク登録失敗\nタイトル：%s\n場所：%s", bookmarkTitle, bookmarkPlace);
						Log.e(TAG, msg, ex.getCause());
					}
				} else if (mDBFlag == DBHelper.SQL_UPDATE) {
					bookmarkIndex = mIndex;
					try {
						dbHelper.updateBookmarkById(bookmarkTitle, bookmarkPlace, bookmarkIndex);
					} catch (SQLException ex) {
						msg = String.format("ブックマーク更新失敗\nタイトル：%s\n場所：%s", bookmarkTitle, bookmarkPlace);
						Log.e(TAG, msg, ex.getCause());
					}
				}
				if (!msg.equals("")) {
					Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_cancel :
				break;
		}
		dismiss();
	}
}
