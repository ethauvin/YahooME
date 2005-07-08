/*
 * @(#)YahooME.java
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

import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreException;

import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;


/**
 * The <code>YahooME</code> class implements a simple front-end for Yahoo! SMS.
 *
 * @author  <a href="http://www.thauvin.net/erik/">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 * @created October 8, 2004
 * @since   1.0
 */
public class YahooME extends MIDlet implements CommandListener, Runnable
{
	/**
	 * Definition choice.
	 */
	protected static final String CHOICE_DEFINITION = "Definitions";

	/**
	 * Stock Quote choice.
	 */
	protected static final String CHOICE_STOCK = "Stock Quotes";

	/**
	 * Local choice.
	 */
	protected static final String CHOICE_YAHOO_LOCAL = "Local Listings";

	/**
	 * Horoscope choice.
	 */
	protected static final String CHOICE_HOROSCOPE = "Daily Horoscope";

	/**
	 * Yahoo! SMS choice.
	 */
	protected static final String CHOICE_YAHOO_SMS = "Yahoo! SMS";

	/**
	 * Weather choice.
	 */
	protected static final String CHOICE_WEATHER = "Weather";

	/**
	 * Area Codes choice.
	 */
	protected static final String CHOICE_AREA_CODE = "Area Codes";

	/**
	 * WIFI Hotspots choice.
	 */
	protected static final String CHOICE_WIFI = "WIFI Hotspots";

	/**
	 * Zip Code choice.
	 */
	protected static final String CHOICE_ZIP = "Zip Codes";


	/**
	 * The <code>About</code> command.
	 */
	protected static final Command COMMAND_ABOUT = new Command("About", Command.SCREEN, 2);

	/**
	 * The application name.
	 */
	protected static final String APP_NAME = "YahooME";

	/**
	 * The application version.
	 */
	protected static final String APP_VERSION = "0.1";

	/**
	 * The <code>Back</code> command.
	 */
	protected static final Command COMMAND_BACK = new Command("Back", Command.BACK, 2);

	/**
	 * The action choices.
	 */
	protected static final String[] CHOICES = new String[]
											  {
												  CHOICE_YAHOO_SMS, CHOICE_YAHOO_LOCAL, CHOICE_WEATHER, CHOICE_STOCK,
												  CHOICE_DEFINITION, CHOICE_HOROSCOPE, CHOICE_WIFI, CHOICE_AREA_CODE,
												  CHOICE_ZIP
											  };

	/**
	 * The <code>Clear</code> command.
	 */
	protected static final Command COMMAND_CLEAR = new Command("Clear", Command.SCREEN, 2);

	/**
	 * The <code>Exit</code> command.
	 */
	protected static final Command COMMAND_EXIT = new Command("Exit", Command.EXIT, 2);

	/**
	 * The <code>Help</code> command.
	 */
	protected static final Command COMMAND_HELP = new Command("Help", Command.HELP, 2);

	/**
	 * The <code>History</code> command.
	 */
	protected static final Command COMMAND_HISTORY = new Command("History", Command.SCREEN, 2);

	/**
	 * The <code>Settings</code> command.
	 */
	protected static final Command COMMAND_SETTINGS = new Command("Settings", Command.SCREEN, 2);

	/**
	 * The <code>Save</code> command.
	 */
	protected static final Command COMMAND_SAVE = new Command("Save", Command.OK, 1);

	/**
	 * The <code>Send</code> command.
	 */
	protected static final Command COMMAND_SEND = new Command("Send", Command.OK, 1);

	/**
	 * The history maximum size.
	 */
	private static final int MAX_HISTORY = 8;

	/**
	 * The description query prefix.
	 */
	private static final String PREFIX_DEFINITION = "D ";

	/**
	 * The Weather query prefix.
	 */
	private static final String PREFIX_WEATHER = "W ";

	/**
	 * The Horoscope query prefix.
	 */
	private static final String PREFIX_HOROSCOPE = "Horo ";

	/**
	 * The Stock Quote query prefix.
	 */
	private static final String PREFIX_STOCK = "Q ";


	/**
	 * The WiFi Hotspots query prefix.
	 */
	private static final String PREFIX_WIFI = "Wifi ";

	/**
	 * The Area Code query prefix.
	 */
	private static final String PREFIX_AREA_CODE = "Area Code ";

