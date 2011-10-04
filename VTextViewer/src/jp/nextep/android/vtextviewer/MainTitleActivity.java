package jp.nextep.android.vtextviewer;

import jp.nextep.android.vtextviewer.app.MediaListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainTitleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_title);

		Button startButton = (Button) findViewById(R.id.btn_start_main_title);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MediaListActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});
	}
}
