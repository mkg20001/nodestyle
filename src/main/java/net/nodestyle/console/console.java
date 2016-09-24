package net.nodestyle.console;

import java.io.PrintStream;

/**
 * Created by maciej on 23.09.16.
 */
public class console {
    public static void log(String... strings) {
        write(System.out,strings);
    }
}
