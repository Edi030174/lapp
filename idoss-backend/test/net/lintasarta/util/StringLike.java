package net.lintasarta.util;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 6, 2010
 * Time: 4:32:03 PM
 */
public class StringLike {
    public StringLike() {
        String s1 = "test";
        String s2 = "test5";
        System.out.println("s1.contains(s2) = " + s1.contains(s2));
        System.out.println("s2.contains(s1) = " + s2.contains(s1));
    }

    public static void main(String[] args) {
        new StringLike();
    }
}
