/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.prezentation.menu;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JDialog;

import org.jdesktop.swingx.JXDatePicker;

import ch.ethz.origo.juigle.application.BrowserLaunch;
import ch.ethz.origo.juigle.application.JUIGLEErrorParser;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.data.EmailErrorReporter;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;
import ch.ethz.origo.juigle.prezentation.JUIGLErrorInfoUtils;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;
import ch.ethz.origo.juigle.prezentation.perspective.PerspectivePanel;

/**
 * Construct main menu of application.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.4.01 (10/03/2010)
 * @since 0.1.0 (08/14/09)
 * @see JUIGLEMenu
 */
public class JUIGLEMainMenu extends JUIGLEMenu {

	/** Only for serialization */
	private static final long serialVersionUID = 2839975234788319679L;
	
	public static final String KEY_PERSPECTIVE_MAIN_MENU = "menu.main.perspectives";

	private JXDatePicker datePicker;

	private JUIGLEMenuItem perspectivesItem;

	/**
	 * Construct main application menu with path to the file which contains
	 * localized text for this menu.
	 * 
	 * @param resourceBundlePath
	 *          path to localized file
	 */
	public JUIGLEMainMenu(String resourceBundlePath) {
		super();
		setLocalizedResourceBundle(resourceBundlePath);
	}

	/**
	 * This method added perspective item to the main menu. This item is list of
	 * all available perspectives.
	 * 
	 * @param icon
	 *          item icon
	 * @param container
	 *          instance of panel which contains displayed perspective
	 * @param perspectives
	 *          list of all available perspectives
	 * @version 0.1.2
	 * @since 0.1.0
	 */
	public void addPerspectiveItems(Icon icon, final PerspectivePanel container,
			List<Perspective> perspectives, String resourceBundleKey) {
		perspectivesItem = new JUIGLEMenuItem(resourceBundleKey);
		perspectivesItem.setLocalizedResourceBundle(getResourceBundlePath());
		if (icon != null) {
			perspectivesItem.setIcon(icon);
		}
		// Collections.sort(perspectives);
		for (final Perspective perspective : perspectives) {
			JUIGLEMenuItem item = new JUIGLEMenuItem();
			item.setLocalizedResourceBundle(perspective.getResourceBundlePath());
			item.setResourceBundleKey(perspective.getRBPerspectiveTitleKey());

			Action action = new AbstractAction() {
				private static final long serialVersionUID = 1796718465304187844L;

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						container.add(perspective);
					} catch (PerspectiveException e1) {
						// parsing error message
						String errorMSG = JUIGLEErrorParser.getJUIGLEErrorMessage(e1
								.getMessage());
						// display error GUI
						JUIGLErrorInfoUtils.showErrorDialog("JUIGLE Error", errorMSG, e1,
								Level.WARNING, new EmailErrorReporter());
						e1.printStackTrace();
					}
					// TODO UPOZORNI OSTATNI PERSPECTIVY ZE SE MENI NA JINOU
				}
			};
			item.setAction(action);
			perspectivesItem.addSubItem(item);
		}
		this.addItem(perspectivesItem);
		this.revalidate();
	}

	public void addSettingsItem(Icon icon, String resourceBundleKey) {
		throw new UnsupportedOperationException("Method not implemented yet...");
	}

	/**
	 * Add item to the main menu which invoke user's default browser and show home
	 * page of the application.
	 * 
	 * @param icon
	 *          item icon
	 * @param url
	 *          internet address
	 * @throws PerspectiveException
	 */
	public void addHomePageItem(Icon icon, final String url)
			throws PerspectiveException {
		JUIGLEMenuItem item = new JUIGLEMenuItem();
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1796718465304187844L;

			@Override
			public void actionPerformed(ActionEvent e) {
				BrowserLaunch.openURL(url);
				// new OpenBrowserAction(url);
			}
		};
		item.setAction(action);
		if (icon == null) {
			item.setIcon(JUIGLEGraphicsUtils.createImageIcon(
					"ch/ethz/origo/juigle/data/images/Firefox_48x48.png", 32, 32));
		} else {
			item.setIcon(icon);
		}
		this.addItem(item);
		this.revalidate();
	}

	/**
	 * Add about item to the main menu
	 * 
	 * @param icon
	 *          item icon
	 * @param dialog
	 *          instance of about dialog
	 * @param resourceBundleKey
	 *          key for resource bundle
	 * @throws PerspectiveException 
	 */
	public void addAboutItem(Icon icon, final JDialog dialog, String resourceBundleKey) throws PerspectiveException {
		JUIGLEMenuItem item = new JUIGLEMenuItem();
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1796718465304187844L;

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		};
		item.setAction(action);
		if (icon == null) {
			item.setIcon(JUIGLEGraphicsUtils.createImageIcon(
					"ch/ethz/origo/juigle/data/images/info-48.png", 32, 32));
		} else {
			item.setIcon(icon);
		}
		this.addItem(item);
		this.revalidate();
	}

	/**
	 * This method add calendar item to the main application menu. Calendar is
	 * instance from library <code>SwingX</code>.
	 * 
	 * @param icon
	 *          item icon
	 * @param resourceBundleKey
	 *          key for resource bundle
	 * 
	 */
	public void addCalendarItem(Icon icon, String resourceBundleKey) {
		datePicker = new JXDatePicker();
		datePicker.setDate(new Date());
		datePicker.setVisible(true);
		this.add(datePicker);
		this.revalidate();
	}

}