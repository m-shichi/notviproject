/**
 *
 */
package info.clockworksapple.android.barsearch.app;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.barmap.overlay.CenterItemizedOverlay;
import info.clockworksapple.android.barsearch.barmap.overlay.CustomItemizedOverlay;
import info.clockworksapple.android.barsearch.barmap.overlay.CustomMyLocationOverlay;
import info.clockworksapple.android.barsearch.barmap.task.LocationChangeTask;
import info.clockworksapple.android.barsearch.common.BarNaviUtil;
import info.clockworksapple.android.barsearch.relax.result.bar.Shop;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * @author ibmpck62u
 *
 */
public class BarMapActivity extends MapActivity implements LocationListener, OnGestureListener, OnDoubleTapListener {

	private GestureDetector mGDetector;
	private LocationManager mLocationManager;
	private MapView mMapView;
	private MapController mMapController;
	private BarNaviUtil barVavi;
	private Shop[] shops = null;
	private String barType = null;
	private boolean onTap = false;
	private String messeage = null;

	private Bitmap mBmp = null;
	private CenterItemizedOverlay mOverlay = null;
	private CustomMyLocationOverlay mMyOverlay = null;
	private GeoPoint mCenterPoint = null;

	private TextView mTextView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.mGDetector = new GestureDetector(this, this);

		setContentView(R.layout.bar_map);

		Intent intent = getIntent();
		barType = intent.getStringExtra("barType");
		String selectType = intent.getStringExtra("selectType");

		// mTextView = (TextView) findViewById(R.id.textview_02);

		mMapView = (MapView) findViewById(R.id.mapview);
		mMapView.setClickable(true);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setMinimumHeight(100);

		mMapController = mMapView.getController();

		mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.reddot);

		mTextView = (TextView) findViewById(R.id.textview_01);

		barVavi = new BarNaviUtil(this.getResources());
		if (selectType.equals("station")) {
			String prefecture = intent.getStringExtra("prefecture");
			// String line = intent.getStringExtra("line");
			String station = intent.getStringExtra("station");
			String lng = intent.getStringExtra("lng");
			String lat = intent.getStringExtra("lat");

			shops = barVavi.getShopByStation(prefecture, station, barType);

			// TextView textView = (TextView) findViewById(R.id.textview_01);

			if (shops.length > 0) {
				messeage = String.format(getString(R.string.str_message_01), station + "駅", String.valueOf(shops.length));
			} else {
				messeage = String.format(getString(R.string.str_message_02), station + "駅");
			}
			mTextView.setText(messeage);

			if (shops.length > 0) {

				Drawable pin = getResources().getDrawable(R.drawable.red);
				// Drawable pin =
				// getResources().getDrawable(android.R.drawable.ic_notification_overlay);
				pin.setBounds(0, 0, pin.getMinimumWidth(), pin.getMinimumHeight());
				CustomItemizedOverlay overLay = new CustomItemizedOverlay(pin, this, onTap);

				overLay.setShop(shops);
				overLay.setView(mMapView);

				for (int i = 0; i < shops.length; i++) {
					if (!shops[i].getLatWorld().equals("") && !shops[i].getLngWorld().equals("")) {
						GeoPoint barPoint = new GeoPoint((int) (new Float(shops[i].getLatWorld()) * 1E6), (int) (new Float(shops[i].getLngWorld()) * 1E6));
						overLay.addPoint(barPoint);
					}
				}
				mMapView.getOverlays().add(overLay);
			}

			mCenterPoint = new GeoPoint((int) (new Float(lat) * 1E6), (int) (new Float(lng) * 1E6));

			// Bitmap bmp = BitmapFactory.decodeResource(getResources(),
			// R.drawable.reddot);
			mOverlay = new CenterItemizedOverlay(mBmp, mCenterPoint);

			mMapView.getOverlays().add(mOverlay);

			mMapController.setZoom(17);
			mMapController.setCenter(mCenterPoint);

		} else if (selectType.equals("location")) {

			mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

			Criteria criteria = new Criteria();
			// criteria.setAccuracy(Criteria.ACCURACY_COARSE);
			// criteria.setPowerRequirement(Criteria.POWER_LOW);
			// criteria.setSpeedRequired(false);
			// criteria.setAltitudeRequired(false);
			// criteria.setBearingRequired(false);
			// criteria.setCostAllowed(false);

			String provider = mLocationManager.getBestProvider(criteria, true);
			if (null != provider && !provider.equals("")) {
				mLocationManager.requestLocationUpdates(provider, 60000, 100, this);
			} else {

			}
			mMyOverlay = new CustomMyLocationOverlay(this, mMapView);
			mMyOverlay.enableMyLocation();
			mMyOverlay.enableCompass();
			mMyOverlay.runOnFirstFix(new Runnable() {
				@Override
				public void run() {
					mMapController.animateTo(mMyOverlay.getMyLocation());
				}
			});

			mMapView.getOverlays().add(mMyOverlay);
			mMapView.invalidate();
			mMapController.setZoom(17);
		}

		// TwitterSearchTask task = new TwitterSearchTask(this);
		// task.execute();
	}

	@Override
	public void onLocationChanged(Location location) {

		mMyOverlay.setMyLocationFlag(true);

		LocationChangeTask task = new LocationChangeTask(barType, location.getLatitude(), location.getLongitude(), this, mMapView,mMapController);
		task.execute();
	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onStop() {
		super.onStop();
		if (null != mLocationManager) {
			mLocationManager.removeUpdates(this);
		}
	}

	/*
	 * (�� Javadoc)
	 *
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/*
	 * (�� Javadoc)
	 *
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.options_menu_02, menu);
		// menu.add(0, MENU_ID1, 0,
		// getString(R.string.option_close)).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		return true;
	}

	/*
	 * (�� Javadoc)
	 *
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.tweets_list) {
			Intent intent = new Intent(getApplicationContext(), TweetListActivity.class);
			intent.setAction(Intent.ACTION_VIEW);
			startActivity(intent);
		} else if (item.getItemId() == R.id.close) {
			finish();
		}
		return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		return onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mGDetector.onTouchEvent(event)) {
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {

		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}
}
