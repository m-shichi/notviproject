package jp.web.sync.android.adapter;

import java.util.List;

import jp.web.sync.android.R;
import jp.web.sync.android.bean.SelfGroupInfoBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GroupListAdapter extends ArrayAdapter<SelfGroupInfoBean> {

	private LayoutInflater mInflater;
	private TextView mGroupName;

	public GroupListAdapter(Context context, List<SelfGroupInfoBean> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = mInflater.inflate(R.layout.group_row, null);
		final SelfGroupInfoBean item = this.getItem(position);
		if (null != item) {
			mGroupName = (TextView) convertView.findViewById(R.id.text_group_name);

			mGroupName.setText(item.getGroupName());
		}
		return convertView;
	}

}
