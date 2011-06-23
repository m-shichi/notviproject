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
 * <b>Response</b> is generated from station.rng by Relaxer. This class is
 * derived from:
 *
 * <!-- for programmer <element name="response"> <oneOrMore> <ref
 * name="station"/> </oneOrMore> </element> --> <!-- for javadoc -->
 *
 * <pre>
 * &lt;element name="response"&gt;
 *   &lt;oneOrMore&gt;
 *     &lt;ref name="station"/&gt;
 *   &lt;/oneOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version station.rng (Mon Dec 20 16:00:49 JST 2010)
 * @author Relaxer 1.0 (http://www.relaxer.org)
 */
public class StationResponse implements java.io.Serializable, Cloneable {
	// List<Station>
	private java.util.List station_ = new java.util.ArrayList();

	/**
	 * Creates a <code>Response</code>.
	 *
	 */
	public StationResponse() {
	}

	/**
	 * Creates a <code>Response</code>.
	 *
	 * @param source
	 */
	public StationResponse(StationResponse source) {
		setup(source);
	}

	/**
	 * Creates a <code>Response</code> by the Stack <code>stack</code> that
	 * contains Elements. This constructor is supposed to be used internally by
	 * the Relaxer system.
	 *
	 * @param stack
	 */
	public StationResponse(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Response</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public StationResponse(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Response</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public StationResponse(Element element) {
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
	public StationResponse(File file) throws IOException, SAXException, ParserConfigurationException {
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
	public StationResponse(String uri) throws IOException, SAXException, ParserConfigurationException {
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
	public StationResponse(URL url) throws IOException, SAXException, ParserConfigurationException {
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
	public StationResponse(InputStream in) throws IOException, SAXException, ParserConfigurationException {
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
	public StationResponse(InputSource is) throws IOException, SAXException, ParserConfigurationException {
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
	public StationResponse(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Response</code> by the Response <code>source</code>
	 * .
	 *
	 * @param source
	 */
	public void setup(StationResponse source) {
		int size;
		this.station_.clear();
		size = source.station_.size();
		for (int i = 0; i < size; i++) {
			addStation((Station) source.getStation(i).clone());
		}
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
		station_.clear();
		while (true) {
			if (Station.isMatch(stack)) {
				addStation(new Station(stack));
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
		return (new StationResponse(this));
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
		size = this.station_.size();
		for (int i = 0; i < size; i++) {
			Station value = (Station) this.station_.get(i);
			value.makeElement(element);
		}
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
	 * Gets the Station property <b>station</b>.
	 *
	 * @return Station[]
	 */
	public final Station[] getStation() {
		Station[] array = new Station[station_.size()];
		return ((Station[]) station_.toArray(array));
	}

	/**
	 * Sets the Station property <b>station</b>.
	 *
	 * @param station
	 */
	public final void setStation(Station[] station) {
		this.station_.clear();
		for (int i = 0; i < station.length; i++) {
			addStation(station[i]);
		}
	}

	/**
	 * Sets the Station property <b>station</b>.
	 *
	 * @param station
	 */
	public final void setStation(Station station) {
		this.station_.clear();
		addStation(station);
	}

	/**
	 * Adds the Station property <b>station</b>.
	 *
	 * @param station
	 */
	public final void addStation(Station station) {
		this.station_.add(station);
	}

	/**
	 * Adds the Station property <b>station</b>.
	 *
	 * @param station
	 */
	public final void addStation(Station[] station) {
		for (int i = 0; i < station.length; i++) {
			addStation(station[i]);
		}
	}

	/**
	 * Gets number of the Station property <b>station</b>.
	 *
	 * @return int
	 */
	public final int sizeStation() {
		return (station_.size());
	}

	/**
	 * Gets the Station property <b>station</b> by index.
	 *
	 * @param index
	 * @return Station
	 */
	public final Station getStation(int index) {
		return ((Station) station_.get(index));
	}

	/**
	 * Sets the Station property <b>station</b> by index.
	 *
	 * @param index
	 * @param station
	 */
	public final void setStation(int index, Station station) {
		this.station_.set(index, station);
	}

	/**
	 * Adds the Station property <b>station</b> by index.
	 *
	 * @param index
	 * @param station
	 */
	public final void addStation(int index, Station station) {
		this.station_.add(index, station);
	}

	/**
	 * Remove the Station property <b>station</b> by index.
	 *
	 * @param index
	 */
	public final void removeStation(int index) {
		this.station_.remove(index);
	}

	/**
	 * Remove the Station property <b>station</b> by object.
	 *
	 * @param station
	 */
	public final void removeStation(Station station) {
		this.station_.remove(station);
	}

	/**
	 * Clear the Station property <b>station</b>.
	 *
	 */
	public final void clearStation() {
		this.station_.clear();
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
		size = this.station_.size();
		for (int i = 0; i < size; i++) {
			Station value = (Station) this.station_.get(i);
			value.makeTextElement(buffer);
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
		size = this.station_.size();
		for (int i = 0; i < size; i++) {
			Station value = (Station) this.station_.get(i);
			value.makeTextElement(buffer);
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
		size = this.station_.size();
		for (int i = 0; i < size; i++) {
			Station value = (Station) this.station_.get(i);
			value.makeTextElement(buffer);
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
		if (!Station.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		while (true) {
			if (!Station.isMatchHungry(target)) {
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
