/**
 *
 */
package info.clockworksapple.android.barsearch.twitter.adapter;

import info.clockworksapple.android.barsearch.R;
import info.clockworksapple.android.barsearch.twitter.bean.TweetItemBean;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author ibmpck62u
 *
 */
public class ListAdapter extends ArrayAdapter<TweetItemBean> {

	private LayoutInflater mInflater;
	private TextView mId;
	private TextView mTime;
	private TextView mContents;

	public ListAdapter(Context context, List<TweetItemBean> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// if (null == convertView) {
		convertView = mInflater.inflate(R.layout.tweet_list_row, null);
		// }
		final TweetItemBean item = this.getItem(position);

		if (null != item) {
			mId = (TextView) convertView.findViewById(R.id.text_twitter_id);
			mTime = (TextView) convertView.findViewById(R.id.text_twitter_time);
			mContents = (TextView) convertView.findViewById(R.id.text_twitter_contents);

			mId.setText(item.getId());
			mTime.setText(item.getTime());
			mContents.setText(item.getContents());
		}
		return convertView;
		// return super.getView(position, convertView, parent);
	}
}
