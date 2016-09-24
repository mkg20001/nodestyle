package net.nodestyle.helper;

/**
 * Created by maciej on 24.09.16.
 */
public class Warn {
    public static void write(Integer line,String t,String... msg2) {
        String msg=null;
        for (String s:msg2) msg=msg==null?s:msg+" "+s;
        System.err.println("(node:"+line+") "+t+": "+msg);
    }
    public static void warn(String... msg) {
        write(6700,"Warning",msg);
    }
}
