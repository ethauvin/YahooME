/*
 * @(#)HelpScreen.java
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


/**
 * The <code>HelpScreen</code> class implements a form used to display some help.
 *
 * @author  <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 * @created October 8, 2004
 * @since   1.0
 */
public class HelpScreen extends Form
{
	/**
	 * Creates a new HelpScreen instance.
	 *
	 * @param midlet The MIDlet instance.
	 */
	public HelpScreen(YahooME midlet)
	{
		super(YahooME.APP_NAME + " Help");

		append(new StringItem(YahooME.CHOICE_YAHOO_SMS + ": ",
							  "Instantly find any local business, from pizza parlors to florists. Type: [type of business] [city] [state] or [type of business] [zip code] or [name of business] [city] [state] or [name of business] [zip code]\n\n"));
		append(new StringItem(YahooME.CHOICE_YAHOO_LOCAL + ": ",
							  "Instantly find any local business, from pizza parlors to florists. Type: [type of business] or [name of business]. The permanent location setting is automatically added to your query.\n\n"));
		append(new StringItem(YahooME.CHOICE_STOCK + ": ", "Get the price of any stock with links to quotes, charts, news, and more. Type: [ticker symbol]\n\n"));
		append(new StringItem(YahooME.CHOICE_HOROSCOPE + ": ",
							  "Are the stars aligned for you today? Type: [sign]\n\n"));
		append(new StringItem(YahooME.CHOICE_WIFI + ": ",
							  "Find the closest WIFI hotspot. Type [zip code] or [city] [state]\n\n"));
		append(new StringItem(YahooME.CHOICE_AREA_CODE + ": ",
							  "Find out where your call's coming from. Type: [3-digit area code]\n\n"));
		append(new StringItem(YahooME.CHOICE_DEFINITION + ": ", "Get the definition of any English word. Type: [word]\n\n"));
		append(new StringItem(YahooME.CHOICE_ZIP + ": ", "Find the zip codes for any city. Type: [city]\n\n"));

		append(new StringItem(YahooME.CHOICE_WEATHER + ": ",
							  "Get current conditions and a 3-day forecast for over 50,000 cities worldwide. Type: weather [zip code] or weather [city] [state].\n\n"));

		addCommand(YahooME.COMMAND_BACK);

		setCommandListener(midlet);
	}
}
