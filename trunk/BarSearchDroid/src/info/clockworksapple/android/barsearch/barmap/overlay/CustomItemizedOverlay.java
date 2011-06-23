/**
 *
 */
package info.clockworksapple.android.barsearch.barmap.overlay;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.app.BarMapActivity;
import info.clockworksapple.android.barsearch.app.TweetPostActivity;
import info.clockworksapple.android.barsearch.common.BarDetail;
import info.clockworksapple.android.barsearch.common.StringUtil;
import info.clockworksapple.android.barsearch.dialog.CustomDialog;
import info.clockworksapple.android.barsearch.relax.result.bar.Shop;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * @author ibmpck62u
 *
 */
public class CustomItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private List<GeoPoint> points = new ArrayList<GeoPoint>();
	private Shop[] shops = null;
	private Shop mCurrentShop = null;
	// private Context mActivity = null;
	// private Dialog dialog = null;
	private int[] mLocation;
	private MapView mMapView;
	private boolean onTap;
	private Activity mActivity;
	private TextView mTextView;

	/**
	 * @param defaultMarker
	 * @param showBarMap
	 */
	public CustomItemizedOverlay(Drawable defaultMarker, BarMapActivity showBarMap, boolean onTap) {
		super(boundCenterBottom(defaultMarker));
		this.mActivity = showBarMap;
		// this.activity = showBarMap;
		this.onTap = onTap;
		this.mTextView = (TextView) mActivity.findViewById(R.id.textview_01);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected CustomOverlayItem createItem(int i) {
		GeoPoint point = points.get(i);
		CustomOverlayItem item = new CustomOverlayItem(point, "aaaaa", "bbbbb");
		return item;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return points.size();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {

		mLocation = new int[2];
		mLocation[0] = (int) event.getX();
		mLocation[1] = (int) event.getY();
		System.out.println("Location_X:" + mLocation[0] + " Location_Y:" + mLocation[1]);

		return super.onTouchEvent(event, mapView);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see com.google.android.maps.ItemizedOverlay#onTap(int)
	 */
	@Override
	public boolean onTap(GeoPoint geopoint, MapView mapview) {
		return super.onTap(geopoint, mapview);
	}

	@Override
	protected boolean onTap(int i) {

		if (onTap) {
			return true;
		}

		mCurrentShop = shops[i];

		mTextView = (TextView) mActivity.findViewById(R.id.textview_01);
		SpannableString spannableString = new SpannableString(StringUtil.zenkakuAlphabetToHankaku(mCurrentShop.getNameAsString()));
		spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
		String text = String.format(mActivity.getString(R.string.str_message_03), spannableString);
		mTextView.setText(text);

		final ScrollView sv = (ScrollView) mActivity.findViewById(R.id.scrollview_01);
		sv.post(new Runnable() {
			@Override
			public void run() {
				sv.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		ActionItem item = new ActionItem();
		item.setTitle(mActivity.getString(R.string.str_detail));
		// item.setTitle(mCurrentShop.getNameAsString());
		item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final Dialog dialog = new CustomDialog(mActivity);

				dialog.setContentView(R.layout.custom_dialog);
				dialog.setTitle(mCurrentShop.getNameAsString());

				TextView text = (TextView) dialog.findViewById(R.id.text);
				text.setTextSize(13f);
				text.setText(editText(mCurrentShop));

				TextView textPhone = (TextView) dialog.findViewById(R.id.text_phone);
				BarDetail bd = new BarDetail(mCurrentShop.getUrlMobileAsString());
				textPhone.setText(mActivity.getString(R.string.str_overlay_text_tel) + bd.getPhoneNo());

				TextView textUri = (TextView) dialog.findViewById(R.id.text_url);
				textUri.setText(mActivity.getString(R.string.str_overlay_text_detail) + mCurrentShop.getUrlPcAsString());

				ImageView image = (ImageView) dialog.findViewById(R.id.image);
				image.setImageBitmap(getBitmap(mCurrentShop.getUrlPhotoK1AsString()));

				TextView textCaution = (TextView) dialog.findViewById(R.id.text_caution);
				textCaution.setText(mActivity.getString(R.string.str_overlay_text_credit));

				Button mButton = (Button) dialog.findViewById(R.id.button_dialog_close);
				mButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						dialog.dismiss();
						onTap = false;
					}
				});
				mButton.setVisibility(Button.VISIBLE);

				Button postButton = (Button) dialog.findViewById(R.id.button_dialog_tweet_post);
				postButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(mActivity.getApplicationContext(), TweetPostActivity.class);
						intent.setAction(Intent.ACTION_VIEW);
						intent.putExtra("shopName", mCurrentShop.getNameAsString());
						intent.putExtra("urlPc", mCurrentShop.getUrlPcAsString());
						intent.putExtra("backFlag", "2");
						mActivity.startActivity(intent);
					}
				});
				postButton.setVisibility(Button.VISIBLE);

				dialog.show();
			}
		});

		LinearLayout textLayout = (LinearLayout) mActivity.findViewById(R.id.textLayout);
		QuickAction qa = new QuickAction(mMapView);
		qa.addActionItem(item);
		qa.setAnimStyle(QuickAction.ANIM_AUTO);
		qa.setLocation(mLocation);
		qa.setViewHeight(textLayout.getMeasuredHeight());
		qa.show();

		return true;
	}

	/**
	 *
	 * @param point
	 */
	public void addPoint(GeoPoint point) {
		this.points.add(point);
		populate();
	}

	/**
	 *
	 */
	public void clearPoint() {
		this.points.clear();
		populate();
	}

	/**
	 * @param shops
	 */
	public void setShop(Shop[] shops) {
		this.shops = shops;
	}

	/**
	 *
	 * @param uri
	 * @return
	 */
	public Bitmap getBitmap(String uri) {
		Bitmap bitmap = null;
		HttpURLConnection huc = null;
		InputStream is = null;
		try {
			huc = ((HttpURLConnection) (new URL(uri).openConnection()));

			huc.setDoInput(true);
			huc.connect();

			is = huc.getInputStream();

			bitmap = BitmapFactory.decodeStream(is);

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	/**
	 * @param shop
	 * @return
	 */
	private String editText(Shop shop) {

		StringBuilder sb = new StringBuilder();

		if (!shop.getNameAsString().equals("")) {
			sb.append(mActivity.getString(R.string.str_overlay_text_s_kakko));
			sb.append(shop.getNameAsString());
			sb.append(mActivity.getString(R.string.str_overlay_text_c_kakko));
		}

		if (!shop.getTypeAsString().equals("")) {
			sb.append(mActivity.getString(R.string.str_overlay_text_s_kakko));
			sb.append(shop.getTypeAsString());
			sb.append(mActivity.getString(R.string.str_overlay_text_c_kakko));
		}

		if (!shop.getAddressAsString().equals("")) {
			sb.append(mActivity.getString(R.string.str_overlay_text_s_kakko));
			sb.append(shop.getAddressAsString());
			sb.append(mActivity.getString(R.string.str_overlay_text_c_kakko));
		}

		if (!shop.getOpenAsString().equals("")) {
			sb.append(mActivity.getString(R.string.str_overlay_text_s_kakko));
			String open = "";
			char[] ch = shop.getOpenAsString().toCharArray();
			for (int i = 0; i < ch.length; i++) {
				String hexStr = Integer.toHexString(ch[i]);
				if (hexStr.equals("301c")) {
					open += mActivity.getString(R.string.str_overlay_text_kara);
				} else {
					open += ch[i];
				}
			}
			sb.append(open);
			sb.append(mActivity.getString(R.string.str_overlay_text_c_kakko));
		}

		if (!shop.getBudgetAsString().equals("")) {
			sb.append(mActivity.getString(R.string.str_overlay_text_s_kakko));
			sb.append(shop.getBudgetAsString());
			sb.append(mActivity.getString(R.string.str_overlay_text_c_kakko));
		}

		return sb.toString();
	}

	/**
	 *
	 * @param mv
	 */
	public void setView(MapView mv) {
		this.mMapView = mv;
	}
}
