package net.nodestyle.console;

/**
 * Created by maciej on 23.09.16.
 */
public class console {
    private static Console c=new Console(System.out,System.err);
    /*public static void assert() {

    }*/
    public static void dir() {

    }
    public static void error(Object... o) {
        c.error(o);
    }
    public static void info(Object... o) {
        c.info(o);
    }
    public static void log(Object... o) {
        c.log(o);
    }
    public static void time(String label) {
        c.time(label);
    }
    public static void timeEnd(String label,Object... o) {
        c.timeEnd(label,o);
    }
    //timeEnd
    public static void trace(Object... o) {
        c.trace(o);
    }
    public static void warn(Object... o) {
        c.warn(o);
    }
}
