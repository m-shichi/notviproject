package jp.nextep.android.vtextviewer.app;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jp.nextep.android.vtextviewer.R;
import jp.nextep.android.vtextviewer.adapter.MediaListAdapter;
import jp.nextep.android.vtextviewer.bean.MediaItemBean;
import jp.nextep.android.vtextviewer.db.DBHelper;
import jp.nextep.android.vtextviewer.util.FileDataUtil;

import org.apache.commons.io.FileUtils;

import android.R.id;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class MediaListActivity extends ListActivity {

	private static final String TAG = "MediaListActivity";
	private static final int ITEM_ID_DELETE = 102;

	private List<MediaItemBean> mItemList;
	private MediaListAdapter mAdapter;
	private int selectedIndex;
	private String mMediaListIndex = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.media_list);

		Intent intent = getIntent();
		mMediaListIndex = intent.getStringExtra(getString(R.string.intent_media_list_index));

		/**
		 * Button btnClose = (Button) findViewById(R.id.btn_close_media_list);
		 * btnClose.setOnClickListener(new OnClickListener() {
		 *
		 * @Override public void onClick(View v) { showDialog(0); } });
		 */

		DBHelper dbHelper = DBHelper.getInstance(this);
		// final List<MediaItemBean> itemList = null;
		try {
			mItemList = dbHelper.selectMedia();
		} catch (Exception ex) {
			Log.e(TAG, "media select failed !!", ex.fillInStackTrace());
		}

		mAdapter = new MediaListAdapter(this, mItemList);
		this.setListAdapter(mAdapter);

		ListView listView = (ListView) findViewById(id.list);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				Intent intent = new Intent(getApplicationContext(), ContentListActivity.class);
				intent.putExtra(getString(R.string.intent_media_id), mItemList.get(index).getId());
				intent.putExtra(getString(R.string.intent_media_name), mItemList.get(index).getName());
				intent.putExtra(getString(R.string.intent_media_list_index), String.valueOf(index));
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				selectedIndex = index;
				return false;
			}
		});
		if (mMediaListIndex != null && !mMediaListIndex.equals("")) {
			listView.setSelection(Integer.parseInt(mMediaListIndex));
		}

		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, ITEM_ID_DELETE, selectedIndex, getString(R.string.context_delete));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		final int itemIndex = item.getOrder();
		final MediaItemBean bean = mItemList.get(itemIndex);

		switch (item.getItemId()) {
			case ITEM_ID_DELETE :

				Dialog deleteDialog = new AlertDialog.Builder(MediaListActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.title_epub_delete)
						.setMessage(R.string.msg_epub_delete).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int arg1) {

								boolean result = false;
								DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
								String msg = "";
								try {
									// レコード削除
									dbHelper.deleteMediaById(bean.getId());
									dbHelper.deleteContentsById(bean.getId());

									String epubDirPath = FileDataUtil.getEpubDirPath(getApplicationContext().getPackageName(), bean.getId());
									File epubDir = new File(epubDirPath);

									if (epubDir.exists()) {
										FileUtils.deleteDirectory(epubDir);
										result = true;
									}

								} catch (SQLException ex) {
									msg = String.format("delete record failed !! [%s]", bean.getName());
									Log.e(TAG, msg, ex.fillInStackTrace());
								} catch (IOException ex) {
									msg = String.format("delete file failed !! [%s]", bean.getName());
									Log.e(TAG, msg, ex.fillInStackTrace());
								}
								if (!result) {
									msg = String.format("EPUB削除失敗\nタイトル：%s", bean.getName());
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
						List<MediaItemBean> itemList = dbHelper.selectMedia();
						if (!mItemList.equals(itemList)) {
							mItemList.clear();
							for (Iterator<MediaItemBean> i = itemList.iterator(); i.hasNext();) {
								MediaItemBean bean = i.next();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.media_list_option, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// String msg = "";
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.download :
				// msg = "Download now!!";

				intent = new Intent(getApplicationContext(), NavigateDownloadActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);

				break;
			case R.id.setting :
				// msg = "Settings now!!";

				intent = new Intent();
				intent.setClassName("jp.nextep.android.vtextviewer", "jp.nextep.android.vtextviewer.app.Preference");
				startActivity(intent);
				overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);

				break;
			case R.id.quit :

				showDialog(0);

				break;
		}
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return new AlertDialog.Builder(MediaListActivity.this).setMessage(R.string.msg_app_end).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				MediaListActivity.this.finish();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		}).create();

	}
}
