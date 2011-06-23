/*
 * The Relaxer artifact
 * Copyright (c) 2000-2003, ASAMI Tomoharu, All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package info.clockworksapple.android.barsearch.relax.result.bar;

import info.clockworksapple.android.barsearch.relax.base.RStack;
import info.clockworksapple.android.barsearch.relax.base.UJAXP;
import info.clockworksapple.android.barsearch.relax.base.URelaxer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>Shop</b> is generated from res_bar.rng by Relaxer. This class is derived
 * from:
 *
 * <!-- for programmer <element name="shop"> <element name="access"> <data
 * type="token"/> </element> <element name="address"> <data type="token"/>
 * </element> <element name="budget"> <data type="token"/> </element> <element
 * name="capacity"> <data type="token"/> </element> <element name="close"> <data
 * type="token"/> </element> <element name="id"> <data type="token"/> </element>
 * <element name="lat_tokyo"> <data type="token"/> </element> <element
 * name="lat_world"> <data type="token"/> </element> <element name="lng_tokyo">
 * <data type="token"/> </element> <element name="lng_world"> <data
 * type="token"/> </element> <element name="name"> <data type="token"/>
 * </element> <element name="name_kana"> <data type="token"/> </element>
 * <element name="open"> <data type="token"/> </element> <element
 * name="private_room"> <data type="token"/> </element> <element name="type">
 * <data type="token"/> </element> <element name="url_mobile"> <data
 * type="token"/> </element> <element name="url_pc"> <data type="token"/>
 * </element> <element name="url_photo_k1"> <data type="token"/> </element>
 * <element name="url_photo_l1"> <data type="token"/> </element> <element
 * name="url_photo_l2"> <data type="token"/> </element> <element
 * name="url_photo_s1"> <data type="token"/> </element> <element
 * name="url_photo_s2"> <data type="token"/> </element> <element
 * name="url_photo_s3"> <data type="token"/> </element> <element
 * name="url_photo_s4"> <data type="token"/> </element> </element> --> <!-- for
 * javadoc -->
 *
 * <pre>
 * &lt;element name="shop"&gt;
 *   &lt;element name="access"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="address"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="budget"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="capacity"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="close"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="id"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="lat_tokyo"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="lat_world"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="lng_tokyo"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="lng_world"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="name"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="name_kana"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="open"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="private_room"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="type"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_mobile"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_pc"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_k1"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_l1"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_l2"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_s1"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_s2"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_s3"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="url_photo_s4"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version res_bar.rng (Mon Dec 27 10:48:03 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class Shop implements java.io.Serializable, Cloneable {
	private String access_;
	private String address_;
	private String budget_;
	private String capacity_;
	private String close_;
	private String id_;
	private String latTokyo_;
	private String latWorld_;
	private String lngTokyo_;
	private String lngWorld_;
	private String name_;
	private String nameKana_;
	private String open_;
	private String privateRoom_;
	private String type_;
	private String urlMobile_;
	private String urlPc_;
	private String urlPhotoK1_;
	private String urlPhotoL1_;
	private String urlPhotoL2_;
	private String urlPhotoS1_;
	private String urlPhotoS2_;
	private String urlPhotoS3_;
	private String urlPhotoS4_;

	/**
	 * Creates a <code>Shop</code>.
	 *
	 */
	public Shop() {
		access_ = "";
		address_ = "";
		budget_ = "";
		capacity_ = "";
		close_ = "";
		id_ = "";
		latTokyo_ = "";
		latWorld_ = "";
		lngTokyo_ = "";
		lngWorld_ = "";
		name_ = "";
		nameKana_ = "";
		open_ = "";
		privateRoom_ = "";
		type_ = "";
		urlMobile_ = "";
		urlPc_ = "";
		urlPhotoK1_ = "";
		urlPhotoL1_ = "";
		urlPhotoL2_ = "";
		urlPhotoS1_ = "";
		urlPhotoS2_ = "";
		urlPhotoS3_ = "";
		urlPhotoS4_ = "";
	}

	/**
	 * Creates a <code>Shop</code>.
	 *
	 * @param source
	 */
	public Shop(Shop source) {
		setup(source);
	}

	/**
	 * Creates a <code>Shop</code> by the Stack <code>stack</code> that contains
	 * Elements. This constructor is supposed to be used internally by the
	 * Relaxer system.
	 *
	 * @param stack
	 */
	public Shop(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Shop</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Shop(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Shop</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Shop(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Shop</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Shop</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Shop</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Shop</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Shop</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Shop</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shop(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Shop</code> by the Shop <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Shop source) {
		int size;
		setAccess(source.getAccess());
		setAddress(source.getAddress());
		setBudget(source.getBudget());
		setCapacity(source.getCapacity());
		setClose(source.getClose());
		setId(source.getId());
		setLatTokyo(source.getLatTokyo());
		setLatWorld(source.getLatWorld());
		setLngTokyo(source.getLngTokyo());
		setLngWorld(source.getLngWorld());
		setName(source.getName());
		setNameKana(source.getNameKana());
		setOpen(source.getOpen());
		setPrivateRoom(source.getPrivateRoom());
		setType(source.getType());
		setUrlMobile(source.getUrlMobile());
		setUrlPc(source.getUrlPc());
		setUrlPhotoK1(source.getUrlPhotoK1());
		setUrlPhotoL1(source.getUrlPhotoL1());
		setUrlPhotoL2(source.getUrlPhotoL2());
		setUrlPhotoS1(source.getUrlPhotoS1());
		setUrlPhotoS2(source.getUrlPhotoS2());
		setUrlPhotoS3(source.getUrlPhotoS3());
		setUrlPhotoS4(source.getUrlPhotoS4());
	}

	/**
	 * Initializes the <code>Shop</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Shop</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Shop</code> by the Stack <code>stack</code> that
	 * contains Elements. This constructor is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 */
	public void setup(RStack stack) {
		init(stack.popElement());
	}

	/**
	 * @param element
	 */
	private void init(Element element) {
		RStack stack = new RStack(element);
		access_ = URelaxer.getElementPropertyAsString(stack.popElement());
		address_ = URelaxer.getElementPropertyAsString(stack.popElement());
		budget_ = URelaxer.getElementPropertyAsString(stack.popElement());
		capacity_ = URelaxer.getElementPropertyAsString(stack.popElement());
		close_ = URelaxer.getElementPropertyAsString(stack.popElement());
		id_ = URelaxer.getElementPropertyAsString(stack.popElement());
		latTokyo_ = URelaxer.getElementPropertyAsString(stack.popElement());
		latWorld_ = URelaxer.getElementPropertyAsString(stack.popElement());
		lngTokyo_ = URelaxer.getElementPropertyAsString(stack.popElement());
		lngWorld_ = URelaxer.getElementPropertyAsString(stack.popElement());
		name_ = URelaxer.getElementPropertyAsString(stack.popElement());
		nameKana_ = URelaxer.getElementPropertyAsString(stack.popElement());
		open_ = URelaxer.getElementPropertyAsString(stack.popElement());
		privateRoom_ = URelaxer.getElementPropertyAsString(stack.popElement());
		type_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlMobile_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPc_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoK1_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoL1_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoL2_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoS1_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoS2_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoS3_ = URelaxer.getElementPropertyAsString(stack.popElement());
		urlPhotoS4_ = URelaxer.getElementPropertyAsString(stack.popElement());
	}

	/**
	 * @return Object
	 */
	@Override
	public Object clone() {
		return (new Shop(this));
	}

	/**
	 * Creates a DOM representation of the object. Result is appended to the
	 * Node <code>parent</code>.
	 *
	 * @param parent
	 */
	public void makeElement(Node parent) {
		Document doc;
		if (parent instanceof Document) {
			doc = (Document) parent;
		} else {
			doc = parent.getOwnerDocument();
		}
		Element element = doc.createElement("shop");
		int size;
		URelaxer.setElementPropertyByString(element, "access", this.access_);
		URelaxer.setElementPropertyByString(element, "address", this.address_);
		URelaxer.setElementPropertyByString(element, "budget", this.budget_);
		URelaxer.setElementPropertyByString(element, "capacity", this.capacity_);
		URelaxer.setElementPropertyByString(element, "close", this.close_);
		URelaxer.setElementPropertyByString(element, "id", this.id_);
		URelaxer.setElementPropertyByString(element, "lat_tokyo", this.latTokyo_);
		URelaxer.setElementPropertyByString(element, "lat_world", this.latWorld_);
		URelaxer.setElementPropertyByString(element, "lng_tokyo", this.lngTokyo_);
		URelaxer.setElementPropertyByString(element, "lng_world", this.lngWorld_);
		URelaxer.setElementPropertyByString(element, "name", this.name_);
		URelaxer.setElementPropertyByString(element, "name_kana", this.nameKana_);
		URelaxer.setElementPropertyByString(element, "open", this.open_);
		URelaxer.setElementPropertyByString(element, "private_room", this.privateRoom_);
		URelaxer.setElementPropertyByString(element, "type", this.type_);
		URelaxer.setElementPropertyByString(element, "url_mobile", this.urlMobile_);
		URelaxer.setElementPropertyByString(element, "url_pc", this.urlPc_);
		URelaxer.setElementPropertyByString(element, "url_photo_k1", this.urlPhotoK1_);
		URelaxer.setElementPropertyByString(element, "url_photo_l1", this.urlPhotoL1_);
		URelaxer.setElementPropertyByString(element, "url_photo_l2", this.urlPhotoL2_);
		URelaxer.setElementPropertyByString(element, "url_photo_s1", this.urlPhotoS1_);
		URelaxer.setElementPropertyByString(element, "url_photo_s2", this.urlPhotoS2_);
		URelaxer.setElementPropertyByString(element, "url_photo_s3", this.urlPhotoS3_);
		URelaxer.setElementPropertyByString(element, "url_photo_s4", this.urlPhotoS4_);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Shop</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file.toURL());
	}

	/**
	 * Initializes the <code>Shop</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
	}

	/**
	 * Initializes the <code>Shop</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
	}

	/**
	 * Initializes the <code>Shop</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
	}

	/**
	 * Initializes the <code>Shop</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
	}

	/**
	 * Initializes the <code>Shop</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public void setup(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(UJAXP.getDocument(reader, UJAXP.FLAG_NONE));
	}

	/**
	 * Creates a DOM document representation of the object.
	 *
	 * @exception ParserConfigurationException
	 * @return Document
	 */
	public Document makeDocument() throws ParserConfigurationException {
		Document doc = UJAXP.makeDocument();
		makeElement(doc);
		return (doc);
	}

	/**
	 * Gets the String property <b>access</b>.
	 *
	 * @return String
	 */
	public final String getAccess() {
		return (access_);
	}

	/**
	 * Sets the String property <b>access</b>.
	 *
	 * @param access
	 */
	public final void setAccess(String access) {
		this.access_ = access;
	}

	/**
	 * Gets the String property <b>address</b>.
	 *
	 * @return String
	 */
	public final String getAddress() {
		return (address_);
	}

	/**
	 * Sets the String property <b>address</b>.
	 *
	 * @param address
	 */
	public final void setAddress(String address) {
		this.address_ = address;
	}

	/**
	 * Gets the String property <b>budget</b>.
	 *
	 * @return String
	 */
	public final String getBudget() {
		return (budget_);
	}

	/**
	 * Sets the String property <b>budget</b>.
	 *
	 * @param budget
	 */
	public final void setBudget(String budget) {
		this.budget_ = budget;
	}

	/**
	 * Gets the String property <b>capacity</b>.
	 *
	 * @return String
	 */
	public final String getCapacity() {
		return (capacity_);
	}

	/**
	 * Sets the String property <b>capacity</b>.
	 *
	 * @param capacity
	 */
	public final void setCapacity(String capacity) {
		this.capacity_ = capacity;
	}

	/**
	 * Gets the String property <b>close</b>.
	 *
	 * @return String
	 */
	public final String getClose() {
		return (close_);
	}

	/**
	 * Sets the String property <b>close</b>.
	 *
	 * @param close
	 */
	public final void setClose(String close) {
		this.close_ = close;
	}

	/**
	 * Gets the String property <b>id</b>.
	 *
	 * @return String
	 */
	public final String getId() {
		return (id_);
	}

	/**
	 * Sets the String property <b>id</b>.
	 *
	 * @param id
	 */
	public final void setId(String id) {
		this.id_ = id;
	}

	/**
	 * Gets the String property <b>latTokyo</b>.
	 *
	 * @return String
	 */
	public final String getLatTokyo() {
		return (latTokyo_);
	}

	/**
	 * Sets the String property <b>latTokyo</b>.
	 *
	 * @param latTokyo
	 */
	public final void setLatTokyo(String latTokyo) {
		this.latTokyo_ = latTokyo;
	}

	/**
	 * Gets the String property <b>latWorld</b>.
	 *
	 * @return String
	 */
	public final String getLatWorld() {
		return (latWorld_);
	}

	/**
	 * Sets the String property <b>latWorld</b>.
	 *
	 * @param latWorld
	 */
	public final void setLatWorld(String latWorld) {
		this.latWorld_ = latWorld;
	}

	/**
	 * Gets the String property <b>lngTokyo</b>.
	 *
	 * @return String
	 */
	public final String getLngTokyo() {
		return (lngTokyo_);
	}

	/**
	 * Sets the String property <b>lngTokyo</b>.
	 *
	 * @param lngTokyo
	 */
	public final void setLngTokyo(String lngTokyo) {
		this.lngTokyo_ = lngTokyo;
	}

	/**
	 * Gets the String property <b>lngWorld</b>.
	 *
	 * @return String
	 */
	public final String getLngWorld() {
		return (lngWorld_);
	}

	/**
	 * Sets the String property <b>lngWorld</b>.
	 *
	 * @param lngWorld
	 */
	public final void setLngWorld(String lngWorld) {
		this.lngWorld_ = lngWorld;
	}

	/**
	 * Gets the String property <b>name</b>.
	 *
	 * @return String
	 */
	public final String getName() {
		return (name_);
	}

	/**
	 * Sets the String property <b>name</b>.
	 *
	 * @param name
	 */
	public final void setName(String name) {
		this.name_ = name;
	}

	/**
	 * Gets the String property <b>nameKana</b>.
	 *
	 * @return String
	 */
	public final String getNameKana() {
		return (nameKana_);
	}

	/**
	 * Sets the String property <b>nameKana</b>.
	 *
	 * @param nameKana
	 */
	public final void setNameKana(String nameKana) {
		this.nameKana_ = nameKana;
	}

	/**
	 * Gets the String property <b>open</b>.
	 *
	 * @return String
	 */
	public final String getOpen() {
		return (open_);
	}

	/**
	 * Sets the String property <b>open</b>.
	 *
	 * @param open
	 */
	public final void setOpen(String open) {
		this.open_ = open;
	}

	/**
	 * Gets the String property <b>privateRoom</b>.
	 *
	 * @return String
	 */
	public final String getPrivateRoom() {
		return (privateRoom_);
	}

	/**
	 * Sets the String property <b>privateRoom</b>.
	 *
	 * @param privateRoom
	 */
	public final void setPrivateRoom(String privateRoom) {
		this.privateRoom_ = privateRoom;
	}

	/**
	 * Gets the String property <b>type</b>.
	 *
	 * @return String
	 */
	public final String getType() {
		return (type_);
	}

	/**
	 * Sets the String property <b>type</b>.
	 *
	 * @param type
	 */
	public final void setType(String type) {
		this.type_ = type;
	}

	/**
	 * Gets the String property <b>urlMobile</b>.
	 *
	 * @return String
	 */
	public final String getUrlMobile() {
		return (urlMobile_);
	}

	/**
	 * Sets the String property <b>urlMobile</b>.
	 *
	 * @param urlMobile
	 */
	public final void setUrlMobile(String urlMobile) {
		this.urlMobile_ = urlMobile;
	}

	/**
	 * Gets the String property <b>urlPc</b>.
	 *
	 * @return String
	 */
	public final String getUrlPc() {
		return (urlPc_);
	}

	/**
	 * Sets the String property <b>urlPc</b>.
	 *
	 * @param urlPc
	 */
	public final void setUrlPc(String urlPc) {
		this.urlPc_ = urlPc;
	}

	/**
	 * Gets the String property <b>urlPhotoK1</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoK1() {
		return (urlPhotoK1_);
	}

	/**
	 * Sets the String property <b>urlPhotoK1</b>.
	 *
	 * @param urlPhotoK1
	 */
	public final void setUrlPhotoK1(String urlPhotoK1) {
		this.urlPhotoK1_ = urlPhotoK1;
	}

	/**
	 * Gets the String property <b>urlPhotoL1</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoL1() {
		return (urlPhotoL1_);
	}

	/**
	 * Sets the String property <b>urlPhotoL1</b>.
	 *
	 * @param urlPhotoL1
	 */
	public final void setUrlPhotoL1(String urlPhotoL1) {
		this.urlPhotoL1_ = urlPhotoL1;
	}

	/**
	 * Gets the String property <b>urlPhotoL2</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoL2() {
		return (urlPhotoL2_);
	}

	/**
	 * Sets the String property <b>urlPhotoL2</b>.
	 *
	 * @param urlPhotoL2
	 */
	public final void setUrlPhotoL2(String urlPhotoL2) {
		this.urlPhotoL2_ = urlPhotoL2;
	}

	/**
	 * Gets the String property <b>urlPhotoS1</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoS1() {
		return (urlPhotoS1_);
	}

	/**
	 * Sets the String property <b>urlPhotoS1</b>.
	 *
	 * @param urlPhotoS1
	 */
	public final void setUrlPhotoS1(String urlPhotoS1) {
		this.urlPhotoS1_ = urlPhotoS1;
	}

	/**
	 * Gets the String property <b>urlPhotoS2</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoS2() {
		return (urlPhotoS2_);
	}

	/**
	 * Sets the String property <b>urlPhotoS2</b>.
	 *
	 * @param urlPhotoS2
	 */
	public final void setUrlPhotoS2(String urlPhotoS2) {
		this.urlPhotoS2_ = urlPhotoS2;
	}

	/**
	 * Gets the String property <b>urlPhotoS3</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoS3() {
		return (urlPhotoS3_);
	}

	/**
	 * Sets the String property <b>urlPhotoS3</b>.
	 *
	 * @param urlPhotoS3
	 */
	public final void setUrlPhotoS3(String urlPhotoS3) {
		this.urlPhotoS3_ = urlPhotoS3;
	}

	/**
	 * Gets the String property <b>urlPhotoS4</b>.
	 *
	 * @return String
	 */
	public final String getUrlPhotoS4() {
		return (urlPhotoS4_);
	}

	/**
	 * Sets the String property <b>urlPhotoS4</b>.
	 *
	 * @param urlPhotoS4
	 */
	public final void setUrlPhotoS4(String urlPhotoS4) {
		this.urlPhotoS4_ = urlPhotoS4;
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @return String
	 */
	public String makeTextDocument() {
		StringBuffer buffer = new StringBuffer();
		makeTextElement(buffer);
		return (new String(buffer));
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(StringBuffer buffer) {
		int size;
		buffer.append("<shop");
		buffer.append(">");
		buffer.append("<access>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getAccess())));
		buffer.append("</access>");
		buffer.append("<address>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getAddress())));
		buffer.append("</address>");
		buffer.append("<budget>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getBudget())));
		buffer.append("</budget>");
		buffer.append("<capacity>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getCapacity())));
		buffer.append("</capacity>");
		buffer.append("<close>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getClose())));
		buffer.append("</close>");
		buffer.append("<id>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getId())));
		buffer.append("</id>");
		buffer.append("<lat_tokyo>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLatTokyo())));
		buffer.append("</lat_tokyo>");
		buffer.append("<lat_world>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLatWorld())));
		buffer.append("</lat_world>");
		buffer.append("<lng_tokyo>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLngTokyo())));
		buffer.append("</lng_tokyo>");
		buffer.append("<lng_world>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLngWorld())));
		buffer.append("</lng_world>");
		buffer.append("<name>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.append("</name>");
		buffer.append("<name_kana>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getNameKana())));
		buffer.append("</name_kana>");
		buffer.append("<open>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getOpen())));
		buffer.append("</open>");
		buffer.append("<private_room>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getPrivateRoom())));
		buffer.append("</private_room>");
		buffer.append("<type>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getType())));
		buffer.append("</type>");
		buffer.append("<url_mobile>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlMobile())));
		buffer.append("</url_mobile>");
		buffer.append("<url_pc>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPc())));
		buffer.append("</url_pc>");
		buffer.append("<url_photo_k1>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoK1())));
		buffer.append("</url_photo_k1>");
		buffer.append("<url_photo_l1>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL1())));
		buffer.append("</url_photo_l1>");
		buffer.append("<url_photo_l2>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL2())));
		buffer.append("</url_photo_l2>");
		buffer.append("<url_photo_s1>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS1())));
		buffer.append("</url_photo_s1>");
		buffer.append("<url_photo_s2>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS2())));
		buffer.append("</url_photo_s2>");
		buffer.append("<url_photo_s3>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS3())));
		buffer.append("</url_photo_s3>");
		buffer.append("<url_photo_s4>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS4())));
		buffer.append("</url_photo_s4>");
		buffer.append("</shop>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<shop");
		buffer.write(">");
		buffer.write("<access>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getAccess())));
		buffer.write("</access>");
		buffer.write("<address>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getAddress())));
		buffer.write("</address>");
		buffer.write("<budget>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getBudget())));
		buffer.write("</budget>");
		buffer.write("<capacity>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getCapacity())));
		buffer.write("</capacity>");
		buffer.write("<close>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getClose())));
		buffer.write("</close>");
		buffer.write("<id>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getId())));
		buffer.write("</id>");
		buffer.write("<lat_tokyo>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLatTokyo())));
		buffer.write("</lat_tokyo>");
		buffer.write("<lat_world>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLatWorld())));
		buffer.write("</lat_world>");
		buffer.write("<lng_tokyo>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLngTokyo())));
		buffer.write("</lng_tokyo>");
		buffer.write("<lng_world>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLngWorld())));
		buffer.write("</lng_world>");
		buffer.write("<name>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.write("</name>");
		buffer.write("<name_kana>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getNameKana())));
		buffer.write("</name_kana>");
		buffer.write("<open>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getOpen())));
		buffer.write("</open>");
		buffer.write("<private_room>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getPrivateRoom())));
		buffer.write("</private_room>");
		buffer.write("<type>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getType())));
		buffer.write("</type>");
		buffer.write("<url_mobile>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlMobile())));
		buffer.write("</url_mobile>");
		buffer.write("<url_pc>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPc())));
		buffer.write("</url_pc>");
		buffer.write("<url_photo_k1>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoK1())));
		buffer.write("</url_photo_k1>");
		buffer.write("<url_photo_l1>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL1())));
		buffer.write("</url_photo_l1>");
		buffer.write("<url_photo_l2>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL2())));
		buffer.write("</url_photo_l2>");
		buffer.write("<url_photo_s1>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS1())));
		buffer.write("</url_photo_s1>");
		buffer.write("<url_photo_s2>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS2())));
		buffer.write("</url_photo_s2>");
		buffer.write("<url_photo_s3>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS3())));
		buffer.write("</url_photo_s3>");
		buffer.write("<url_photo_s4>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS4())));
		buffer.write("</url_photo_s4>");
		buffer.write("</shop>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<shop");
		buffer.print(">");
		buffer.print("<access>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getAccess())));
		buffer.print("</access>");
		buffer.print("<address>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getAddress())));
		buffer.print("</address>");
		buffer.print("<budget>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getBudget())));
		buffer.print("</budget>");
		buffer.print("<capacity>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getCapacity())));
		buffer.print("</capacity>");
		buffer.print("<close>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getClose())));
		buffer.print("</close>");
		buffer.print("<id>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getId())));
		buffer.print("</id>");
		buffer.print("<lat_tokyo>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLatTokyo())));
		buffer.print("</lat_tokyo>");
		buffer.print("<lat_world>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLatWorld())));
		buffer.print("</lat_world>");
		buffer.print("<lng_tokyo>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLngTokyo())));
		buffer.print("</lng_tokyo>");
		buffer.print("<lng_world>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLngWorld())));
		buffer.print("</lng_world>");
		buffer.print("<name>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.print("</name>");
		buffer.print("<name_kana>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getNameKana())));
		buffer.print("</name_kana>");
		buffer.print("<open>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getOpen())));
		buffer.print("</open>");
		buffer.print("<private_room>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getPrivateRoom())));
		buffer.print("</private_room>");
		buffer.print("<type>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getType())));
		buffer.print("</type>");
		buffer.print("<url_mobile>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlMobile())));
		buffer.print("</url_mobile>");
		buffer.print("<url_pc>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPc())));
		buffer.print("</url_pc>");
		buffer.print("<url_photo_k1>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoK1())));
		buffer.print("</url_photo_k1>");
		buffer.print("<url_photo_l1>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL1())));
		buffer.print("</url_photo_l1>");
		buffer.print("<url_photo_l2>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoL2())));
		buffer.print("</url_photo_l2>");
		buffer.print("<url_photo_s1>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS1())));
		buffer.print("</url_photo_s1>");
		buffer.print("<url_photo_s2>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS2())));
		buffer.print("</url_photo_s2>");
		buffer.print("<url_photo_s3>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS3())));
		buffer.print("</url_photo_s3>");
		buffer.print("<url_photo_s4>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getUrlPhotoS4())));
		buffer.print("</url_photo_s4>");
		buffer.print("</shop>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextAttribute(StringBuffer buffer) {
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextAttribute(Writer buffer) throws IOException {
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextAttribute(PrintWriter buffer) {
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getAccessAsString() {
		return (URelaxer.getString(getAccess()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getAddressAsString() {
		return (URelaxer.getString(getAddress()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getBudgetAsString() {
		return (URelaxer.getString(getBudget()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getCapacityAsString() {
		return (URelaxer.getString(getCapacity()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getCloseAsString() {
		return (URelaxer.getString(getClose()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getIdAsString() {
		return (URelaxer.getString(getId()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getLatTokyoAsString() {
		return (URelaxer.getString(getLatTokyo()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getLatWorldAsString() {
		return (URelaxer.getString(getLatWorld()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getLngTokyoAsString() {
		return (URelaxer.getString(getLngTokyo()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getLngWorldAsString() {
		return (URelaxer.getString(getLngWorld()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getNameAsString() {
		return (URelaxer.getString(getName()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getNameKanaAsString() {
		return (URelaxer.getString(getNameKana()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getOpenAsString() {
		return (URelaxer.getString(getOpen()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getPrivateRoomAsString() {
		return (URelaxer.getString(getPrivateRoom()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getTypeAsString() {
		return (URelaxer.getString(getType()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlMobileAsString() {
		return (URelaxer.getString(getUrlMobile()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPcAsString() {
		return (URelaxer.getString(getUrlPc()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoK1AsString() {
		return (URelaxer.getString(getUrlPhotoK1()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoL1AsString() {
		return (URelaxer.getString(getUrlPhotoL1()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoL2AsString() {
		return (URelaxer.getString(getUrlPhotoL2()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoS1AsString() {
		return (URelaxer.getString(getUrlPhotoS1()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoS2AsString() {
		return (URelaxer.getString(getUrlPhotoS2()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoS3AsString() {
		return (URelaxer.getString(getUrlPhotoS3()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getUrlPhotoS4AsString() {
		return (URelaxer.getString(getUrlPhotoS4()));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setAccessByString(String string) {
		setAccess(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setAddressByString(String string) {
		setAddress(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setBudgetByString(String string) {
		setBudget(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setCapacityByString(String string) {
		setCapacity(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setCloseByString(String string) {
		setClose(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setIdByString(String string) {
		setId(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setLatTokyoByString(String string) {
		setLatTokyo(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setLatWorldByString(String string) {
		setLatWorld(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setLngTokyoByString(String string) {
		setLngTokyo(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setLngWorldByString(String string) {
		setLngWorld(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setNameByString(String string) {
		setName(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setNameKanaByString(String string) {
		setNameKana(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setOpenByString(String string) {
		setOpen(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setPrivateRoomByString(String string) {
		setPrivateRoom(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setTypeByString(String string) {
		setType(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlMobileByString(String string) {
		setUrlMobile(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPcByString(String string) {
		setUrlPc(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoK1ByString(String string) {
		setUrlPhotoK1(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoL1ByString(String string) {
		setUrlPhotoL1(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoL2ByString(String string) {
		setUrlPhotoL2(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoS1ByString(String string) {
		setUrlPhotoS1(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoS2ByString(String string) {
		setUrlPhotoS2(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoS3ByString(String string) {
		setUrlPhotoS3(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setUrlPhotoS4ByString(String string) {
		setUrlPhotoS4(string);
	}

	/**
	 * Returns a String representation of this object. While this method informs
	 * as XML format representaion, it's purpose is just information, not making
	 * a rigid XML documentation.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return (makeTextDocument());
		} catch (Exception e) {
			return (super.toString());
		}
	}

	/**
	 * Tests if a Element <code>element</code> is valid for the
	 * <code>Shop</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "shop")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "access")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "address")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "budget")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "capacity")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "close")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "id")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "lat_tokyo")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "lat_world")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "lng_tokyo")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "lng_world")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "name")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "name_kana")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "open")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "private_room")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "type")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_mobile")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_pc")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_k1")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_l1")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_l2")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_s1")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_s2")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_s3")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "url_photo_s4")) {
			return (false);
		}
		$match$ = true;
		if (!target.isEmptyElement()) {
			return (false);
		}
		return (true);
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Shop</code>. This mehtod is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 * @return boolean
	 */
	public static boolean isMatch(RStack stack) {
		Element element = stack.peekElement();
		if (element == null) {
			return (false);
		}
		return (isMatch(element));
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Shop</code>. This method consumes the stack contents during
	 * matching operation. This mehtod is supposed to be used internally by the
	 * Relaxer system.
	 *
	 * @param stack
	 * @return boolean
	 */
	public static boolean isMatchHungry(RStack stack) {
		Element element = stack.peekElement();
		if (element == null) {
			return (false);
		}
		if (isMatch(element)) {
			stack.popElement();
			return (true);
		} else {
			return (false);
		}
	}
}
