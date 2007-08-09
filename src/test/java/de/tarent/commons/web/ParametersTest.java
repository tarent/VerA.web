/*
 * tarent commons,
 * a set of common components and solutions
 * Copyright (c) 2006-2007 tarent GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License,version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'tarent commons'
 * Signature of Elmar Geese, 14 June 2007
 * Elmar Geese, CEO tarent GmbH.
 */

/**
 * 
 */
package de.tarent.commons.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import de.tarent.commons.utils.StringTools;

import junit.framework.TestCase;

/**
 * @author tim
 * 
 * Unit test for {@link Parameters}.
 *
 */
public class ParametersTest extends TestCase {
	/**
	 * Test method for {@link de.tarent.commons.web.Parameters#encodeUrl(java.lang.String)}.
	 * @throws UnsupportedEncodingException 
	 */
	public void testEncodeUrl() throws UnsupportedEncodingException {
		Parameters parameters = new Parameters();
		parameters.put("key1", "value1");
		parameters.put("key2", "value2");
		
		String url="http://www.tarent.de";
		String paramString1 = "key1=value1";
		String paramString2 = "key2=value2";
		assertEquals("General problems", url + "?" + paramString1 + "&amp;" + paramString2, parameters.encodeUrl(url));
		assertEquals("Problems when already parameters exists in URL", url + "?a=b&amp;" + paramString1 + "&amp;" + paramString2, parameters.encodeUrl(url + "?a=b"));
		assertEquals("Problems with withespaces", url + "?" + paramString1 + "&amp;" + paramString2, parameters.encodeUrl(" " + url + " "));
		parameters.put("key2", "v alue2");
		assertEquals("Problems with string encoding", url + "?" + paramString1 + "&amp;key2=v+alue2", parameters.encodeUrl(url));
		parameters = new Parameters();
		assertEquals("Problems with empty parameter set", url, parameters.encodeUrl(url));
	}

	/**
	 * Test method for {@link de.tarent.commons.web.Parameters#encodeUrl(java.lang.String)}.
	 * @throws UnsupportedEncodingException 
	 */
	public void testEncodeUrlDecode() throws UnsupportedEncodingException {
		Parameters parameters = new Parameters();
		parameters.setNeedsEncoding(false);
		parameters.put("a", URLEncoder.encode("\u00e4\u00fc\u00f6\u00df?$", parameters.getDefaultEncoding()));
		String url="http://www.tarent.de";
		String paramString1 = "a=\u00e4\u00fc\u00f6\u00df?$";
		parameters.decodeAll();
		assertEquals("General problems", url + "?" + paramString1, parameters.encodeUrl(url));
	}

	/**
	 * Test method for {@link de.tarent.commons.web.Parameters#getHiddenHTMLFormFields()}.
	 * @throws UnsupportedEncodingException 
	 */
	public void testGetHiddenHTMLFormFields() throws UnsupportedEncodingException {
		Parameters parameters = new Parameters();
		parameters.put("key1", "value1");
		parameters.put("key2", "value2");
		
		assertEquals("General problems", 
				"<input type=\"hidden\" name=\"key1\" value=\"value1\">" + StringTools.LINE_SEPERATOR +
				"<input type=\"hidden\" name=\"key2\" value=\"value2\">" + StringTools.LINE_SEPERATOR,
				parameters.getHiddenHTMLFormFields()) ;
		parameters.put("key2", "v alue2");		
		parameters.put("key2", "v alue2");
		assertEquals("Problems with string encoding",
				"<input type=\"hidden\" name=\"key1\" value=\"value1\">" + StringTools.LINE_SEPERATOR +
				"<input type=\"hidden\" name=\"key2\" value=\"v+alue2\">" + StringTools.LINE_SEPERATOR,
				parameters.getHiddenHTMLFormFields()) ;
		parameters = new Parameters();
		assertEquals("Problems with empty parameter set", "", parameters.getHiddenHTMLFormFields());
	}

	public void testNullValues() throws UnsupportedEncodingException {
		Parameters parameters = new Parameters();
		parameters.put(null, "value1");
		parameters.put("key2", null);
		
		parameters.encodeUrl("http://www.tarent.de");
		parameters.encodeUrl("http://www.tarent.de?");
		
		assertTrue(true);
	}
}