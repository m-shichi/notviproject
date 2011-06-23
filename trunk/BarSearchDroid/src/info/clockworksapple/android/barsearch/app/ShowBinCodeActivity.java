/**
 *
 */
package info.clockworksapple.android.barsearch.app;

import info.clockworksapple.android.barsearch.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

/**
 * @author m-shichi
 *
 */
public class ShowBinCodeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show_bin_code);

		Intent intent = getIntent();
		String requestURL = intent.getStringExtra("requestURL");

		WebView webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(requestURL);

		Button closeButton = (Button) findViewById(R.id.button_webview_close);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

}