	/**
	 * The Zip Code query prefix.
	 */
	private static final String PREFIX_ZIP = "Zip Code ";

	/**
	 * The history preferences key.
	 */
	private static final String PREFS_HISTORY_KEY = "h";

	/**
	 * The location preferences key.
	 */
	private static final String PREFS_LOCATION_KEY = "l";

	/**
	 * The address preferences key.
	 */
	private static final String PREFS_ADDRESS_KEY = "a";

	// The current action.
	private String _action = CHOICE_YAHOO_SMS;

	// The Yahoo! SMS address.
	private String _address = "92466";

	// The default error/message alert dialog.
	private Alert _defaultAlert;

	// The display instance.
	private Display _display;

	// The first time flag.
	private boolean _firstTime;

	// The help screen.
	private HelpScreen _helpScreen;

	// The history.
	private /* final */ Vector _history = new Vector(8);

	// The history screen.
	private HistoryScreen _historyScreen;

	// The location.
	private String _location = "";

	// The main screen.
	private /* final */ MainScreen _mainScreen;

	// The preferences store.
	private RmsIndex _prefs;

	// The preferences screen.
	private PrefsScreen _prefsScreen;

	// The current query.
	private String _query = "";

	// The sending message alert dialog.
	private Alert _sendingAlert;

	/**
	 * Creates a new YahooME instance.
	 */
	public YahooME()
	{
		super();

		_mainScreen = new MainScreen(this);
		_firstTime = true;
	}

	/**
	 * Performs a command.
	 *
	 * @param c The command action.
	 * @param d The diplayable screen.
	 */
	public /* final */ void commandAction(Command c, Displayable d)
	{
		if (c == COMMAND_EXIT)
		{
			exit();
		}
		else if (c == COMMAND_ABOUT)
		{
			alert("About " + APP_NAME,
				  APP_NAME + ' ' + APP_VERSION + "\nCopyright 2005\nErik C. Thauvin\nerik@thauvin.net", d, false);
		}
		else if (c == COMMAND_CLEAR)
		{
			_mainScreen.queryFld.setString("");
			Util.setCurrentItem(_display, (Item) _mainScreen.queryFld);
		}
		else if (c == COMMAND_SEND)
		{
			if (isValidString(_address))
			{
				/* final */ String q = _mainScreen.queryFld.getString();
				_action = CHOICES[_mainScreen.actionPopup.getSelectedIndex()];

				if (isValidString(q))
				{
					if ((_action.equals(CHOICE_YAHOO_LOCAL) && !isValidString(_location)))
					{
						alert("No Location", "Please set a location first.", d, true);
					}
					else
					{
						if (_sendingAlert == null)
						{
							_sendingAlert = new Alert("");
						}

						_query = buildQuery(q);

						addHistory(_query);
						saveHistory();

						alert(_sendingAlert, "Sending SMS", "Sending message to " + _address + "...", d, 2500, false);

						new Thread(this).start();
					}
				}
				else
				{
					Util.setCurrentItem(_display, (Item) _mainScreen.queryFld);
					alert("Invalid Input", "Please specify a query.", d, true);
				}
			}
			else
			{
				alert("No SMS Address", "Please set the Yahoo! SMS number.", d, true);
			}
		}
		else if (c == COMMAND_SETTINGS)
		{
			if (_prefsScreen == null)
			{
				_prefsScreen = new PrefsScreen(this);
			}

			_prefsScreen.smsFld.setString(_address);
			_prefsScreen.locationFld.setString(_location);

			_display.setCurrent(_prefsScreen);
		}
		else if (c == COMMAND_SAVE)
		{
			_location = _prefsScreen.locationFld.getString();
			_address = _prefsScreen.smsFld.getString();

			_display.setCurrent(_mainScreen);

			saveSettings();
		}
		else if (c == COMMAND_HISTORY)
		{
			if (_historyScreen == null)
			{
				_historyScreen = new HistoryScreen(this);
			}

			int size = _historyScreen.size();

			if (size > 0)
			{
				for (int i = 0; i < size; i++)
				{
					_historyScreen.delete(0);
				}
			}

			size = _history.size();

			if (size > 0)
			{
				for (int i = 0; i < size; i++)
				{
					_historyScreen.append((String) _history.elementAt(i), null);
				}

				_display.setCurrent(_historyScreen);
			}
			else
			{
				alert("No History", "There is no history to display.", d, true);
			}
		}
		else if (c == List.SELECT_COMMAND)
		{
			/* final */ String query = _historyScreen.getString(_historyScreen.getSelectedIndex());

			// Definition
			if (query.startsWith(PREFIX_DEFINITION))
			{
				_mainScreen.queryFld.setString(query.substring(query.indexOf(' ') + 1));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_DEFINITION), true);
			}

