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
package ch.ethz.origo.juigle.prezentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.graphics.GraphicsUtilities;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;

import com.jhlabs.image.NoiseFilter;

/**
 * Class which contains a lot of graphics utilities methods for
 * <code>JUIGLE</code> Library and user applications.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.4 (5/19/2010)
 * @since 0.1.0 (05/18/09)
 */
public class JUIGLEGraphicsUtils {

	/** Transparent color */
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

	private static final GraphicsConfiguration configuration = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();

	/**
	 * Create and return translucent image
	 * 
	 * @param width
	 *          of image
	 * @param height
	 *          of image
	 * @return translucent image
	 */
	public static BufferedImage createTranslucentCompatibleImage(int width,
			int height) {
		return configuration.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
	}

	/**
	 * Create texture for background of component
	 * 
	 * @param color1
	 *          color number 1
	 * @param color2
	 *          color number 2
	 * @param size
	 *          the size of created image
	 * @return created instance of class java.awt.Paint {@link Paint}
	 * @since 0.1.0
	 */
	public static Paint createBackgroundTexture(Color color1, Color color2,
			int size) {
		BufferedImage image = JUIGLEGraphicsUtils.createTranslucentCompatibleImage(
				size, size);
		Graphics2D g2d = image.createGraphics();
		Paint paint = new GradientPaint(0, 0, color1, 0, size, color2);
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, size, size);
		g2d.dispose();
		NoiseFilter filter = new NoiseFilter();
		filter.setAmount(10);
		filter.setDensity(0.5f);
		filter.setDistribution(NoiseFilter.UNIFORM);
		filter.setMonochrome(true);
		filter.filter(image, image);

		Paint result = new TexturePaint(image, new Rectangle(size, size));
		return result;
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 * @param path
	 * @return
	 */
	public static Icon createImageIcon(String path) {
		java.net.URL imgURL = ClassLoader.getSystemResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			// TODO udelat lepsi error
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 * @param path
	 * @param description
	 * @return
	 */
	public static Icon createImageIcon(String path, String description) {
		java.net.URL imgURL = ClassLoader.getSystemResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			// TODO udelat lepsi error
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	/**
	 * Create and return image icon from images specified by 
	 * path.
	 * @param path of image
	 * @param width image width
	 * @param height image height
	 * @return image icon from images specified by path
	 * @throws PerspectiveException
	 */
	public static Icon createImageIcon(String path, int width, int height)
			throws PerspectiveException {
		return new ImageIcon(GraphicsUtilities.createThumbnail(JUIGLEGraphicsUtils
				.getImage(path), width, height));
	}

	/**
	 * Return image specified by path as instance of <code>BufferedImage</code>.
	 * @param path image
	 * @return image specified by path as instance of <code>BufferedImage</code>
	 * @throws PerspectiveException
	 */
	public static BufferedImage getImage(String path) throws PerspectiveException {
		try {
			return ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		} catch (IOException e) {
			throw new PerspectiveException(e);
		}
	}

	/**
	 * Create instance of highlighter for JXTable instances
	 * 
	 * @return nstance of highlighter for JXTable instances
	 * @version 0.1.0
	 * @since 0.1.1
	 */
	public static Highlighter getHighlighterInstance() {
		HighlightPredicate feverWarning = new HighlightPredicate() {
			@Override
			public boolean isHighlighted(Component component, ComponentAdapter adapter) {
				return (adapter.row % 2) == 0;
			}
		};
		Highlighter hl = null;
		// hl = new ColorHighlighter(Color.LIGHT_GRAY, Color.orange);
		hl = new HighlighterFactory.UIColorHighlighter(feverWarning);
		return hl;
	}

	/**
	 * Return center position as instance of class <code>Point</code> for current
	 * component
	 * 
	 * @param comp
	 *          instance of <code>Component</code> class on which will be computed
	 *          center position
	 * @return center position as instance of class <code>Point</code>
	 * @version 0.1.0 (3/17/2010)
	 * @since 0.1.3 (3/17/2010)
	 * @see Point
	 */
	public static Point getCenterPosition(Component comp) {
		Dimension dimJFV = comp.getSize();
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Point p = new Point((dimScreen.width - dimJFV.width) / 2,
				(dimScreen.height - dimJFV.height) / 2);
		return p;
	}
	
	/**
	 * Return center position
	 * 
	 * @param dimension
	 *          instance of dimension from which will be create position counted
	 * @return center position for dialog
	 * @version 0.1.0 (5/19/2010)
	 * @since 0.1.4 (5/19/2010)
	 */
	public static Point getCenterPosition(Dimension dimension) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Calculate the frame location
		int x = (screenSize.width - dimension.width) / 2;
		int y = (screenSize.height - dimension.height) / 2;
		return new Point(x, y);
	}

}
