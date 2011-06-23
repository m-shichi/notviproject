package info.clockworksapple.android.barsearch;

import info.clockworksapple.android.barsearch.app.MainMenuActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainTitleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_title);

		TextView textView1 = (TextView) findViewById(R.id.text_view_powered);
		textView1.append(getString(R.string.str_credit_01));

		TextView textView2 = (TextView) findViewById(R.id.text_view_credit);
		MovementMethod movementmethod = LinkMovementMethod.getInstance();
		textView2.setMovementMethod(movementmethod);
		String html = "<a href='http://webapi.suntory.co.jp/barnavidocs/'><span style='color: #0000ff; text-decoration: underline'>Bar-Navi Web API</span></a>";
		CharSequence source = Html.fromHtml(html);
		textView2.append(source);
		textView2.append(getString(R.string.str_credit_02));
		textView2.append(getString(R.string.str_credit_03));

		Button startButton = (Button) findViewById(R.id.button_start);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				finish();
			}
		});

	}
}
