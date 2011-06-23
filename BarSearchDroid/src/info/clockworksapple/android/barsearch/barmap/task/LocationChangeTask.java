package info.clockworksapple.android.barsearch.barmap.task;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.app.BarMapActivity;
import info.clockworksapple.android.barsearch.barmap.overlay.CustomItemizedOverlay;
import info.clockworksapple.android.barsearch.common.BarNaviUtil;
import info.clockworksapple.android.barsearch.common.StringUtil;
import info.clockworksapple.android.barsearch.relax.result.bar.Shop;

import java.io.IOException;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class LocationChangeTask extends AsyncTask<Void, Integer, Shop[]> {

	private String mBarType;
	private double mLattitude;
	private double mLongitude;

	private BarNaviUtil mBarnavi;

	private Activity mActivity;
	private TextView mTextView;
	private MapView mMapView;
	private MapController mMapController;
	private ProgressDialog mProgressDialog;

	private String mText = null;

	public LocationChangeTask(String barType, double lat, double lng, Activity activity, MapView mv, MapController mc) {
		this.mActivity = activity;
		this.mBarType = barType;
		this.mLattitude = lat;
		this.mLongitude = lng;
		this.mMapView = mv;
		this.mMapController = mc;
	}

	@Override
	protected void onPreExecute() {
		// super.onPreExecute();
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage(mActivity.getString(R.string.dialog_msg_load_shops));
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();

		// mMapView = (MapView) mActivity.findViewById(R.id.mapview);
		mTextView = (TextView) mActivity.findViewById(R.id.textview_01);

		mBarnavi = new BarNaviUtil(mActivity.getResources());

		try {
			mText = StringUtil.point2address(mLattitude, mLongitude, mActivity) + mActivity.getString(R.string.str_shuuhen);
		} catch (IOException ex) {
			String jLat = mLattitude > 0 ? mActivity.getString(R.string.str_lat_north) : mActivity.getString(R.string.str_lat_south);
			String jLng = mLongitude > 0 ? mActivity.getString(R.string.str_lng_east) : mActivity.getString(R.string.str_lng_west);

			NumberFormat format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(6);

			StringBuilder sb = new StringBuilder();
			sb.append(jLat);
			sb.append(String.valueOf(format.format(Math.abs(mLattitude))));
			sb.append(mActivity.getString(R.string.str_location_unit));
			sb.append(jLng);
			sb.append(String.valueOf(format.format(Math.abs(mLongitude))));
			sb.append(mActivity.getString(R.string.str_location_unit));
			mText = sb.toString();
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO 自動生成されたメソッド・スタブ
		super.onProgressUpdate(values);
	}

	@Override
	protected Shop[] doInBackground(Void... params) {
		Shop[] shops = mBarnavi.getShopByLocation(mLattitude, mLongitude, mBarType);
		return shops;
	}

	@Override
	protected void onPostExecute(Shop[] result) {

		mProgressDialog.dismiss();

		String messeage = "";
		if (result.length > 0) {
			messeage = String.format(mActivity.getString(R.string.str_message_01), mText, String.valueOf(result.length));
		} else {
			messeage = String.format(mActivity.getString(R.string.str_message_02), mText);
		}
		mTextView.setText(messeage);

		for (int i = 1; i < mMapView.getOverlays().size(); i++) {
			mMapView.getOverlays().remove(i);
		}
		if (result.length > 0) {

			Drawable pin = mActivity.getResources().getDrawable(R.drawable.red);
			pin.setBounds(0, 0, pin.getMinimumWidth(), pin.getMinimumHeight());
			CustomItemizedOverlay overLay = new CustomItemizedOverlay(pin, (BarMapActivity) mActivity, false);

			overLay.setShop(result);
			overLay.setView(mMapView);

			for (int i = 0; i < result.length; i++) {
				if (!result[i].getLatWorld().equals("") && !result[i].getLngWorld().equals("")) {
					GeoPoint gp = new GeoPoint((int) (new Float(result[i].getLatWorld()) * 1E6), (int) (new Float(result[i].getLngWorld()) * 1E6));
					overLay.addPoint(gp);
				}
			}
			mMapView.getOverlays().add(overLay);
		}

		GeoPoint centerPoint = new GeoPoint((int) (new Float(mLattitude) * 1E6), (int) (new Float(mLongitude) * 1E6));
		mMapController.setCenter(centerPoint);

		// super.onPostExecute(result);
	}

}
