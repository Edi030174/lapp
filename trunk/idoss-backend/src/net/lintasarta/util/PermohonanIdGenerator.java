package net.lintasarta.util;

/**
 * Created by Joshua
 * Date: Aug 11, 2010
 * Time: 10:04:11 AM
 */
public class PermohonanIdGenerator {
    private String permohonanId;

    public String getPermohonanId() {
        return permohonanId;
    }

    public PermohonanIdGenerator(String seq) {
        int len = 9;
        StringBuffer seqBuff = new StringBuffer();
        for (int i = 0; i < (len - seq.length()); i++) {
              seqBuff.append("0");
        }
        seq = seqBuff.toString() + seq;
        permohonanId = seq;
    }

    public static void main(String[] args) {
        String seq = "7777";
        PermohonanIdGenerator pid = new PermohonanIdGenerator(seq);
        String permohonanIdResult = pid.getPermohonanId();
        System.out.println("permohonanIdResult = " + permohonanIdResult);
    }
}