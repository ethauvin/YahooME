/*
 * @(#)Util.java
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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;


/**
 * The <code>Util</code> class implements methods specific to MIDP 1.x.
 *
 * @author  <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 * @created October 8, 2004
 * @since   1.0
 */
public class Util
{
	/**
	 * Disables the default constructor.
	 *
	 * @throws UnsupportedOperationException if the constructor is called.
	 */
	private Util()
	{
		;
	}

	/**
	 * Returns a new exclusive choice group.
	 *
	 * @param  title   The title.
	 * @param  choices The choices.
	 *
	 * @return The choice group.
	 */
	public static ChoiceGroup getChoiceGroup(String title, String[] choices)
	{
		return new ChoiceGroup(title, Choice.EXCLUSIVE, choices, null);
	}

	/**
	 * Sets the current item.
	 *
	 * @param screen The screen.
	 * @param item   The item.
	 */
	public static void setCurrentItem(Display screen, Item item)
	{
		; // Do nothing
	}

	/**
	 * Sets the initial input mode of the given text field.
	 *
	 * @param field     The text field.
	 * @param inputMode The input mode.
	 */
	public static void setInitialInputMode(TextField field, String inputMode)
	{
		; // Do nothing
	}
}
