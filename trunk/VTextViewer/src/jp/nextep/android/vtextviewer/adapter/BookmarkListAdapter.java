package jp.nextep.android.vtextviewer.adapter;

import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.bean.BookmarkItemBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookmarkListAdapter extends ArrayAdapter<BookmarkItemBean> {

	private LayoutInflater mInflater;
	// private TextView mId;
	private TextView mBookmarkTitle;
	private TextView mBookmarkPlace;

	public BookmarkListAdapter(Context context, List<BookmarkItemBean> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = mInflater.inflate(R.layout.bookmark_list_row, null);
		final BookmarkItemBean item = this.getItem(position);
		if (null != item) {
			mBookmarkTitle = (TextView) convertView.findViewById(R.id.text_bookmark_title);
			mBookmarkPlace = (TextView) convertView.findViewById(R.id.text_bookmark_place);

			mBookmarkTitle.setText(item.getTitle());
			mBookmarkPlace.setText(item.getPlace());

		}
		return convertView;
		// return super.getView(position, convertView, parent);
	}

}
