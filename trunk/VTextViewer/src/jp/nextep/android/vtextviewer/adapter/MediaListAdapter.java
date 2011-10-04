package jp.nextep.android.vtextviewer.adapter;

import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.bean.MediaItemBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MediaListAdapter extends ArrayAdapter<MediaItemBean> {

	private LayoutInflater mInflater;
	// private TextView mId;
	private TextView mName;

	public MediaListAdapter(Context context, List<MediaItemBean> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = mInflater.inflate(R.layout.media_list_row, null);
		final MediaItemBean item = this.getItem(position);
		if (null != item) {
			mName = (TextView) convertView.findViewById(R.id.text_media_name);
			mName.setText(item.getName());
		}
		return convertView;
		// return super.getView(position, convertView, parent);
	}

}
