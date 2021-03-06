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
package info.clockworksapple.android.barsearch.relax.station.prefecture;

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
 * <b>Response</b> is generated from prefecture.rng by Relaxer. This class is
 * derived from:
 *
 * <!-- for programmer <element name="response"> <oneOrMore> <element
 * name="prefecture"> <data type="token"/> </element> </oneOrMore> </element>
 * --> <!-- for javadoc -->
 *
 * <pre>
 * &lt;element name="response"&gt;
 *   &lt;oneOrMore&gt;
 *     &lt;element name="prefecture"&gt;
 *       &lt;data type="token"/&gt;
 *     &lt;/element&gt;
 *   &lt;/oneOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version prefecture.rng (Mon Dec 20 13:33:58 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class PrefectureResponse implements java.io.Serializable, Cloneable {
	// List<String>
	private java.util.List prefecture_ = new java.util.ArrayList();

	/**
	 * Creates a <code>Response</code>.
	 *
	 */
	public PrefectureResponse() {
	}

	/**
	 * Creates a <code>Response</code>.
	 *
	 * @param source
	 */
	public PrefectureResponse(PrefectureResponse source) {
		setup(source);
	}

	/**
	 * Creates a <code>Response</code> by the Stack <code>stack</code> that
	 * contains Elements. This constructor is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 */
	public PrefectureResponse(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Response</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public PrefectureResponse(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Response</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public PrefectureResponse(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Response</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Response</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Response</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Response</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Response</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Response</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public PrefectureResponse(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Response</code> by the Response <code>source</code>
	 * .
	 *
	 * @param source
	 */
	public void setup(PrefectureResponse source) {
		int size;
		setPrefecture(source.getPrefecture());
	}

	/**
	 * Initializes the <code>Response</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Response</code> by the Element <code>element</code>
	 * .
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Response</code> by the Stack <code>stack</code>
	 * that contains Elements. This constructor is supposed to be used
	 * internally by the Relaxer system.
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
		prefecture_ = URelaxer.getElementPropertyAsStringListByStack(stack, "prefecture");
	}

	/**
	 * @return Object
	 */
	@Override
	public Object clone() {
		return (new PrefectureResponse(this));
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
		Element element = doc.createElement("response");
		int size;
		URelaxer.setElementPropertyByStringList(element, "prefecture", this.prefecture_);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Response</code> by the File <code>file</code>.
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
	 * Initializes the <code>Response</code> by the String representation of URI
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
	 * Initializes the <code>Response</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Response</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Response</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Response</code> by the Reader <code>reader</code>.
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
	 * Gets the String property <b>prefecture</b>.
	 *
	 * @return String[]
	 */
	public final String[] getPrefecture() {
		String[] array = new String[prefecture_.size()];
		return ((String[]) prefecture_.toArray(array));
	}

	/**
	 * Sets the String property <b>prefecture</b>.
	 *
	 * @param prefecture
	 */
	public final void setPrefecture(String[] prefecture) {
		this.prefecture_.clear();
		for (int i = 0; i < prefecture.length; i++) {
			addPrefecture(prefecture[i]);
		}
	}

	/**
	 * Sets the String property <b>prefecture</b>.
	 *
	 * @param prefecture
	 */
	public final void setPrefecture(String prefecture) {
		this.prefecture_.clear();
		addPrefecture(prefecture);
	}

	/**
	 * Adds the String property <b>prefecture</b>.
	 *
	 * @param prefecture
	 */
	public final void addPrefecture(String prefecture) {
		this.prefecture_.add(prefecture);
	}

	/**
	 * Adds the String property <b>prefecture</b>.
	 *
	 * @param prefecture
	 */
	public final void addPrefecture(String[] prefecture) {
		for (int i = 0; i < prefecture.length; i++) {
			addPrefecture(prefecture[i]);
		}
	}

	/**
	 * Gets number of the String property <b>prefecture</b>.
	 *
	 * @return int
	 */
	public final int sizePrefecture() {
		return (prefecture_.size());
	}

	/**
	 * Gets the String property <b>prefecture</b> by index.
	 *
	 * @param index
	 * @return String
	 */
	public final String getPrefecture(int index) {
		return ((String) prefecture_.get(index));
	}

	/**
	 * Sets the String property <b>prefecture</b> by index.
	 *
	 * @param index
	 * @param prefecture
	 */
	public final void setPrefecture(int index, String prefecture) {
		this.prefecture_.set(index, prefecture);
	}

	/**
	 * Adds the String property <b>prefecture</b> by index.
	 *
	 * @param index
	 * @param prefecture
	 */
	public final void addPrefecture(int index, String prefecture) {
		this.prefecture_.add(index, prefecture);
	}

	/**
	 * Remove the String property <b>prefecture</b> by index.
	 *
	 * @param index
	 */
	public final void removePrefecture(int index) {
		this.prefecture_.remove(index);
	}

	/**
	 * Remove the String property <b>prefecture</b> by object.
	 *
	 * @param prefecture
	 */
	public final void removePrefecture(String prefecture) {
		this.prefecture_.remove(prefecture);
	}

	/**
	 * Clear the String property <b>prefecture</b>.
	 *
	 */
	public final void clearPrefecture() {
		this.prefecture_.clear();
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
		buffer.append("<response");
		buffer.append(">");
		size = sizePrefecture();
		for (int i = 0; i < size; i++) {
			buffer.append("<prefecture>");
			buffer.append(URelaxer.escapeCharData(URelaxer.getString(getPrefecture(i))));
			buffer.append("</prefecture>");
		}
		buffer.append("</response>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<response");
		buffer.write(">");
		size = sizePrefecture();
		for (int i = 0; i < size; i++) {
			buffer.write("<prefecture>");
			buffer.write(URelaxer.escapeCharData(URelaxer.getString(getPrefecture(i))));
			buffer.write("</prefecture>");
		}
		buffer.write("</response>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<response");
		buffer.print(">");
		size = sizePrefecture();
		for (int i = 0; i < size; i++) {
			buffer.print("<prefecture>");
			buffer.print(URelaxer.escapeCharData(URelaxer.getString(getPrefecture(i))));
			buffer.print("</prefecture>");
		}
		buffer.print("</response>");
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
	 * Gets the property value as String array.
	 *
	 * @return String[]
	 */
	public String[] getPrefectureAsString() {
		int size = sizePrefecture();
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = URelaxer.getString(getPrefecture(i));
		}
		return (array);
	}

	/**
	 * Gets the property value by index as String.
	 *
	 * @param index
	 * @return String
	 */
	public String getPrefectureAsString(int index) {
		return (URelaxer.getString(getPrefecture(index)));
	}

	/**
	 * Sets the property value by String array.
	 *
	 * @param strings
	 */
	public void setPrefectureByString(String[] strings) {
		if (strings.length > 0) {
			String string = strings[0];
			setPrefecture(string);
			for (int i = 1; i < strings.length; i++) {
				string = strings[i];
				addPrefecture(string);
			}
		}
	}

	/**
	 * Sets the property value by String via index.
	 *
	 * @param index
	 * @param value
	 */
	public void setPrefectureByString(int index, String value) {
		setPrefecture(index, value);
	}

	/**
	 * Adds the property value by String.
	 *
	 * @param string
	 */
	public void addPrefectureByString(String string) {
		addPrefecture(string);
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
	 * <code>Response</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "response")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "prefecture")) {
			return (false);
		}
		$match$ = true;
		while ((child = target.peekElement()) != null) {
			if (!URelaxer.isTargetElement(child, "prefecture")) {
				break;
			}
			target.popElement();
			$match$ = true;
		}
		if (!target.isEmptyElement()) {
			return (false);
		}
		return (true);
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Response</code>. This mehtod is supposed to be used internally
	 * by the Relaxer system.
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
	 * the <code>Response</code>. This method consumes the stack contents during
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
