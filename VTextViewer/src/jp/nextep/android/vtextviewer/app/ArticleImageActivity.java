package jp.nextep.android.vtextviewer.app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.adapter.BitmapAdapter;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class ArticleImageActivity extends Activity {

	private ImageView selectedImageView;
	private Gallery imageMapGallery;
	private BitmapAdapter bitmapAdapter;
	private Button btnClose;
	private String mMediaId;
	private String[] mImgPath;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.article_image);

		Intent intent = getIntent();
		mMediaId = intent.getStringExtra(getString(R.string.intent_media_id));
		mImgPath = intent.getStringArrayExtra(getString(R.string.intent_image_path));

		// 画像ギャラリーから選択された画像を表示するイメージビュー
		selectedImageView = (ImageView) findViewById(R.id.selectedImageView);
		// 画像ギャラリー
		imageMapGallery = (Gallery) findViewById(R.id.imageGallery);

		// ギャラリーの画像リストアダプター作成
		bitmapAdapter = new BitmapAdapter(this);
		imageMapGallery.setAdapter(bitmapAdapter);
		imageMapGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View parent, int position, long id) {
				// 選択された画像をイメージビューに表示
				Bitmap selectedBitmap = (Bitmap) bitmapAdapter.getItem(position);
				selectedImageView.setImageBitmap(selectedBitmap);
			}
		});

		// アプリで保存した画像を画像リストアダプターにロードする
		loadMapImage(bitmapAdapter);

		// 画像ロードによりデータが変更されたことを通知する
		// ※これをしないとギャラリーが表示されない
		bitmapAdapter.notifyDataSetChanged();

		btnClose = (Button) findViewById(R.id.btn_close_article_image);
		btnClose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
			}
		});
	}

	/**
	 *
	 * @param bitmapAdapter
	 */
	private void loadMapImage(BitmapAdapter bitmapAdapter) {
		try {

			for (int i = 0; i < mImgPath.length; i++) {
				BufferedInputStream bis = null;
				try {
					// bis = new BufferedInputStream(getAssets().open(mMediaId +
					// "/OEBPS/" + mImgPath[i]), 256 * 1024);
					String path = FileDataUtil.getContentDirPath(getApplicationContext().getPackageName(), mMediaId) + mImgPath[i];
					bis = new BufferedInputStream(new FileInputStream(path), 256 * 1024);

					// 画像をロードする
					Bitmap bitmap = FileDataUtil.loadBitmap(bis);
					// イメージリストアダプターに追加
					bitmapAdapter.addBitmap(bitmap);

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (bis != null) {
						bis.close();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}