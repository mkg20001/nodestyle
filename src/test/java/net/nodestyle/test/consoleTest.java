package net.nodestyle.test;

import net.nodestyle.console.Console;
import net.nodestyle.test.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by maciej on 24.09.16.
 */
public class consoleTest {
//    Console c=new Console();
    // TODO: 24.09.16 add stream first 

    @Before
    public void setUp() throws Exception {
        Log.log("init");
    }

    @After
    public void tearDown() throws Exception {
        Log.log("after");
    }

    @Test
    public void dir() throws Exception {
        Log.log("d");
    }

    @Test
    public void error() throws Exception {
        Log.log("d");
    }

    @Test
    public void info() throws Exception {

    }

    @Test
    public void log() throws Exception {

    }

    @Test
    public void time() throws Exception {

    }

    @Test
    public void timeEnd() throws Exception {

    }

    @Test
    public void trace() throws Exception {

    }

    @Test
    public void warn() throws Exception {

    }

}