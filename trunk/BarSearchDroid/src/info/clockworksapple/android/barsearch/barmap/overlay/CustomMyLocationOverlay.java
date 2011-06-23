package info.clockworksapple.android.barsearch.barmap.overlay;

import android.content.Context;
import android.graphics.Canvas;

import com.google.android.maps.MapView;

public class CustomMyLocationOverlay extends FixedMyLocationOverlay {

	public boolean mLocationFlag = false;
	private MapView mMapView;

	public CustomMyLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		this.mMapView = mapView;
	}

	public void setMyLocationFlag(boolean flag) {
		mLocationFlag = flag;
	}

	@Override
	public synchronized boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		boolean ret = super.draw(canvas, mapView, shadow, when);
		try {
			if (mLocationFlag) {
				drawMyLocation(canvas, mMapView, getLastFix(), getMyLocation(), 5000);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

}
