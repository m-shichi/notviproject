package info.clockworksapple.android.barsearch.barmap.overlay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class CenterItemizedOverlay extends Overlay {

	private Bitmap mBmp = null;;
	private GeoPoint mGeopoint = null;;

	// public CenterItemizedOverlay(Context context, MapView mapView, Bitmap
	// bmp, GeoPoint geopoint) {
	// super(context, mapView);
	// this.mBmp = bmp;
	// this.mGeopoint = geopoint;
	// }

	public CenterItemizedOverlay(Bitmap bmp, GeoPoint gp) {
		this.mBmp = bmp;
		this.mGeopoint = gp;
	}

	// @Override
	// public synchronized boolean draw(Canvas canvas, MapView mapView, boolean
	// shadow, long when) {
	// boolean ret = super.draw(canvas, mapView, shadow, when);
	//
	// Projection projection = mapView.getProjection();
	// Point p = projection.toPixels(mGeopoint, null);
	// canvas.drawBitmap(mBmp, p.x - 36, p.y - 36, null);
	//
	// return ret;
	// }

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();// Mapと画面の位置を計算するオブジェクト
		Point p = projection.toPixels(mGeopoint, null); // ロケーションから、表示する位置を計算する
		canvas.drawBitmap(mBmp, p.x - 36, p.y - 36, null); // 表示する場所へ画像を配置する。
	}
}
