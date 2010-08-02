package net.lintasarta.idoss.chat;

import net.lintasarta.idoss.webui.util.FDDateFormat;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 13, 2010
 * Time: 1:20:00 PM
 */
public class ChatRoom implements Serializable {
    private transient final Collection<Chatter> _chatters;

    private transient static final String SIGNAL = "~~~";

    public ChatRoom() {
        _chatters = new LinkedList<Chatter>();
    }

    public void broadcast(String sender, String message) {

        say(sender, getDateTime() + " / " + sender + ": " + message);
    }

    private void say(String sender, String message) {
        synchronized (_chatters) {
            for (Chatter _chatter : _chatters)
                if (!_chatter.getSender().equals(sender))
                    _chatter.addMessage(message);
        }
    }

    public void subscribe(Chatter chatter) {
        chatter.addMessage(SIGNAL + "Welcome " + chatter.getSender() + SIGNAL);
        synchronized (_chatters) {
            _chatters.add(chatter);
        }
        say(chatter.getSender(), getDateTime() + ": " + SIGNAL + chatter.getSender() + " join this chatroom" + SIGNAL);
    }

    public void unsubscribe(Chatter chatter) {
        _chatters.remove(chatter);
        chatter.addMessage(SIGNAL + "Bye " + chatter.getSender() + SIGNAL);
        synchronized (_chatters) {
            for (Chatter _chatter : _chatters)
                _chatter.addMessage(getDateTime() + ": " + SIGNAL + chatter.getSender() + " leaves the chat room!" + SIGNAL);
        }
    }

    private String getDateTime() {
        return FDDateFormat.getTimeLongFormater().format(new Date());
    }
}
