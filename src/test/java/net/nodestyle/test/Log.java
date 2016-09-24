/**
 *
 *         Copyright (C) 2016 Maciej Krüger <mkg20001@gmail.com>
 *
 *         NodeStyle is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         NodeStyle is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with NodeStyle.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.nodestyle.test;

import java.util.Date;

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
