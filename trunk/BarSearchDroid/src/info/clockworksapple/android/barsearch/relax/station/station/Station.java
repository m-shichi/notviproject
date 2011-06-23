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
 * <b>Station</b> is generated from station.rng by Relaxer. This class is
 * derived from:
 *
 * <!-- for programmer <element name="station"> <element name="prefecture">
 * <data type="token"/> </element> <element name="postal"> <data type="int"/>
 * </element> <element name="x"> <data type="float"/> </element> <element
 * name="next"> <data type="token"/> </element> <ref name="prev"/> <element
 * name="y"> <data type="float"/> </element> <element name="line"> <data
 * type="token"/> </element> <element name="name"> <data type="token"/>
 * </element> </element> --> <!-- for javadoc -->
 *
 * <pre>
 * &lt;element name="station"&gt;
 *   &lt;element name="prefecture"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="postal"&gt;
 *     &lt;data type="int"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="x"&gt;
 *     &lt;data type="float"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="next"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;ref name="prev"/&gt;
 *   &lt;element name="y"&gt;
 *     &lt;data type="float"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="line"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 *   &lt;element name="name"&gt;
 *     &lt;data type="token"/&gt;
 *   &lt;/element&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version station.rng (Mon Dec 20 16:00:49 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class Station implements java.io.Serializable, Cloneable {
	private String prefecture_;
	private int postal_;
	private float x_;
	private String next_;
	private Prev prev_;
	private float y_;
	private String line_;
	private String name_;

	/**
	 * Creates a <code>Station</code>.
	 *
	 */
	public Station() {
		prefecture_ = "";
		next_ = "";
		line_ = "";
		name_ = "";
	}

	/**
	 * Creates a <code>Station</code>.
	 *
	 * @param source
	 */
	public Station(Station source) {
		setup(source);
	}

	/**
	 * Creates a <code>Station</code> by the Stack <code>stack</code> that
	 * contains Elements. This constructor is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 */
	public Station(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Station</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Station(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Station</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Station(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Station</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Station</code> by the String representation of URI
	 * <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Station</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Station</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Station</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Station</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Station(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Station</code> by the Station <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Station source) {
		int size;
		setPrefecture(source.getPrefecture());
		setPostal(source.getPostal());
		setX(source.getX());
		setNext(source.getNext());
		if (source.prev_ != null) {
			setPrev((Prev) source.getPrev().clone());
		}
		setY(source.getY());
		setLine(source.getLine());
		setName(source.getName());
	}

	/**
	 * Initializes the <code>Station</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Station</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Station</code> by the Stack <code>stack</code> that
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
		prefecture_ = URelaxer.getElementPropertyAsString(stack.popElement());
		postal_ = URelaxer.getElementPropertyAsInt(stack.popElement());
		x_ = URelaxer.getElementPropertyAsFloat(stack.popElement());
		next_ = URelaxer.getElementPropertyAsString(stack.popElement());
		setPrev(new Prev(stack));
		y_ = URelaxer.getElementPropertyAsFloat(stack.popElement());
		line_ = URelaxer.getElementPropertyAsString(stack.popElement());
		name_ = URelaxer.getElementPropertyAsString(stack.popElement());
	}

	/**
	 * @return Object
	 */
	@Override
	public Object clone() {
		return (new Station(this));
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
		Element element = doc.createElement("station");
		int size;
		URelaxer.setElementPropertyByString(element, "prefecture", this.prefecture_);
		URelaxer.setElementPropertyByInt(element, "postal", this.postal_);
		URelaxer.setElementPropertyByFloat(element, "x", this.x_);
		URelaxer.setElementPropertyByString(element, "next", this.next_);
		this.prev_.makeElement(element);
		URelaxer.setElementPropertyByFloat(element, "y", this.y_);
		URelaxer.setElementPropertyByString(element, "line", this.line_);
		URelaxer.setElementPropertyByString(element, "name", this.name_);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Station</code> by the File <code>file</code>.
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
	 * Initializes the <code>Station</code> by the String representation of URI
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
	 * Initializes the <code>Station</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Station</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Station</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Station</code> by the Reader <code>reader</code>.
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
	 * @return String
	 */
	public final String getPrefecture() {
		return (prefecture_);
	}

	/**
	 * Sets the String property <b>prefecture</b>.
	 *
	 * @param prefecture
	 */
	public final void setPrefecture(String prefecture) {
		this.prefecture_ = prefecture;
	}

	/**
	 * Gets the int property <b>postal</b>.
	 *
	 * @return int
	 */
	public final int getPostal() {
		return (postal_);
	}

	/**
	 * Sets the int property <b>postal</b>.
	 *
	 * @param postal
	 */
	public final void setPostal(int postal) {
		this.postal_ = postal;
	}

	/**
	 * Gets the float property <b>x</b>.
	 *
	 * @return float
	 */
	public final float getX() {
		return (x_);
	}

	/**
	 * Sets the float property <b>x</b>.
	 *
	 * @param x
	 */
	public final void setX(float x) {
		this.x_ = x;
	}

	/**
	 * Gets the String property <b>next</b>.
	 *
	 * @return String
	 */
	public final String getNext() {
		return (next_);
	}

	/**
	 * Sets the String property <b>next</b>.
	 *
	 * @param next
	 */
	public final void setNext(String next) {
		this.next_ = next;
	}

	/**
	 * Gets the Prev property <b>prev</b>.
	 *
	 * @return Prev
	 */
	public final Prev getPrev() {
		return (prev_);
	}

	/**
	 * Sets the Prev property <b>prev</b>.
	 *
	 * @param prev
	 */
	public final void setPrev(Prev prev) {
		this.prev_ = prev;
	}

	/**
	 * Gets the float property <b>y</b>.
	 *
	 * @return float
	 */
	public final float getY() {
		return (y_);
	}

	/**
	 * Sets the float property <b>y</b>.
	 *
	 * @param y
	 */
	public final void setY(float y) {
		this.y_ = y;
	}

	/**
	 * Gets the String property <b>line</b>.
	 *
	 * @return String
	 */
	public final String getLine() {
		return (line_);
	}

	/**
	 * Sets the String property <b>line</b>.
	 *
	 * @param line
	 */
	public final void setLine(String line) {
		this.line_ = line;
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
		buffer.append("<station");
		buffer.append(">");
		buffer.append("<prefecture>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getPrefecture())));
		buffer.append("</prefecture>");
		buffer.append("<postal>");
		buffer.append(URelaxer.getString(getPostal()));
		buffer.append("</postal>");
		buffer.append("<x>");
		buffer.append(URelaxer.getString(getX()));
		buffer.append("</x>");
		buffer.append("<next>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getNext())));
		buffer.append("</next>");
		prev_.makeTextElement(buffer);
		buffer.append("<y>");
		buffer.append(URelaxer.getString(getY()));
		buffer.append("</y>");
		buffer.append("<line>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLine())));
		buffer.append("</line>");
		buffer.append("<name>");
		buffer.append(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.append("</name>");
		buffer.append("</station>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<station");
		buffer.write(">");
		buffer.write("<prefecture>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getPrefecture())));
		buffer.write("</prefecture>");
		buffer.write("<postal>");
		buffer.write(URelaxer.getString(getPostal()));
		buffer.write("</postal>");
		buffer.write("<x>");
		buffer.write(URelaxer.getString(getX()));
		buffer.write("</x>");
		buffer.write("<next>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getNext())));
		buffer.write("</next>");
		prev_.makeTextElement(buffer);
		buffer.write("<y>");
		buffer.write(URelaxer.getString(getY()));
		buffer.write("</y>");
		buffer.write("<line>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLine())));
		buffer.write("</line>");
		buffer.write("<name>");
		buffer.write(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.write("</name>");
		buffer.write("</station>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<station");
		buffer.print(">");
		buffer.print("<prefecture>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getPrefecture())));
		buffer.print("</prefecture>");
		buffer.print("<postal>");
		buffer.print(URelaxer.getString(getPostal()));
		buffer.print("</postal>");
		buffer.print("<x>");
		buffer.print(URelaxer.getString(getX()));
		buffer.print("</x>");
		buffer.print("<next>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getNext())));
		buffer.print("</next>");
		prev_.makeTextElement(buffer);
		buffer.print("<y>");
		buffer.print(URelaxer.getString(getY()));
		buffer.print("</y>");
		buffer.print("<line>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLine())));
		buffer.print("</line>");
		buffer.print("<name>");
		buffer.print(URelaxer.escapeCharData(URelaxer.getString(getName())));
		buffer.print("</name>");
		buffer.print("</station>");
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
	public String getPrefectureAsString() {
		return (URelaxer.getString(getPrefecture()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getPostalAsString() {
		return (URelaxer.getString(getPostal()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getXAsString() {
		return (URelaxer.getString(getX()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getNextAsString() {
		return (URelaxer.getString(getNext()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getYAsString() {
		return (URelaxer.getString(getY()));
	}

	/**
	 * Gets the property value as String.
	 *
	 * @return String
	 */
	public String getLineAsString() {
		return (URelaxer.getString(getLine()));
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
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setPrefectureByString(String string) {
		setPrefecture(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setPostalByString(String string) {
		setPostal(Integer.parseInt(string));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setXByString(String string) {
		setX(Float.parseFloat(string));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setNextByString(String string) {
		setNext(string);
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setYByString(String string) {
		setY(Float.parseFloat(string));
	}

	/**
	 * Sets the property value by String.
	 *
	 * @param string
	 */
	public void setLineByString(String string) {
		setLine(string);
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
	 * <code>Station</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "station")) {
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
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "postal")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "x")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "next")) {
			return (false);
		}
		$match$ = true;
		if (!Prev.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "y")) {
			return (false);
		}
		$match$ = true;
		child = target.popElement();
		if (child == null) {
			return (false);
		}
		if (!URelaxer.isTargetElement(child, "line")) {
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
		if (!target.isEmptyElement()) {
			return (false);
		}
		return (true);
	}

	/**
	 * Tests if elements contained in a Stack <code>stack</code> is valid for
	 * the <code>Station</code>. This mehtod is supposed to be used internally
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
	 * the <code>Station</code>. This method consumes the stack contents during
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
