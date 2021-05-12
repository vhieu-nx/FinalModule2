package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInformation {
    public static final String CHECK_PHONE = "^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$";
    public static final Pattern CHECK_GMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern pattern;
    private static Matcher matcher;
    public static boolean checkPhone(String phone){
        pattern = Pattern.compile(CHECK_PHONE);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public static boolean checkEmail(String email){
        Matcher matcher = CHECK_GMAIL.matcher(email);
        return matcher.find();
    }
}
