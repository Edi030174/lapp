package net.lintasarta.util;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 10, 2010
 * Time: 5:13:44 PM
 */
public class PhoneNumberValidation {
    public PhoneNumberValidation() {
        String phoneNumber = "0899088";
//        String pattern = "[a-zA-Z_0-9]*idoss[a-zA-Z_0-9]*";
        String pattern = "[0-9-/() ]*";

        boolean isMatch = phoneNumber.matches(pattern);
        System.out.println("isMatch = " + isMatch);
    }

    public static void main(String[] args) {

        new PhoneNumberValidation();

    }
}
