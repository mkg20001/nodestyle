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
package net.nodestyle.console;

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