			// Horoscope
			else if (query.startsWith(PREFIX_HOROSCOPE))
			{
				_mainScreen.queryFld.setString(query.substring(query.indexOf(' ') + 1));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_HOROSCOPE), true);
			}

			// Stock Quote
			else if (query.startsWith(PREFIX_STOCK))
			{
				_mainScreen.queryFld.setString(query.substring(query.indexOf(' ') + 1));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_STOCK), true);
			}

			// Weather
			else if (query.startsWith(PREFIX_WEATHER))
			{
				_mainScreen.queryFld.setString(query.substring(PREFIX_WEATHER.length()));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_WEATHER), true);
			}

			// Area Code
			else if (query.startsWith(PREFIX_AREA_CODE))
			{
				_mainScreen.queryFld.setString(query.substring(PREFIX_AREA_CODE.length()));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_AREA_CODE), true);
			}

			// Zip Code
			else if (query.startsWith(PREFIX_ZIP))
			{
				_mainScreen.queryFld.setString(query.substring(PREFIX_ZIP.length()));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_ZIP), true);
			}

			// WiFi Hotspots
			else if (query.startsWith(PREFIX_WIFI))
			{
				_mainScreen.queryFld.setString(query.substring(PREFIX_WIFI.length()));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_WIFI), true);
			}

			// Local
			else if (isValidString(_location) && query.endsWith(' ' + _location))
			{
				_mainScreen.queryFld.setString(query.substring(0, query.lastIndexOf(' ')));
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_YAHOO_LOCAL), true);
			}
			else
			{
				_mainScreen.queryFld.setString(query);
				_mainScreen.actionPopup.setSelectedIndex(choiceIndex(CHOICE_YAHOO_SMS), true);
			}

			_display.setCurrent(_mainScreen);
		}
		else if (c == COMMAND_HELP)
		{
			if (_helpScreen == null)
			{
				_helpScreen = new HelpScreen(this);
			}

			_display.setCurrent(_helpScreen);
		}
		else if (c == COMMAND_BACK)
		{
			_display.setCurrent(_mainScreen);
		}
	}

	/**
	 * Executes the thread.
	 */
	public /* final */ void run()
	{
		sendSMS();
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean b)
					   throws MIDletStateChangeException
	{
		try
		{
			if (_prefs != null)
			{
				_prefs.close();
			}
		}
		catch (RecordStoreException e)
		{
			alert("Close Error", "Could not close the preferences store: " + e.getMessage(), _mainScreen, true);
		}

		notifyDestroyed();
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected /* final */ void pauseApp()
	{
		;
	}

	/**
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected /* final */ void startApp()
								 throws MIDletStateChangeException
	{
		if (_firstTime)
		{
			try
			{
				_prefs = new RmsIndex("YahooME");

				String item;

				// Load the history.
				for (int i = 0; i < MAX_HISTORY; i++)
				{
					item = _prefs.get(PREFS_HISTORY_KEY + i);

					if (item != null)
					{
						_history.addElement(item);
					}
					else
					{
						break;
					}
				}

				// Load the location.
				item = _prefs.get(PREFS_LOCATION_KEY);

				if (item != null)
				{
					_location = item;
				}

				// Load the address.
				item = _prefs.get(PREFS_ADDRESS_KEY);

				if (item != null)
				{
					_address = item;
				}
			}
			catch (RecordStoreException e)
			{
				alert("Load Error", "Could not load the preferences: " + e.getMessage(), _mainScreen, true);
			}

			_firstTime = false;
		}

		_display = Display.getDisplay(this);
		_display.setCurrent(_mainScreen);
	}

	// Return the index of the given choice.
	private static int choiceIndex(String choice)
	{
		for (int i = 0; i < CHOICES.length; i++)
		{
			if (CHOICES[i].equals(choice))
			{
				return i;
			}
		}

		return 0;
	}

	// Validates a string.
	private static boolean isValidString(String s)
	{
		return ((s != null) && (s.length() > 0));
	}

	// Adds to the history.
	private void addHistory(String query)
	{
		if (_history.size() == MAX_HISTORY)
		{
			_history.removeElementAt(MAX_HISTORY - 1);
		}

		_history.insertElementAt(query, 0);
	}

	// Displays a modal message/error alert dialog.
	private void alert(String title, String msg, Displayable d, boolean isError)
	{
		if (_defaultAlert == null)
		{
			_defaultAlert = new Alert("");
		}

		alert(_defaultAlert, title, msg, d, Alert.FOREVER, isError);
	}

	// Displays a message/error alert dialog.
	private void alert(Alert alert, String title, String msg, Displayable d, int timeout, boolean isError)
	{
		if (title != null)
		{
			alert.setTitle(title);
		}

		alert.setString(msg);
		alert.setType(isError ? AlertType.ERROR : AlertType.INFO);

		alert.setTimeout(timeout);

		_display.setCurrent(alert, d);
	}

	// Builds the full query .
	private String buildQuery(String query)
	{
		/* final */ String text;

		if (_action.equals(CHOICE_YAHOO_LOCAL))
		{
			if (isValidString(_location))
			{
				text = query + ' ' + _location;
			}
			else
			{
				text = query;
			}
		}

		// Stock
		else if (_action.equals(CHOICE_STOCK))
		{
			text = PREFIX_STOCK + query;
		}

		// Horoscope
		else if (_action.equals(CHOICE_HOROSCOPE))
		{
			text = PREFIX_HOROSCOPE + query;
		}

		// Definition
		else if (_action.equals(CHOICE_DEFINITION))
		{
			text = PREFIX_DEFINITION + query;
		}

		// WIFI
		else if (_action.equals(CHOICE_WIFI))
		{
			text = PREFIX_WIFI + query;
		}

		// Area Code
		else if (_action.equals(CHOICE_AREA_CODE))
		{
			text = PREFIX_AREA_CODE + query;
		}

		// Weather
		else if (_action.equals(CHOICE_WEATHER))
		{
			text = PREFIX_WEATHER + query;
		}

		// Zip Code
		else if (_action.equals(CHOICE_ZIP))
		{
			text = PREFIX_ZIP + query;
		}

		// Default
		else
		{
			text = query;
		}

		return text;
	}

	// Exits the application.
	private void exit()
	{
		try
		{
			destroyApp(false);
		}
		catch (MIDletStateChangeException ignore)
		{
			; // Do nothing
		}
	}

	// Saves the history.
	private void saveHistory()
	{
		try
		{
			if (_prefs != null)
			{
				/* final */ int size = _history.size();

				// Save the history.
				for (int i = 0; i < size; i++)
				{
					_prefs.put(PREFS_HISTORY_KEY + i, (String) _history.elementAt(i));
				}
			}
		}
		catch (RecordStoreException e)
		{
			alert("Save Error", "Could not save the history: " + e.getMessage(), _mainScreen, true);
		}
	}

	// Saves the settings.
	private void saveSettings()
	{
		try
		{
			if (_prefs != null)
			{
				// Save the location.
				_prefs.put(PREFS_LOCATION_KEY, _location);

				// Save the address.
				_prefs.put(PREFS_ADDRESS_KEY, _address);
			}
		}
		catch (RecordStoreException e)
		{
			alert("Save Error", "Could not save the settings: " + e.getMessage(), _mainScreen, true);
		}
	}

	// Sends the SMS.
	private void sendSMS()
	{
		/* final */ String address = "sms://" + _address;
		MessageConnection conn = null;

		try
		{
			conn = (MessageConnection) Connector.open(address);

			/* final */ TextMessage msg = (TextMessage) conn.newMessage(MessageConnection.TEXT_MESSAGE);
			msg.setAddress(address);

			msg.setPayloadText(_query);

			conn.send(msg);

			//alert("SMS Sent", "The text message was sent.", _mainScreen, false);
		}
		catch (SecurityException e)
		{
			alert("SMS Error", "Access to SMS was refused: " + e.getMessage(), _mainScreen, true);
		}
		catch (Exception e)
		{
			alert("SMS Error", "The text message could not be sent: " + e.getMessage(), _mainScreen, true);
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception ignore)
			{
				; // Do nothing;
			}
		}
	}
}
