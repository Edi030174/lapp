package net.lintasarta.idoss.chat;

import net.lintasarta.idoss.webui.util.FDDateFormat;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 13, 2010
 * Time: 1:18:53 PM
 */
public class ChatWindow extends GFCBaseCtrl implements Serializable {
    private transient static final Logger logger = Logger.getLogger(ChatWindow.class);

    private transient Window chatWindow;
    private transient Textbox nickname;
    private transient Vbox msgBoard;

    private transient String sender;

    private transient ChatRoom chatroom;

    private transient Chatter chatter;

    private transient Desktop desktop;

    private transient boolean isLogin;

    public void init() {
        desktop = Executions.getCurrent().getDesktop();

        chatroom = (ChatRoom) desktop.getWebApp().getAttribute("chatroom");
        if (chatroom == null) {
            chatroom = new ChatRoom();
            desktop.getWebApp().setAttribute("chatroom", chatroom);
        }
    }

    public void onClose$ChatWindow(Event event) {
        onExit();
    }

    public void onCreate() {
        init();
    }

    public void onOK() {
        if (isLogin())
            onSendMsg();
        else
            onLogin();
    }

    public void onLogin() {
        desktop.enableServerPush(true);

        sender = nickname.getValue();

        chatter = new Chatter(chatroom, sender, msgBoard);
        chatter.start();

        setLogin(true);

        chatWindow.setWidth("100%");
        chatWindow.getFellow("dv").setVisible(true);
        chatWindow.getFellow("input").setVisible(true);
        chatWindow.getFellow("login").setVisible(false);
        ((Textbox) chatWindow.getFellow("nickname")).setRawValue("");

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        int width = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopWidth")).getValue();
    }

    public void onExit() {
        chatter.setDone();

        setLogin(false);

        chatWindow.setWidth("300px");
        chatWindow.setHeight("200px");

        chatWindow.getFellow("msgBoard").getChildren().clear();
        chatWindow.getFellow("login").setVisible(true);
        chatWindow.getFellow("dv").setVisible(false);
        chatWindow.getFellow("input").setVisible(false);

        desktop.enableServerPush(false);

        Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
        Center center = bl.getCenter();
        Tabs tabs = (Tabs) center.getFellow("divCenter").getFellow("tabBoxIndexCenter").getFellow("tabsIndexCenter");

        Tab checkTab = null;
        try {
            checkTab.onClose();
        } catch (ComponentNotFoundException ignored) {
        }
    }

    public void onSendMsg() {
        Label message = new Label();
        message.setValue(getDateTime() + " / " + sender + ": " + ((Textbox) chatWindow.getFellow("msg")).getValue());
        chatWindow.getFellow("msgBoard").appendChild(message);
        chatter.sendMessage(((Textbox) chatWindow.getFellow("msg")).getValue());
        ((Textbox) chatWindow.getFellow("msg")).setRawValue("");
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean bool) {
        isLogin = bool;
    }

    private String getDateTime() {
        return FDDateFormat.getTimeLongFormater().format(new Date());
    }
}
