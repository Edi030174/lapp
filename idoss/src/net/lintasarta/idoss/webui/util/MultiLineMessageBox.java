package net.lintasarta.idoss.webui.util;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 8, 2010
 * Time: 1:48:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class MultiLineMessageBox extends Messagebox {

private static final long serialVersionUID = 1L;

	// path of the messagebox zul-template
	private transient static String _templ = "/WEB-INF/pages/util/multiLineMessageBox.zul";

	public MultiLineMessageBox() {
	}

	public static void doSetTemplate() {
		setTemplate(_templ);
	}

	/**
	 * Shows a message box and returns what button is pressed. A shortcut to
	 * show(message, null, OK, INFORMATION). <br>
	 * <br>
	 * Simple MessageBox with customizable message and title. <br>
	 *
	 * @param message
	 *            The message to display.
	 * @param title
	 *            The title to display.
	 * @param icon
	 *            The icon to display. <br>
	 *            QUESTION = "z-msgbox z-msgbox-question"; <br>
	 *            EXCLAMATION = "z-msgbox z-msgbox-exclamation"; <br>
	 *            INFORMATION = "z-msgbox z-msgbox-imformation"; <br>
	 *            ERROR = "z-msgbox z-msgbox-error"; <br>
	 * @param buttons
	 *            MultiLineMessageBox.CANCEL<br>
	 *            MultiLineMessageBox.YES<br>
	 *            MultiLineMessageBox.NO<br>
	 *            MultiLineMessageBox.ABORT<br>
	 *            MultiLineMessageBox.RETRY<br>
	 *            MultiLineMessageBox.IGNORE<br>
	 * @param padding
	 *            true = Added an empty line before and after the message.<br>
	 *
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static final int show(String message, String title, int buttons, String icon, boolean padding) throws InterruptedException {

		String msg = message;

		if (padding == true) {
			msg = "\n" + message + "\n\n";
		}

		if (icon.equals("QUESTION")) {
			icon = "z-msgbox z-msgbox-question";
		} else if (icon.equals("EXCLAMATION")) {
			icon = "z-msgbox z-msgbox-exclamation";
		} else if (icon.equals("INFORMATION")) {
			icon = "z-msgbox z-msgbox-imformation";
		} else if (icon.equals("ERROR")) {
			icon = "z-msgbox z-msgbox-error";
		}

		return show(msg, title, buttons, icon, 0, null);
	}

	/**
	 * Shows a message box and returns what button is pressed. A shortcut to
	 * show(message, null, OK, INFORMATION). <br>
	 * <br>
	 * Simple MessageBox with customizable message and title. <br>
	 *
	 * @param message
	 *            The message to display.
	 * @param title
	 *            The title to display.
	 * @param icon
	 *            The icon to display. <br>
	 *            QUESTION = "z-msgbox z-msgbox-question"; <br>
	 *            EXCLAMATION = "z-msgbox z-msgbox-exclamation"; <br>
	 *            INFORMATION = "z-msgbox z-msgbox-imformation"; <br>
	 *            ERROR = "z-msgbox z-msgbox-error"; <br>
	 * @param buttons
	 *            MultiLineMessageBox.CANCEL<br>
	 *            MultiLineMessageBox.YES<br>
	 *            MultiLineMessageBox.NO<br>
	 *            MultiLineMessageBox.ABORT<br>
	 *            MultiLineMessageBox.RETRY<br>
	 *            MultiLineMessageBox.IGNORE<br>
	 * @param padding
	 *            true = Added an empty line before and after the message.<br>
	 *
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public static final int show(String message, String title, int buttons, String icon, boolean padding, EventListener listener)
			throws InterruptedException {

		String msg = message;

		if (padding == true) {
			msg = "\n" + message + "\n\n";
		}

		if (icon.equals("QUESTION")) {
			icon = "z-msgbox z-msgbox-question";
		} else if (icon.equals("EXCLAMATION")) {
			icon = "z-msgbox z-msgbox-exclamation";
		} else if (icon.equals("INFORMATION")) {
			icon = "z-msgbox z-msgbox-imformation";
		} else if (icon.equals("ERROR")) {
			icon = "z-msgbox z-msgbox-error";
		}

		return show(msg, title, buttons, icon, 0, listener);
	}
    
}
