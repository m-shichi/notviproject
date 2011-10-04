package jp.nextep.android.vtextviewer.adapter;

import java.util.ArrayList;
import java.util.List;

import jp.nextep.android.vtextviewer.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class BitmapAdapter extends BaseAdapter {

	private Context mContext;
	// ロードされた画像ファイルを保持するリスト
	private List<Bitmap> imageItems;
	private int galleryItemBackground;

	/**
	 *
	 * @param context
	 */
	public BitmapAdapter(Context context) {
		this.mContext = context;
		TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.myGallery);
		galleryItemBackground = typedArray.getResourceId(R.styleable.myGallery_android_galleryItemBackground, 0);
		typedArray.recycle();
		imageItems = new ArrayList<Bitmap>();
	}

	// メインクラスでロードした画像イメージを追加します
	public void addBitmap(Bitmap image) {
		imageItems.add(image);
	}

	// 不要になった画像イメージをリストから削除します
	public void deleteBitmap(int index) {
		imageItems.remove(index);
	}

	// クリーンアップ
	public void clear() {
		imageItems.clear();
	}

	public int getCount() {
		return imageItems.size();
	}

	public Object getItem(int position) {
		return imageItems.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		// ギャラリーに画像を表示するためのイメージビュー作成
		ImageView imageView = new ImageView(mContext);

		// 表示する画像
		Bitmap bitmap = (Bitmap) getItem(position);
		imageView.setImageBitmap(bitmap);

		// イメージビューでの表示(フィット[リサイズして]中央揃え)
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new Gallery.LayoutParams(100, 120));

		// イメージビューの背景
		imageView.setBackgroundResource(galleryItemBackground);
		return imageView;
	}

}
