package net.nodestyle.test;

import java.util.Date;

/**
 * Created by maciej on 23.09.16.
 */
public class Log {
    public static String prefix="nodestyle";
    private static String is0(String s) {
        if (s.length()==1) return 0+s; else return s;
    }
    private static String is0(Integer i) {
        return is0(i.toString());
    }
    private static String time() {
        Date d=new Date();
        return is0(d.getDate())+"."+is0(d.getMonth())+"."+(d.getYear()-100+2000)+" "+is0(d.getHours())+":"+is0(d.getMinutes())+":"+is0(d.getSeconds());
    }
    private static void out(String t,String[] s2) {
        String msg=null;
        for (String s:s2) msg=msg==null?s:msg+" "+s;
        System.out.println("["+time()+"] "+prefix+"/"+t.toUpperCase()+": "+msg);
    }
    public static void log(String... s) {
        out("log",s);
    }
}
