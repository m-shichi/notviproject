package info.clockworksapple.android.barsearch.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.utils.URIUtils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class StringUtil {

	private StringUtil() {
	}

	/**
	 *
	 * @param url
	 * @return
	 */
	public static URI getURI(String url) {

		List<String> list = new ArrayList<String>();
		String[] schema = url.split("/");

		for (int i = 0; i < schema.length; i++) {
			if (!schema[i].equals("")) {
				list.add(schema[i]);
			}
		}
		URI uri = null;
		try {
			uri = URIUtils.createURI("http", list.get(1), -1, createPath(list), null, null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri;
	}

	/**
	 *
	 * @param list
	 * @return
	 */
	private static String createPath(List<String> list) {

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < list.size(); i++) {
			sb.append("/");
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	public static String zenkakuAlphabetToHankaku(String s) {

		s = s.replaceAll("　", " ");
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ａ' && c <= 'ｚ') {
				sb.setCharAt(i, (char) (c - 'ａ' + 'a'));
			} else if (c >= 'Ａ' && c <= 'Ｚ') {
				sb.setCharAt(i, (char) (c - 'Ａ' + 'A'));
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * @param latitude
	 * @param longitude
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static String point2address(double latitude, double longitude, Context context) throws IOException {

		String string = new String();

		Geocoder geocoder = new Geocoder(context, Locale.JAPAN);
		List<Address> list_address = geocoder.getFromLocation(latitude, longitude, 5); // 引数末尾は返す検索結果数

		// ジオコーディングに成功したらStringへ
		if (!list_address.isEmpty()) {

			Address address = list_address.get(0);
			StringBuffer strbuf = new StringBuffer();

			// adressをStringへ
			String buf;
			for (int i = 0; (buf = address.getAddressLine(i)) != null; i++) {
				if (buf.indexOf("日本") == -1) {
					strbuf.append(buf);
				}
			}

			string = strbuf.toString();

		}
		// 失敗（Listが空だったら）
		else {
		}
		return string;
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	public static String getNullValue(Object o) {
		String str = null;
		if (null == o) {
			str = "";
		} else {
			str = o.toString();
		}
		return str;
	}

}
