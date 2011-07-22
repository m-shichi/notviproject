package jp.web.sync.android.db;

import java.util.ArrayList;
import java.util.List;

import jp.web.sync.android.bean.SelfGroupInfoBean;
import jp.web.sync.android.bean.SelfInfoBean;
import jp.web.sync.relax.response.ResponseXML;
import jp.web.sync.relax.response.UserInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
	// -----------------------------------
	// 定数
	// -----------------------------------
	public static final int SQL_INSERT = 1;
	public static final int SQL_UPDATE = 2;
	private static final String[] COLUMN_SELF_INFO = {"id", "mail_addr", "password", "handle_name", "message", "img_path", "terminal_id"};
	private static final String[] COLUMN_SELF_GROUP_INFO = {"id", "user_id", "group_name"};

	private static final String DB_NAME = "kddb";
	private static final int DB_VERSION = 1;

	private static DBHelper dbHelper;
	public static boolean isDebug = false;

	public DBHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 *
	 * @param ctx
	 * @return
	 */
	public static DBHelper getInstance(Context ctx)
	{
		if (dbHelper == null)
		{
			dbHelper = new DBHelper(ctx);
		}
		return dbHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		try
		{
			db.execSQL("create table self_info(id integer not null, mail_addr text, password text, handle_name text, message text, img_path text, terminal_id text, primary key(id));");
			db.execSQL("create table self_group_info(id integer not null, user_id integer not null, group_name text not null, primary key(id, user_id));");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		if (!isDebug)
		{
			return;
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db)
	{
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

	/**
	 *
	 * @return
	 */
	public SelfInfoBean selectSelfInfoByAddr(String mailAddr, String password) throws SQLException
	{
		SelfInfoBean bean = null;
		Cursor cur = null;
		try
		{
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("self_info", COLUMN_SELF_INFO, "mail_addr=? and password=?", new String[]{mailAddr, password}, null, null, null);

			boolean isEof = cur.moveToFirst();

			while (isEof)
			{
				bean = new SelfInfoBean();
				bean.setId(cur.getInt(0));
				bean.setMailAddr(cur.getString(1));
				bean.setPassword(cur.getString(2));
				bean.setHandleName(cur.getString(3));
				bean.setMessage(cur.getString(4));
				bean.setImgPath(cur.getString(5));
				bean.setTerminalId(cur.getString(6));

				isEof = cur.moveToNext();
			}
		}
		finally
		{
			if (null != cur && !cur.isClosed())
			{
				cur.close();
			}
		}
		return bean;
	}

	/**
	 *
	 * @return
	 */
	public SelfInfoBean selectSelfInfoById(int userId) throws SQLException
	{
		SelfInfoBean bean = null;
		Cursor cur = null;
		try
		{
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("self_info", COLUMN_SELF_INFO, "id=?", new String[]{String.valueOf(userId)}, null, null, null);

			boolean isEof = cur.moveToFirst();

			while (isEof)
			{
				bean = new SelfInfoBean();
				bean.setId(cur.getInt(0));
				bean.setMailAddr(cur.getString(1));
				bean.setPassword(cur.getString(2));
				bean.setHandleName(cur.getString(3));
				bean.setMessage(cur.getString(4));
				bean.setImgPath(cur.getString(5));
				bean.setTerminalId(cur.getString(6));

				isEof = cur.moveToNext();
			}
		}
		finally
		{
			if (null != cur && !cur.isClosed())
			{
				cur.close();
			}
		}
		return bean;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public SelfInfoBean[] selectSelfInfos() throws SQLException
	{
		List<SelfInfoBean> list = new ArrayList<SelfInfoBean>();
		Cursor cur = null;
		try
		{
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("self_info", COLUMN_SELF_INFO, null, null, null, null, null);

			boolean isEof = cur.moveToFirst();

			while (isEof)
			{
				SelfInfoBean bean = new SelfInfoBean();
				bean.setId(cur.getInt(0));
				bean.setMailAddr(cur.getString(1));
				bean.setPassword(cur.getString(2));
				bean.setHandleName(cur.getString(3));
				bean.setMessage(cur.getString(4));
				bean.setImgPath(cur.getString(5));
				bean.setTerminalId(cur.getString(6));

				list.add(bean);

				isEof = cur.moveToNext();
			}
		}
		finally
		{
			if (null != cur && !cur.isClosed())
			{
				cur.close();
			}
		}
		return list.toArray(new SelfInfoBean[0]);
	}

	/**
	 *
	 * @param title
	 * @param place
	 * @return
	 */
	public boolean insertSelfInfo(ResponseXML bean, String password) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		UserInfo userInfo = bean.getData().getUserInfo();

		ContentValues values = new ContentValues();
		values.put("id", userInfo.getId());
		values.put("mail_addr", userInfo.getMailAddrAsString());
		values.put("password", password);
		values.put("terminal_id", userInfo.getTerminalIdAsString());

		long num = db.insert("self_info", null, values);

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}
	/**
	 *
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSelfInfo(SelfInfoBean bean) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("mail_addr", bean.getMailAddr());
		values.put("password", bean.getPassword());
		values.put("handle_name", bean.getHandleName());
		values.put("message", bean.getMessage());
		values.put("img_path", bean.getImgPath());
		values.put("terminal_id", bean.getTerminalId());

		int num = db.update("self_info", values, "id=?", new String[]{String.valueOf(bean.getId())});

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteSelfInfo(int id) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int num = db.delete("self_info", "id=?", new String[]{String.valueOf(id)});

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public List<SelfGroupInfoBean> selectSelfGroupInfo(int userId) throws SQLException
	{
		List<SelfGroupInfoBean> list = new ArrayList<SelfGroupInfoBean>();
		Cursor cur = null;
		try
		{
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("self_group_info", COLUMN_SELF_GROUP_INFO, "user_id=?", new String[]{String.valueOf(userId)}, null, null, null);

			boolean isEof = cur.moveToFirst();

			while (isEof)
			{

				SelfGroupInfoBean bean = new SelfGroupInfoBean();
				bean.setId(cur.getInt(0));
				bean.setUserId(cur.getInt(1));
				bean.setGroupName(cur.getString(2));

				list.add(bean);

				isEof = cur.moveToNext();
			}
		}
		finally
		{
			if (null != cur && !cur.isClosed())
			{
				cur.close();
			}
		}
		return list;
	}

	/**
	 *
	 * @param title
	 * @param place
	 * @return
	 */
	public boolean insertSelfGroupInfo(int id, int userId, String groupName) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("user_id", userId);
		values.put("group_name", groupName);

		long num = db.insert("self_group_info", null, values);

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}

	/**
	 *
	 * @param selfGroupInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSelfGroupInfo(SelfGroupInfoBean selfGroupInfo, SelfInfoBean selfInfo) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("group_name", selfGroupInfo.getGroupName());

		int num = db.update("self_group_info", values, "id=? and user_id=?", new String[]{String.valueOf(selfGroupInfo.getId()), String.valueOf(selfInfo.getId())});

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteSelfGroupInfo(int id, int userId) throws SQLException
	{
		boolean ret = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int num = db.delete("self_group_info", "id=? and user_id=?", new String[]{String.valueOf(id), String.valueOf(userId)});

		if (num == 1)
		{
			ret = true;
		}
		return ret;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteSelfGroupInfoByUserId(int userId)
	{
		boolean ret = false;
		try
		{
			SQLiteDatabase db = dbHelper.getWritableDatabase();

			db.delete("self_group_info", "user_id=?", new String[]{String.valueOf(userId)});

			ret = true;

		}
		catch (SQLException ex)
		{
			Log.e("", "グループ情報削除失敗", ex);
		}
		return ret;
	}
}
