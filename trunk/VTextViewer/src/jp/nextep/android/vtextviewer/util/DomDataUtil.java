package jp.nextep.android.vtextviewer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDataUtil {

	private InputStream mIs = null;
	private Document mDocument = null;

	public DomDataUtil(InputStream is) {
		this.mIs = is;
	}

	public void init() {
		getDocument();
	}

	/**
	 *
	 * @return
	 */
	private void getDocument() {

		DocumentBuilder builder = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		try {
			mDocument = builder.parse(mIs);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != mIs) {
					mIs.close();
				}
			} catch (IOException ex) {
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public NodeList getParagraph() {
		NodeList pList = mDocument.getElementsByTagName("p");
		return pList;
	}

	/**
	 *
	 * @return
	 */
	public String[] getImagePath() {
		String path = "";
		List<String> list = new ArrayList<String>();
		NodeList imgList = mDocument.getElementsByTagName("img");
		for (int i = 0; i < imgList.getLength(); i++) {
			Node srcNode = imgList.item(i).getAttributes().getNamedItem("src");
			path = srcNode.getNodeValue();
			list.add(path.replaceAll("\\.\\./", ""));
		}
		return list.toArray(new String[0]);
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	public String[] getText(Node node) {
		List<String> list = new ArrayList<String>();
		NodeList nList = node.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++) {
			Node cNode = nList.item(i);
			if (cNode.getNodeType() == Node.TEXT_NODE) {
				if (!cNode.getNodeValue().equals("")) {
					list.add("　" + cNode.getNodeValue());
				}
			}
		}
		return list.toArray(new String[0]);
	}
}
