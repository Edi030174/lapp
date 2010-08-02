package net.lintasarta.idoss.chat;

import org.zkoss.lang.Threads;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.*;
import org.zkoss.zul.Label;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 13, 2010
 * Time: 1:20:36 PM
 */
public class Chatter extends Thread {
    private static final Log log = Log.lookup(Chatter.class);

    private boolean _ceased;

    private ChatRoom _chatroom;

    private final Desktop _desktop;

    private Component _msgBoard;

    private String _name;

    private List<String> _msgs;

    public Chatter(ChatRoom chatroom, String name, Component msgBoard) {
        _chatroom = chatroom;
        _name = name;
        _msgBoard = msgBoard;
        _desktop = msgBoard.getDesktop();
        _msgs = new LinkedList<String>();
    }

    public void run() {
        if (!_desktop.isServerPushEnabled())
            _desktop.enableServerPush(true);
        log.info("active chatter : " + getName());
        _chatroom.subscribe(this);
        try {
            while (!_ceased) {
                try {
                    if (_msgs.isEmpty()) {
                        Threads.sleep(500);
                    } else {
                        Executions.activate(_desktop);
                        try {
                            process();
                        } finally {
                            Executions.deactivate(_desktop);
                        }
                    }
                } catch (DesktopUnavailableException ex) {
                    throw ex;
                } catch (Throwable ex) {
                    log.error(ex);
                    throw UiException.Aide.wrap(ex);
                }
            }
        } finally {
            log.info(getName() + " logout the chatroom!");
            _chatroom.unsubscribe(this);
            if (_desktop.isServerPushEnabled())
                Executions.getCurrent().getDesktop().enableServerPush(false);
        }
        log.info("The chatter thread ceased: " + getName());
    }

    public String getSender() {
        return _name;
    }

    public void addMessage(String message) {
        _msgs.add(message);
    }

    public void sendMessage(String message) {
        _chatroom.broadcast(getSender(), message);
    }

    private void renderMessages() {
        while (!_msgs.isEmpty()) {
            String msg;
            synchronized (_msgs) {
                msg = _msgs.remove(0);
            }
            _msgBoard.appendChild(new Label(msg));
            String browserTyp = Executions.getCurrent().getUserAgent();
            System.out.println(browserTyp);
        }
    }

    private void process() throws Exception {
        renderMessages();
    }

    public void setDone() {
        _ceased = true;
    }
}
