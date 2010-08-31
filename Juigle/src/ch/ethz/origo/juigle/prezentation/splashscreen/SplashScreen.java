package ch.ethz.origo.juigle.prezentation.splashscreen;

import java.awt.BorderLayout;
import java.awt.Image;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXImagePanel;
import org.jdesktop.swingx.JXFrame.StartPosition;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.exception.SplashScreenException;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;

/**
 * Class which create SplashScreen as instance of JXFrame.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.0 (8/29/2010)
 * @since 0.1.0 (5/19/2010)
 * 
 */
public class SplashScreen {

	/** Frame where will be frame displayed */
	private JXFrame frame;
	/** SplashScreen's image */
	private Image image;

	private static SplashScreen splash;

	/**
	 * Construct splash screen with image by given path
	 * 
	 * @param path
	 *          image path
	 * @throws SplashScreenException
	 *           image can not be created
	 */
	private SplashScreen(String path) throws SplashScreenException {
		try {
			this.image = JUIGLEGraphicsUtils.getImage(path);
		} catch (PerspectiveException e) {
			new SplashScreenException(e);
		}
	}

	/**
	 * Construct splash screen with specified image.
	 * 
	 * @param image
	 *          specified image
	 */
	private SplashScreen(Image image) {
		this.image = image;
	}

	public static SplashScreen getInstance(Image image) {
		if (splash == null) {
			splash = new SplashScreen(image);
		}
		return splash;
	}

	public static SplashScreen getInstance(String path)
	    throws SplashScreenException {
		if (splash == null) {
			splash = new SplashScreen(path);
		}
		return splash;
	}

	/**
	 * Method show splash screen
	 */
	public void show() {
		if (frame == null) {
			frame = new JXFrame();
			frame.setLayout(new BorderLayout());

			JXImagePanel imagePanel = new JXImagePanel();
			imagePanel.setImage(image);
			imagePanel.setEditable(false);
			frame.add(imagePanel, BorderLayout.CENTER);
			frame.setStartPosition(StartPosition.CenterInScreen);
			frame.setUndecorated(true);
			frame.pack();
			frame.setVisible(true);
		}
	}

	/**
	 * Method close splash screen (if is showed).
	 * 
	 * @version 0.2.0 (8/29/2010)
	 * @since 0.1.0 (5/19/2010)
	 */
	public void close() {
		if (frame != null) {
			frame.setVisible(false);
			frame = null;
			splash = null;
		}
	}

}