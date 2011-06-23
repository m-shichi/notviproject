/**
 *
 */
package info.clockworksapple.android.barsearch.barmap.overlay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * @author ibmpck62u
 * 
 */
public class CustomOverlayItem extends OverlayItem {

    /**
     * 
     * @param mPoint
     */
    public CustomOverlayItem(GeoPoint mPoint) {
	super(mPoint, "", "");
    }

    /**
     * @param mPoint
     * @param mTitle
     * @param mSnippet
     */
    public CustomOverlayItem(GeoPoint mPoint, String mTitle, String mSnippet) {
	super(mPoint, mTitle, mSnippet);
    }

    @Override
    public void setMarker(Drawable drawable) {
	Rect rect = drawable.getBounds();
	Canvas canvas = new Canvas();
	String text = "hogehoge";
	canvas.drawText(text, 0, text.length(), rect.left, rect.top, new Paint());
	drawable.draw(canvas);
	super.setMarker(drawable);
    }
}
