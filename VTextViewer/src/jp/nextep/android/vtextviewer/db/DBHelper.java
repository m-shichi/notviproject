package jp.nextep.android.vtextviewer.db;

import java.util.ArrayList;
import java.util.List;

import jp.nextep.android.vtextviewer.bean.BookmarkItemBean;
import jp.nextep.android.vtextviewer.bean.ContentItemBean;
import jp.nextep.android.vtextviewer.bean.MediaItemBean;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static boolean isDebug = false;

	public static final int SQL_INSERT = 1;
	public static final int SQL_UPDATE = 2;

	private static final String DB_NAME = "epub_viewer";
	private static final int DB_VERSION = 1;
	private static DBHelper dbHelper;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public static DBHelper getInstance(Context ctx) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(ctx);
		}
		return dbHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
			db.execSQL("create table media(id text not null, name text, primary key(id));");
			db.execSQL("create table contents(id text not null, content_index integer, page text not null, contents text, path text, image_flag integer, image_path text, primary key(id, content_index));");
			db.execSQL("create table bookmark(id integer, title text not null, place text not null, primary key(id));");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!isDebug) {
			return;
		}

		db.execSQL("insert into media(id, name) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', '東京本紙朝刊[20110125]');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 1, '朝国際', 'じょうほう◎金総書記、企業家と会　じょう．．．', 'Text/20110125TTSM00M002.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 2, '朝国際', '密室勝負のワシントン', 'Text/20110125TTSM00M003.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 3, '朝国際', 'ＡＳＥＡＮ外相陸路中国入り　議長国インド．．．', 'Text/20110125TTSM00M004.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 4, '朝国際', 'リトアニアのランズベルギス欧州議員　「北．．．', 'Text/20110125TTSM00M005.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 5, '朝国際', 'アルジャジーラ中東和平秘密文書公開　パレ．．．', 'Text/20110125TTSM00M006.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 6, '朝国際', '撃墜米軍機の技術盗用？　中国のステルス戦．．．', 'Text/20110125TTSM00M007.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 7, '朝国際', '広がる秘密情報漏洩・報道　「パレスチナ・．．．', 'Text/20110125TTSM00M008.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 8, '朝経済', 'トヨタ、世界販売台数３年連続世界一　米で．．．', 'Text/20110125TTSM00M009.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 9, '朝経済', 'スーパー売上高は年連続前年割れ日本．．．', 'Text/20110125TTSM00M010.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 10, '朝経済',  '薄型テレビ８４．９％増　過去最高の２５１．．．', 'Text/20110125TTSM00M011.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 11, '朝経済',  '長嶺正氏（ながみね・ただし‖元上組会長）．．．', 'Text/20110125TTSM00M012.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 12, '朝経済', 'スケジュール先走り　共通番号制法案化へ検討会', 'Text/20110125TTSM00M013.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 13, '朝経済', '国内外で大量出店攻勢　ファーストリテイリング会長兼社長　柳井正氏　山内隆司氏　大', 'Text/20110125TTSM00M014.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 14, '朝経済', '中国・アジア面〉〉人手不足で出稼ぎ争奪戦', 'Text/20110125TTSM00M015.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 15, '朝１社', '◇寒波で部屋の温度が上昇―。民間気象会社「ウェザーニューズ」（東京都）が今冬の部屋の温度を調べるアンケートを行ったところ、平均室温は．度と昨年より．度上昇して', 'Text/20110125TTSM00M016.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 16, '朝１社', '島津尚純氏（しまづ・なおずみ‖元衆院議員）平成年月８日、肝腫瘍のため死去、歳。葬儀・告別式は親族で済ませた。お別れ会は２月日午後５時、東京都中央区銀座８の４の', 'Text/20110125TTSM00M017.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 17, '朝１社', '熱した金具で虐待　傷害容疑夫婦逮捕　熱したライターの金具を子供の腕に押し当てるなどしたとして、埼玉県警西入間署は日、傷害容疑で、同県毛呂山町前久保南、会社員、六車裕之', 'Text/20110125TTSM00M018.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 18, '朝１社', '３歳児死亡　母親も重体　愛知の住宅', 'Text/20110125TTSM00M019.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 19, '朝１社', '小２　切られ重傷　顔６カ所刃物男立ち去る', 'Text/20110125TTSM00M020.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 20, '朝１社', '㍍　世界一高いレストラン　アラブ首長国連邦（ＵＡＥ）・ドバイで日、「世界一高い場所」にあるレストランがオープンした‖写真（ＡＰ）。昨年１月に完成した世界一の高', 'Text/20110125TTSM00M021.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 21, '朝１社', 'かみつきザル「らっきー」　脱走', 'Text/20110125TTSM00M022.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 22, '朝１社', '警官発砲殺人罪も審理　奈良地裁付審判殺意争点に', 'Text/20110125TTSM00M023.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 23, '朝１社', '付審判制度　警察官の暴行など公務員の職権乱用が疑われる事件を検察が不起訴にした場合、告訴した被害者らは審判を開くよう地裁に請求できる。地裁が請求を認める決定をすれば起訴', 'Text/20110125TTSM00M024.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 24, '朝１社', '未払い給料立て替えを悪用　失業救済金数千万詐取か　詐欺容疑組員らきょうにも逮捕', 'Text/20110125TTSM00M025.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 25, '朝１社', '不況を逆手　公的制度ターゲット', 'Text/20110125TTSM00M026.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('0cc9f95e-db5c-43f7-b314-d8289f6a7ddf', 26, '朝１社', '未払賃金立替払制度　倒産企業の従業員を救済することを目的に昭和年に制定。申請を受け、最長で半年間、未払い給料や退職金の８割を国が立て替える。平成年度の立て替え払い金', 'Text/20110125TTSM00M027.xhtml');");

		db.execSQL("insert into media(id, name) values ('d42473e5-31b9-4c83-b2c9-86369521008a', '東京本紙朝刊[20100912]');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('d42473e5-31b9-4c83-b2c9-86369521008a', 1, '朝１面', '古里の匂い　大阪府大阪狭山市　岡　洋子　　', 'Text/20100912TTSM00M002.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('d42473e5-31b9-4c83-b2c9-86369521008a', 2, '朝１面', '沖縄本島から西北西へ約２８０㌔の日本の排他的経済水域（ＥＥＺ）内で日朝、海洋　', 'Text/20100912TTSM00M003.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('d42473e5-31b9-4c83-b2c9-86369521008a', 3, '朝１面', '米国のＧＰＳ（衛星利用測位システム）を補う準天頂衛星の初号機「みちびき」が日　', 'Text/20100912TTSM00M004.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('d42473e5-31b9-4c83-b2c9-86369521008a', 4, '朝１面', 'あす　日も駅売り　テレビ、ラジオ欄～面、地上波テレビ番組は、面　■　谷　', 'Text/20100912TTSM00M005.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('d42473e5-31b9-4c83-b2c9-86369521008a', 5, '朝１面', '民主党代表選の投開票まであと３日に迫った日、首相の菅直人、前幹事長の小沢一郎　', 'Text/20100912TTSM00M006.xhtml');");

		db.execSQL("insert into media(id, name) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 'デザインでいっぷく');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 1, '', 'はじめまして', 'Text/Section0002.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 2, '', 'クッションというデザイン', 'Text/Section0003.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 3, '', '剥げていくデザイン', 'Text/Section0004.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 4, '', 'Google のロゴデザイン', 'Text/Section0005.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 5, '', '湯たんぽのデザイン', 'Text/Section0006.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 6, '', 'ティッシュのデザイン', 'Text/Section0007.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 7, '', 'プラダンのデザイン', 'Text/Section0008.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 8, '', '醤油差しのデザイン', 'Text/Section0009.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 9, '', 'ブログのデザイン', 'Text/Section0010.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 10, '', 'クーピーのデザイン', 'Text/Section00011.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 11, '', 'Apple のウェブデザイン', 'Text/Section00012.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 12, '', 'えさ入れのデザイン', 'Text/Section00013.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 13, '', 'ろうそくのデザイン', 'Text/Section00014.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 14, '', '週刊文春のデザイン', 'Text/Section00015.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 15, '', 'お酒のデザイン', 'Text/Section00016.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 16, '', 'タグのデザイン', 'Text/Section00017.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 17, '', '不揃いのデザイン', 'Text/Section00018.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 18, '', 'ツイッターのデザイン', 'Text/Section00019.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 19, '', '青空のデザイン', 'Text/Section00020.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 20, '', '植物のデザイン', 'Text/Section00021.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 21, '', '本のデザイン', 'Text/Section00022.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 22, '', '菓子楊枝のデザイン', 'Text/Section00023.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 23, '', '日本野鳥の会のデザイン', 'Text/Section00024.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 24, '', 'バリアフリーのデザイン', 'Text/Section00025.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 25, '', 'マウスのデザイン', 'Text/Section00026.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 26, '', 'iPadのデザイン', 'Text/Section00027.xhtml');");
		db.execSQL("insert into contents(id, content_index, page, contents, path) values ('b3835eb6-d556-45d8-9e21-7c1aa86e46b7', 27, '', '著者 フロップデザイン / 加藤雅士', 'Text/Section00028.xhtml');");

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/**
	 *
	 * @return
	 */
	public List<MediaItemBean> selectMedia() throws SQLException {

		List<MediaItemBean> itemList = new ArrayList<MediaItemBean>();
		Cursor cur = null;
		try {
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("media", new String[]{"id", "name"}, null, null, null, null, null);
			boolean isEof = cur.moveToFirst();

			while (isEof) {

				MediaItemBean bean = new MediaItemBean();
				bean.setId(cur.getString(0));
				bean.setName(cur.getString(1));

				itemList.add(bean);

				isEof = cur.moveToNext();
			}
		} finally {
			if (null != cur && !cur.isClosed()) {
				cur.close();
			}
		}
		return itemList;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public List<MediaItemBean> selectMediaById(String id) throws SQLException {

		List<MediaItemBean> itemList = null;
		Cursor cur = null;
		try {
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("media", new String[]{"id", "name"}, "id=?", new String[]{id}, null, null, null);
			// String sql = "select id, name from media where id=?";
			// cur = db.rawQuery(sql, new String[]{id});

			boolean isEof = cur.moveToFirst();

			itemList = new ArrayList<MediaItemBean>();
			while (isEof) {

				MediaItemBean bean = new MediaItemBean();
				bean.setId(cur.getString(0));
				bean.setName(cur.getString(1));

				itemList.add(bean);

				isEof = cur.moveToNext();
			}
		} finally {
			if (null != cur && !cur.isClosed()) {
				cur.close();
			}
		}
		return itemList;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteMediaById(String id) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int num = db.delete("media", "id=?", new String[]{id});

		if (num == 1) {
			result = true;
		}
		return result;
	}

	/**
	 *
	 * @param mediaId
	 * @return
	 * @throws SQLException
	 */
	public List<ContentItemBean> selectContents(String mediaId) throws SQLException {

		List<ContentItemBean> itemList = null;
		Cursor cur = null;
		String menName = "";
		try {
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			String sql = "select id, content_index, page, contents, path, image_flag, image_path from contents where id=? order by content_index";
			cur = db.rawQuery(sql, new String[]{mediaId});

			boolean isEof = cur.moveToFirst();

			itemList = new ArrayList<ContentItemBean>();
			while (isEof) {

				if (menName.equals("") || !menName.equals(cur.getString(2))) {
					ContentItemBean menBean = new ContentItemBean();
					menBean.setId("-");
					menBean.setContent_index(0);
					menBean.setPage(cur.getString(2));
					menBean.setContents("");
					menBean.setPath("");
					menBean.setImage_flag(0);
					menBean.setImage_path("");

					itemList.add(menBean);
				}

				ContentItemBean bean = new ContentItemBean();
				bean.setId(cur.getString(0));
				bean.setContent_index(cur.getInt(1));
				bean.setPage(cur.getString(2));
				bean.setContents(cur.getString(3));
				bean.setPath(cur.getString(4));
				bean.setImage_flag(cur.getInt(5));
				bean.setImage_path(cur.getString(6));

				itemList.add(bean);

				menName = cur.getString(2);

				isEof = cur.moveToNext();
			}
		} finally {
			if (null != cur && !cur.isClosed()) {
				cur.close();
			}
		}
		return itemList;
	}

	/**
	 *
	 * @param mediaId
	 * @param contentIndex
	 * @return
	 */
	public ContentItemBean selectContentsByContentIndex(String mediaId, String contentIndex) throws SQLException {

		ContentItemBean itemBean = null;
		Cursor cur = null;
		try {
			// read only
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			String sql = "select id, content_index, page, contents, path, image_flag from contents where id=? and content_index=abs(?)";
			cur = db.rawQuery(sql, new String[]{mediaId, contentIndex});

			boolean isEof = cur.moveToFirst();
			itemBean = new ContentItemBean();
			while (isEof) {

				itemBean.setId(cur.getString(0));
				itemBean.setContent_index(cur.getInt(1));
				itemBean.setPage(cur.getString(2));
				itemBean.setContents(cur.getString(3));
				itemBean.setPath(cur.getString(4));
				itemBean.setImage_flag(cur.getInt(5));

				isEof = cur.moveToNext();
			}
		} finally {
			if (null != cur && !cur.isClosed()) {
				cur.close();
			}
		}
		return itemBean;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteContentsById(String id) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int num = db.delete("contents", "id=?", new String[]{id});

		result = true;

		return result;
	}

	/**
	 *
	 * @return
	 */
	public List<BookmarkItemBean> selectBookmark() throws SQLException {

		List<BookmarkItemBean> itemList = null;
		Cursor cur = null;
		try {
			SQLiteDatabase db = dbHelper.getReadableDatabase();

			cur = db.query("bookmark", new String[]{"id", "title", "place"}, null, null, null, null, null);
			boolean isEof = cur.moveToFirst();

			itemList = new ArrayList<BookmarkItemBean>();
			while (isEof) {

				BookmarkItemBean bean = new BookmarkItemBean();
				bean.setId(cur.getInt(0));
				bean.setTitle(cur.getString(1));
				bean.setPlace(cur.getString(2));
				itemList.add(bean);

				isEof = cur.moveToNext();
			}
		} finally {
			if (null != cur && !cur.isClosed()) {
				cur.close();
			}
		}
		return itemList;
	}

	/**
	 *
	 * @param title
	 * @param place
	 * @return
	 */
	public boolean insertBookmark(String title, String place) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("place", place);

		long num = db.insert("bookmark", null, values);

		if (num == 1) {
			result = true;
		}
		return result;
	}

	/**
	 *
	 * @param title
	 * @param place
	 * @param id
	 * @return
	 */
	public boolean updateBookmarkById(String title, String place, String id) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("place", place);

		int num = db.update("bookmark", values, "id=?", new String[]{id});

		if (num == 1) {
			result = true;
		}
		return result;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteBookmarkById(int id) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int num = db.delete("bookmark", "id=?", new String[]{String.valueOf(id)});

		if (num == 1) {
			result = true;
		}
		return result;
	}

	/**
	 *
	 * @param id
	 * @param text
	 * @return
	 */
	public boolean insertMedia(String id, String text) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", text);

		long num = db.insert("media", null, values);

		if (num == 1) {
			result = true;
		}
		return result;
	}
	/**
	 *
	 * @param id
	 * @param index
	 * @param page
	 * @param contents
	 * @param path
	 * @return
	 */
	public boolean insertContents(String id, int content_index, String page, String contents, String path, int image_flag, String image_path) throws SQLException {

		boolean result = false;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("content_index", content_index);
		values.put("page", page);
		values.put("contents", contents);
		values.put("path", path);
		values.put("image_flag", image_flag);
		values.put("image_path", image_path);

		long num = db.insert("contents", null, values);

		if (num == 1) {
			result = true;
		}
		return result;
	}
}
