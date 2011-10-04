package jp.nextep.android.vtextviewer.adapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.bean.ContentItemBean;
import jp.nextep.android.vtextviewer.util.FileDataUtil;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContentListAdapter extends ArrayAdapter<ContentItemBean> {

	private LayoutInflater mInflater;
	private TextView mTextView;
	private ImageView mThumnail;
	private String mPackageName = "";
	private Context mContext;

	public ContentListAdapter(Context context, List<ContentItemBean> objects) {
		super(context, 0, objects);
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = context;
		this.mPackageName = context.getPackageName();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		float tmpDensity = mContext.getResources().getDisplayMetrics().density;

		convertView = mInflater.inflate(R.layout.content_list_row, null);
		final ContentItemBean item = this.getItem(position);

		if (null != item) {

			if (item.getId().equals("-")) {
				convertView.setBackgroundColor(Color.LTGRAY);
				mTextView = (TextView) convertView.findViewById(R.id.text_content);
				mTextView.setTextColor(Color.BLACK);

				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				layoutParams.height = (int) (25f * tmpDensity + 0.5f);
				layoutParams.setMargins(3, 3, 3, 3);
				mTextView.setLayoutParams(layoutParams);

				mTextView.setText(item.getPage());

			} else {
				mTextView = (TextView) convertView.findViewById(R.id.text_content);
				mTextView.setText(item.getContents());

				LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				textParams.height = (int) (40f * tmpDensity + 0.5f);
				textParams.setMargins(10, 10, 5, 10);
				mTextView.setLayoutParams(textParams);

				if (item.getImage_flag() == 1) {
					mTextView.setTextColor(Color.BLUE);
					BufferedInputStream bis = null;
					try {
						String path = FileDataUtil.getContentDirPath(mPackageName, item.getId()) + item.getImage_path();
						bis = new BufferedInputStream(new FileInputStream(path), 256 * 1024);
						mThumnail = (ImageView) convertView.findViewById(R.id.image_content);
						mThumnail.setImageBitmap(FileDataUtil.loadBitmap(bis));
						LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						imageParams.height = (int) (50f * tmpDensity + 0.5f);
						imageParams.setMargins(5, 10, 10, 10);
						mThumnail.setLayoutParams(imageParams);

					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						if (null != bis) {
							try {
								bis.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
			// mMenName = item.getPage();
		}
		return convertView;
		// return super.getView(position, convertView, parent);
	}
}
