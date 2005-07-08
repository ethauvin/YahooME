/*
 * @(#)PrefsScreen.java
 *
 * Copyright (c) 2004-2005, Erik C. Thauvin (http://www.thauvin.net/erik/)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the authors nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id$
 *
 */
package net.thauvin.j2me.yahoome;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;


/**
 * The <code>PrefsScreen</code> class implements a form used to set the preferences.
 *
 * @author  Erik C. Thauvin
 * @version $Revision$, $Date$
 * @created Oct 12, 2004
 * @since   1.0
 */
public class PrefsScreen extends Form
{
	/**
	 * The query field.
	 */
	protected /* final */ TextField locationFld;

	/**
	 * The SMS number field.
	 */
	protected /* final */ TextField smsFld;

	/**
	 * Creates a new PrefsScreen object.
	 *
	 * @param midlet The MIDlet instance.
	 */
	public PrefsScreen(YahooME midlet)
	{
		super(YahooME.APP_NAME + " Settings");

		locationFld = new TextField("Location: ", "", 25, TextField.ANY);

		append(locationFld);
		append(new StringItem("", "Your current city and state or zip. (e.g.: san francisco ca or 94123)"));

		smsFld = new TextField("Yahoo! SMS: ", "", 25, TextField.PHONENUMBER);

		append(smsFld);
		append(new StringItem("", "The short code or number of the Yahoo! SMS service. (e.g.: 92466)"));

		addCommand(YahooME.COMMAND_BACK);
		addCommand(YahooME.COMMAND_SAVE);

		setCommandListener(midlet);
	}
}
