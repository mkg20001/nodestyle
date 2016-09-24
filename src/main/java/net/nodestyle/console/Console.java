package net.nodestyle.console;

import java.io.PrintStream;

/**
 * Created by maciej on 24.09.16.
 */
public class Console {

    private void write(PrintStream stream, String[] strings) {
        String out=null;
        for (String s:strings) {
            out = out == null ? s : out + " " + s;
        }
        stream.printf(out,new Object());
    }
    public Console(PrintStream stdout,PrintStream stderr) {

    }
}
