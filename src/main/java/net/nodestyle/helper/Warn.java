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
package net.nodestyle.helper;

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
