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
 * <b>Shops</b> is generated from res_bar.rng by Relaxer. This class is derived
 * from:
 *
 * <!-- for programmer <element name="shops"> <element name="api_version"> <data
 * type="token"/> </element> <element name="results_available"> <data
 * type="token"/> </element> <element name="results_returned"> <data
 * type="token"/> </element> <element name="results_start"> <data type="token"/>
 * </element> <zeroOrMore> <ref name="shop"/> </zeroOrMore> </element> --> <!--
 * for javadoc -->
 *
 * <pre>
 * &lt;element name="shops"&gt;
 *   &lt;element name="api_version"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="results_available"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="results_returned"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="results_start"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="shop"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version res_bar.rng (Mon Dec 27 10:48:03 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class Shops implements java.io.Serializable, Cloneable {
	private String apiVersion_;
	private String resultsAvailable_;
	private String resultsReturned_;
	private String resultsStart_;
	// List<Shop>
	private java.util.List shop_ = new java.util.ArrayList();

	/**
	 * Creates a <code>Shops</code>.
	 *
	 */
	public Shops() {
		apiVersion_ = "";
		resultsAvailable_ = "";
		resultsReturned_ = "";
		resultsStart_ = "";
	}

	/**
	 * Creates a <code>Shops</code>.
	 *
	 * @param source
	 */
	public Shops(Shops source) {
		setup(source);
	}

	/**
	 * Creates a <code>Shops</code> by the Stack <code>stack</code> that
	 * contains Elements. This constructor is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 */
	public Shops(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Shops</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Shops(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Shops</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Shops(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Shops</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Shops</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Shops</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Shops</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Shops</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Shops</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Shops(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Shops</code> by the Shops <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Shops source) {
		int size;
		setApiVersion(source.getApiVersion());
		setResultsAvailable(source.getResultsAvailable());
		setResultsReturned(source.getResultsReturned());
		setResultsStart(source.getResultsStart());
		this.shop_.clear();
		size = source.shop_.size();
		for (int i = 0; i < size; i++) {
			addShop((Shop) source.getShop(i).clone());
		}
	}

	/**
	 * Initializes the <code>Shops</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Shops</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Shops</code> by the Stack <code>stack</code> that
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
		apiVersion_ = URelaxer.getElementPropertyAsString(stack.popElement());
		resultsAvailable_ = URelaxer.getElementPropertyAsString(stack.popElement());
		resultsReturned_ = URelaxer.getElementPropertyAsString(stack.popElement());
		resultsStart_ = URelaxer.getElementPropertyAsString(stack.popElement());
		shop_.clear();
		while (true) {
			if (Shop.isMatch(stack)) {
				addShop(new Shop(stack));
			} else {
				break;
			}
		}
	}

	/**
	 * @return Object
	 */
	@Override
	public Object clone() {
		return (new Shops(this));
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
		Element element = doc.createElement("shops");
		int size;
		URelaxer.setElementPropertyByString(element, "api_version", this.apiVersion_);
		URelaxer.setElementPropertyByString(element, "results_available", this.resultsAvailable_);
		URelaxer.setElementPropertyByString(element, "results_returned", this.resultsReturned_);
		URelaxer.setElementPropertyByString(element, "results_start", this.resultsStart_);
		size = this.shop_.size();
		for (int i = 0; i < size; i++) {
			Shop value = (Shop) this.shop_.get(i);
			value.makeElement(element);
		}
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Shops</code> by the File <code>file</code>.
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
	 * Initializes the <code>Shops</code> by the String representation of URI
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
	 * Initializes the <code>Shops</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Shops</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Shops</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Shops</code> by the Reader <code>reader</code>.
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
	 * Gets the String property <b>apiVersion</b>.
	 *
	 * @return String
	 */
	public final String getApiVersion() {
		return (apiVersion_);
	}

	/**
	 * Sets the String property <b>apiVersion</b>.
	 *
	 * @param apiVersion
	 */
	public final void setApiVersion(String apiVersion) {
		this.apiVersion_ = apiVersion;
	}

	/**
	 * Gets the String property <b>resultsAvailable</b>.
	 *
	 * @return String
	 */
	public final String getResultsAvailable() {
		return (resultsAvailable_);
	}

	/**
	 * Sets the String property <b>resultsAvailable</b>.
	 *
	 * @param resultsAvailable
	 */
	public final void setResultsAvailable(String resultsAvailable) {
		this.resultsAvailable_ = resultsAvailable;
	}

	/**
	 * Gets the String property <b>resultsReturned</b>.
	 *
	 * @return String
	 */
	public final String getResultsReturned() {
		return (resultsReturned_);
	}

	/**
	 * Sets the String property <b>resultsReturned</b>.
	 *
	 * @param resultsReturned
	 */
	public final void setResultsReturned(String resultsReturned) {
		this.resultsReturned_ = resultsReturned;
	}

	/**
	 * Gets the String property <b>resultsStart</b>.
	 *
	 * @return String
	 */
	public final String getResultsStart() {
		return (resultsStart_);
	}

	/**
	 * Sets the String property <b>resultsStart</b>.
	 *
	 * @param resultsStart
	 */
	public final void setResultsStart(String resultsStart) {
		this.resultsStart_ = resultsStart;
	}

	/**
	 * Gets the Shop property <b>shop</b>.
	 *
	 * @return Shop[]
	 */
	public final Shop[] getShop() {
		Shop[] array = new Shop[shop_.size()];
		return ((Shop[]) shop_.toArray(array));
	}

	/**
	 * Sets the Shop property <b>shop</b>.
	 *
	 * @param shop
	 */
	public final void setShop(Shop[] shop) {
		this.shop_.clear();
		for (int i = 0; i < shop.length; i++) {
			addShop(shop[i]);
		}
	}

	/**
	 * Sets the Shop property <b>shop</b>.
	 *
	 * @param shop
	 */
	public final void setShop(Shop shop) {
		this.shop_.clear();
		addShop(shop);
	}

	/**
	 * Adds the Shop property <b>shop</b>.
	 *
	 * @param shop
	 */
	public final void addShop(Shop shop) {
		this.shop_.add(shop);
	}

	/**
	 * Adds the Shop property <b>shop</b>.
	 *
	 * @param shop
	 */
	public final void addShop(Shop[] shop) {
		for (int i = 0; i < shop.length; i++) {
			addShop(shop[i]);
		}
	}

	/**
	 * Gets number of the Shop property <b>shop</b>.
	 *
	 * @return int
	 */
	public final int sizeShop() {
		return (shop_.size());
	}

	/**
	 * Gets the Shop property <b>shop</b> by index.
	 *
	 * @param index
	 * @return Shop
	 */
	public final Shop getShop(int index) {
		return ((Shop) shop_.get(index));
	}

	/**
	 * Sets the Shop property <b>shop</b> by index.
	 *
	 * @param index
	 * @param shop
	 */
	public final void setShop(int index, Shop shop) {
		this.shop_.set(index, shop);
	}

	/**
	 * Adds the Shop property <b>shop</b> by index.
	 *
	 * @param index
	 * @param shop
	 */
	public final void addShop(int index, Shop shop) {
		this.shop_.add(index, shop);
	}

	/**
	 * Remove the Shop property <b>shop</b> by index.
	 *
	 * @param index
	 */
	public final void removeShop(int index) {
		this.shop_.remove(index);
	}

	/**
	 * Remove the Shop property <b>shop</b> by object.
	 *
	 * @param shop
	 */
	public final void removeShop(Shop shop) {
		this.shop_.remove(shop);
	}

	/**
	 * Clear the Shop property <b>shop</b>.
	 *
	 */
	public final void clearShop() {
		this.shop_.clear();
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
		buffer.append("<shops");
		buffer.append(">");
		buffer.append("<api_version>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getApiVersion())));
		buffer.append("</api_version>");
		buffer.append("<results_available>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getResultsAvailable())));
		buffer.append("</results_available>");
		buffer.append("<results_returned>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getResultsReturned())));
		buffer.append("</results_returned>");
		buffer.append("<results_start>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getResultsStart())));
		buffer.append("</results_start>");
		size = this.shop_.size();
		for (int i = 0; i < size; i++) {
			Shop value = (Shop) this.shop_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.append("</shops>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<shops");
		buffer.write(">");
		buffer.write("<api_version>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getApiVersion())));
		buffer.write("</api_version>");
		buffer.write("<results_available>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getResultsAvailable())));
		buffer.write("</results_available>");
		buffer.write("<results_returned>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getResultsReturned())));
		buffer.write("</results_returned>");
		buffer.write("<results_start>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getResultsStart())));
		buffer.write("</results_start>");
		size = this.shop_.size();
		for (int i = 0; i < size; i++) {
			Shop value = (Shop) this.shop_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.write("</shops>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<shops");
		buffer.print(">");
		buffer.print("<api_version>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getApiVersion())));
		buffer.print("</api_version>");
		buffer.print("<results_available>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getResultsAvailable())));
		buffer.print("</results_available>");
		buffer.print("<results_returned>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getResultsReturned())));
		buffer.print("</results_returned>");
		buffer.print("<results_start>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getResultsStart())));
		buffer.print("</results_start>");
		size = this.shop_.size();
		for (int i = 0; i < size; i++) {
			Shop value = (Shop) this.shop_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.print("</shops>");
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
	public String getApiVersionAsString() {
		return (URelaxer.getString(getApiVersion()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getResultsAvailableAsString() {
		return (URelaxer.getString(getResultsAvailable()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getResultsReturnedAsString() {
		return (URelaxer.getString(getResultsReturned()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getResultsStartAsString() {
		return (URelaxer.getString(getResultsStart()));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setApiVersionByString(String string) {
		setApiVersion(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setResultsAvailableByString(String string) {
		setResultsAvailable(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setResultsReturnedByString(String string) {
		setResultsReturned(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setResultsStartByString(String string) {
		setResultsStart(string);
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
	 * <code>Shops</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "shops")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "api_version")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "results_available")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "results_returned")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "results_start")) {
			return (false);
		}
		$match$ = true;
		while (true) {
			if (!Shop.isMatchHungry(target)) {
				break;
			}
			$match$ = true;
		}
		if (!target.isEmptyElement()) {
			return (false);
		}
		return (true);
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Shops</code>. This mehtod is supposed to be used internally by
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
	 * the <code>Shops</code>. This method consumes the stack contents during
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
