/**
 *
 */
package jp.nextep.android.vtextviewer.dialog;

import jp.nextep.android.vtextviewer.R;
import android.app.Dialog;
import android.content.Context;

/**
 * @author ibmpck62u
 *
 */
public class CustomDialog extends Dialog {

	/**
	 * @param context
	 */
	public CustomDialog(Context context) {
		super(context, R.style.Theme_CustomDialog);
		// this.mContext = context;
	}
}
