package jp.nextep.android.vtextviewer.app;

import java.util.Iterator;
import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.adapter.BookmarkListAdapter;
import jp.nextep.android.vtextviewer.bean.BookmarkItemBean;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.dialog.BookmarkDialog;
import android.R.id;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BookmarkListActivity extends ListActivity {

	private static final int ITEM_ID_EDIT = 101;
	private static final int ITEM_ID_DELETE = 102;

	private int selectedIndex;
	private List<BookmarkItemBean> mItemList = null;
	private BookmarkListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmark_list);

		Button btnClose = (Button) findViewById(R.id.btn_close_bookmarkList);
		btnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), NavigateDownloadActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
			}
		});

		DBHelper dbHelper = DBHelper.getInstance(this);
		mItemList = dbHelper.selectBookmark();

		mAdapter = new BookmarkListAdapter(this, mItemList);
		this.setListAdapter(mAdapter);

		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				System.out.println("ItemClick Now !!");

				BookmarkItemBean bean = mItemList.get(index);

				Intent intent = new Intent(getApplicationContext(), NavigateDownloadActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				intent.putExtra(getString(R.string.intent_request_url), bean.getPlace());
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				System.out.println("ItemLongClick Now !!");
				selectedIndex = index;
				return false;
			}
		});

		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, ITEM_ID_EDIT, selectedIndex, getString(R.string.context_edit));
		menu.add(0, ITEM_ID_DELETE, selectedIndex, getString(R.string.context_delete));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		System.out.println(item.getOrder());

		final int itemIndex = item.getOrder();
		final BookmarkItemBean bean = mItemList.get(itemIndex);

		switch (item.getItemId()) {
			case ITEM_ID_EDIT :
				BookmarkDialog editDialog = new BookmarkDialog(this, String.valueOf(bean.getId()), bean.getTitle(), bean.getPlace(), DBHelper.SQL_UPDATE);
				editDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
						List<BookmarkItemBean> itemList = dbHelper.selectBookmark();
						if (!bean.equals(itemList.get(itemIndex))) {
							mItemList.set(itemIndex, itemList.get(itemIndex));
							mAdapter.notifyDataSetChanged();
						}
					}
				});
				editDialog.show();

				System.out.println("ほげほげ");

				break;
			case ITEM_ID_DELETE :
				Dialog deleteDialog = new AlertDialog.Builder(BookmarkListActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.title_bookmark_delete)
						.setMessage(R.string.msg_bookmark_delete).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
								String msg = "";
								if (dbHelper.deleteBookmarkById(bean.getId())) {
								} else {
									msg = String.format("ブックマーク削除失敗\nタイトル：%s\n場所：%s", bean.getTitle(), bean.getPlace());
									Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
								}
								dialog.dismiss();
							}
						}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						}).create();
				deleteDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
						List<BookmarkItemBean> itemList = dbHelper.selectBookmark();
						if (!mItemList.equals(itemList)) {
							mItemList.clear();
							for (Iterator<BookmarkItemBean> i = itemList.iterator(); i.hasNext();) {
								BookmarkItemBean bean = i.next();
								mItemList.add(bean);
							}
							mAdapter.notifyDataSetChanged();
						}
					}
				});
				deleteDialog.show();
				break;
			default :
				break;
		}
		return true;
	}
}
