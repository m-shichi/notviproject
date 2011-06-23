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
package info.clockworksapple.android.barsearch.relax.station.station;

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
 * <b>Prev</b> is generated from station.rng by Relaxer. This class is derived
 * from:
 *
 * <!-- for programmer <element name="prev"> <optional> <attribute name="nil">
 * <data type="boolean"/> </attribute> </optional> <data type="token"/>
 * </element> --> <!-- for javadoc -->
 *
 * <pre>
 * &lt;element name="prev"&gt;
 *   &lt;optional&gt;
 *     &lt;attribute name="nil"&gt;
 *       &lt;data type="boolean"/&gt;
 *     &lt;/attribute&gt;
 *   &lt;/optional&gt;
 *   &lt;data type="token"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version station.rng (Mon Dec 20 16:00:49 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class Prev implements java.io.Serializable, Cloneable {
	private String content_;
	private Boolean nil_;

	/**
	 * Creates a <code>Prev</code>.
	 *
	 */
	public Prev() {
	}

	/**
	 * Creates a <code>Prev</code>.
	 *
	 * @param source
	 */
	public Prev(Prev source) {
		setup(source);
	}

	/**
	 * Creates a <code>Prev</code> by the Stack <code>stack</code> that contains
	 * Elements. This constructor is supposed to be used internally by the
	 * Relaxer system.
	 *
	 * @param stack
	 */
	public Prev(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Prev</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Prev(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Prev</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Prev(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Prev</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Prev</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Prev</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Prev</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Prev</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Prev</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Prev(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Prev</code> by the Prev <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Prev source) {
		int size;
		setContent(source.getContent());
		setNil(source.getNil());
	}

	/**
	 * Initializes the <code>Prev</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Prev</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Prev</code> by the Stack <code>stack</code> that
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
		content_ = URelaxer.getElementPropertyAsString(element);
		nil_ = URelaxer.getAttributePropertyAsBooleanObject(element, "nil");
	}

	/**
	 * @return Object
	 */
	@Override
	public Object clone() {
		return (new Prev(this));
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
		Element element = doc.createElement("prev");
		URelaxer.setElementPropertyByString(element, this.content_);
		int size;
		if (this.nil_ != null) {
			URelaxer.setAttributePropertyByBoolean(element, "nil", this.nil_);
		}
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Prev</code> by the File <code>file</code>.
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
	 * Initializes the <code>Prev</code> by the String representation of URI
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
	 * Initializes the <code>Prev</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Prev</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Prev</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Prev</code> by the Reader <code>reader</code>.
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
	 * Gets the String property <b>content</b>.
	 *
	 * @return String
	 */
	public final String getContent() {
		return (content_);
	}

	/**
	 * Sets the String property <b>content</b>.
	 *
	 * @param content
	 */
	public final void setContent(String content) {
		this.content_ = content;
	}

	/**
	 * Gets the boolean property <b>nil</b>.
	 *
	 * @return boolean
	 */
	public boolean getNil() {
		if (nil_ == null) {
			return (false);
		}
		return (nil_.booleanValue());
	}

	/**
	 * Gets the boolean property <b>nil</b>.
	 *
	 * @param nil
	 * @return boolean
	 */
	public boolean getNil(boolean nil) {
		if (nil_ == null) {
			return (nil);
		}
		return (this.nil_.booleanValue());
	}

	/**
	 * Gets the boolean property <b>nil</b>.
	 *
	 * @return Boolean
	 */
	public Boolean getNilAsBoolean() {
		return (nil_);
	}

	/**
	 * Check the boolean property <b>nil</b>.
	 *
	 * @return boolean
	 */
	public boolean checkNil() {
		return (nil_ != null);
	}

	/**
	 * Sets the boolean property <b>nil</b>.
	 *
	 * @param nil
	 */
	public void setNil(boolean nil) {
		this.nil_ = new Boolean(nil);
	}

	/**
	 * Sets the boolean property <b>nil</b>.
	 *
	 * @param nil
	 */
	public void setNil(Boolean nil) {
		this.nil_ = nil;
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
		buffer.append("<prev");
		if (nil_ != null) {
			buffer.append(" nil=\"");
			buffer.append(URelaxer.getString(getNil()));
			buffer.append("\"");
		}
		buffer.append(">");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getContent())));
		buffer.append("</prev>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<prev");
		if (nil_ != null) {
			buffer.write(" nil=\"");
			buffer.write(URelaxer.getString(getNil()));
			buffer.write("\"");
		}
		buffer.write(">");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getContent())));
		buffer.write("</prev>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<prev");
		if (nil_ != null) {
			buffer.print(" nil=\"");
			buffer.print(URelaxer.getString(getNil()));
			buffer.print("\"");
		}
		buffer.print(">");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getContent())));
		buffer.print("</prev>");
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
	public String getContentAsString() {
		return (URelaxer.getString(getContent()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getNilAsString() {
		return (URelaxer.getString(getNil()));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setContentByString(String string) {
		setContent(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setNilByString(String string) {
		setNil(new Boolean(string).booleanValue());
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
	 * <code>Prev</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "prev")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		if (!target.isEmptyElement()) {
			return (false);
		}
		return (true);
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Prev</code>. This mehtod is supposed to be used internally by
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
	 * the <code>Prev</code>. This method consumes the stack contents during
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
