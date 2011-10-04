package jp.nextep.android.vtextviewer.app;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.dialog.BookmarkDialog;
import jp.nextep.android.vtextviewer.task.DownloadEpubTask;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NavigateDownloadActivity extends Activity {

	private Toast mToast;
	private Activity mActivity;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigate_download);

		mActivity = this;

		Intent intent = getIntent();
		String requestUrl = intent.getStringExtra(getString(R.string.intent_request_url));

		// this.mActivity = this;
		final WebView wv = (WebView) findViewById(R.id.webview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setBuiltInZoomControls(true);

		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				boolean bool = false;
				String msg = "";
				if (url.indexOf(".epub") > 0) {
					msg = String.format("It's EPUB !! [ %s ]", url);
					bool = true;

					String packageName = getApplicationContext().getPackageName();

					String tempPath = FileDataUtil.getTempFilePath(packageName);

					DownloadEpubTask task = new DownloadEpubTask(mActivity, url, tempPath, packageName);
					task.execute();

				} else {
					msg = String.format("not EPUB !! [ %s ]", url);

				}
				mToast = Toast.makeText(NavigateDownloadActivity.this, msg, Toast.LENGTH_SHORT);
				mToast.show();

				return bool;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});

		Button startBtn = (Button) findViewById(R.id.btn_search_go_url);
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				EditText et = (EditText) findViewById(R.id.edittext_url);
				SpannableStringBuilder sb = (SpannableStringBuilder) et.getText();

				mToast = Toast.makeText(NavigateDownloadActivity.this, sb.toString(), Toast.LENGTH_SHORT);
				mToast.show();

				wv.loadUrl(sb.toString());
			}
		});

		Button closeBtn = (Button) findViewById(R.id.btn_close_navi_dl);
		closeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MediaListActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
			}
		});

		EditText edittextUrl = (EditText) findViewById(R.id.edittext_url);
		if (null != requestUrl && !requestUrl.equals("")) {
			edittextUrl.setText(requestUrl);
			wv.loadUrl(requestUrl);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.navigate_download_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.bookmark_new :

				EditText editTextUrl = (EditText) findViewById(R.id.edittext_url);
				SpannableStringBuilder sb = (SpannableStringBuilder) editTextUrl.getText();

				BookmarkDialog dialog = new BookmarkDialog(this, sb.toString(), DBHelper.SQL_INSERT);
				dialog.show();

				break;
			case R.id.bookmark_manager :
			default :
				// bookmark Activity
				intent = new Intent(getApplicationContext(), BookmarkListActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	// @Override
	// protected Dialog onCreateDialog(int id) {
	// switch (id) {
	// case R.id.bookmark_new :
	// // ダイアログの作成(AlertDialog.Builder)
	//
	// return new
	// AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info).setTitle(R.string.title_bookmark).setView(mInputView)
	// .setPositiveButton(R.string.button_ok, new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int whichButton) {
	// // bookmarkテーブル
	// EditText editTextName = (EditText)
	// mInputView.findViewById(R.id.edittext_bookmark_name);
	// SpannableStringBuilder sbName = (SpannableStringBuilder)
	// editTextName.getText();
	// SpannableStringBuilder sbPlace = (SpannableStringBuilder)
	// mRequestUrl.getText();
	//
	// if (sbName.toString().equals("") || sbPlace.toString().equals("")) {
	// mToast = Toast.makeText(NavigateDownloadActivity.this,
	// getString(R.string.msg_err_name_url), Toast.LENGTH_SHORT);
	// mToast.show();
	// } else {
	//
	// }
	// }
	// }).setNegativeButton(R.string.button_cancel, new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int whichButton) {
	// // dialog;
	// dismissDialog(R.id.bookmark_new);
	// }
	// }).create();
	// default :
	// break;
	// }
	// return null;
	// }
}