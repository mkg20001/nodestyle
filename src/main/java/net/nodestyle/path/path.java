package net.nodestyle.path;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.javafx.PlatformUtil.isWindows;

/**
 * Created by maciej on 23.09.16.
 */
class Helper {
    public static String unixSeperator="/";
    public static String replaceLast(String input, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            return input;
        }
        int lastMatchStart=0;
        do {
            lastMatchStart=matcher.start();
        } while (matcher.find());
        matcher.find(lastMatchStart);
        StringBuffer sb = new StringBuffer(input.length());
        matcher.appendReplacement(sb, replacement);
        matcher.appendTail(sb);
        return sb.toString();
    }
}

public class path {
    public static Path win32=new Path(";","\\",true);
    public static Path posix=new Path(":","/",false);
    private static Path current=new Path(isWindows()?";":":",File.separator,isWindows());

    public static String basename(String s) {
        return current.basename(s);
    }
    public static String basename(String s,String ext) {
        return current.basename(s,ext);
    }

}
