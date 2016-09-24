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
/**
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.nodestyle.console;

import net.nodestyle.helper.Object;

import java.io.PrintStream;
import java.util.Date;

/**
 * Created by maciej on 24.09.16.
 */
public class Console {

    private PrintStream stdout=null;
    private PrintStream stderr=null;
    private Object timers=new Object();
    private boolean hasStderr=false;

    private String[] makeString(java.lang.Object... o) {
        String[] strings=new String[o.length];
        Integer i=0;
        for (java.lang.Object ob:o) {
            try {
                strings[i]=(String) ob;
            } catch(Exception e) {
                try {
                    Integer r=(Integer) ob;
                    strings[i]=r.toString();
                } catch(Exception e1) {
                    try {
                        Boolean r=(Boolean) ob;
                        strings[i]=r.toString();
                    } catch(Exception e2) {
                        strings[i]=ob.toString();
                    }
                }
            }
        }
        return strings;
    }
    private void makePrintfString(PrintStream s,java.lang.Object... o) {
        java.lang.Object[] args=new java.lang.Object[o.length-1];
        Integer i=1;
        while(i>=o.length) {
            args[i-1]=o[i];
            i++;
        }
        s.format(makeString(o[0])[0],args);
    }
    private String makeSingleString(java.lang.Object... o) {
        String out=null;
        for (String s:makeString(o)) {
            out = out == null ? s : out + " " + s;
        }
        return out;
    }

    private void write(PrintStream stream, java.lang.Object... o) {
        //stream.printf(makePrintfString(o),new Object());
        makePrintfString(stream,o);
    }
    public Console(PrintStream stdout,PrintStream stderr) {
        this.stdout=stdout;
        this.stderr=stderr;
        this.hasStderr=true;
    }
    public Console(PrintStream stdout) {
        this.stdout=stdout;
    }

    public void error(java.lang.Object... o) {
        write(hasStderr?stderr:stdout,o);
    }
    public void warn(java.lang.Object... o) {
        error(o);
    }
    public void log(java.lang.Object... o) {
        write(stdout,o);
    }
    public void info(java.lang.Object... o) {
        log(o);
    }
    public void trace(java.lang.Object o) {
        new Error(makeSingleString(o)).printStackTrace(hasStderr?stderr:stdout);
    }
    public void time(String label) {
        timers.set(label,new Date());
    }
    public void timeEnd(String label,java.lang.Object... o) {
        if (timers.get(label)==null) time(label);
        Date t=(Date) timers.get(label);
        Integer diff=t.compareTo(new Date());
        makePrintfString(stdout,label,o,": "+diff+"ms");
    }
}
