package net.lintasarta.util;

/**
 * Created by Joshua
 * Date: Jul 2, 2010
 * Time: 11:17:12 AM
 */
public class TicketIdGenerator {
    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public TicketIdGenerator(String seq) {
        int len = 9;
        StringBuffer seqBuff = new StringBuffer();
        for (int i = 0; i < (len - seq.length()); i++) {
              seqBuff.append("0");
        }
        seq = seqBuff.toString() + seq;
        ticketId = seq;
    }

    public static void main(String[] args) {
        String seq = "1976";
        TicketIdGenerator tid = new TicketIdGenerator(seq);
        String ticketIdResult = tid.getTicketId();
        System.out.println("ticketIdResult = " + ticketIdResult);
    }
}
