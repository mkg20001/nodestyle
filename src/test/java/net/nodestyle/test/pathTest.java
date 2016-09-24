package net.nodestyle.test;

import net.nodestyle.path.path;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciej on 23.09.16.
 */

abstract class PathTest {
    abstract void run(String file,net.nodestyle.path.Path p);
}

public class pathTest {
    net.nodestyle.path.Path[] paths={path.posix,path.win32};
    private void pathTest(String path,PathTest test) { //allows to run tests for unix, win and win with / separator
        for (net.nodestyle.path.Path p:paths) {
            if (p.sep.equalsIgnoreCase("\\")) {
                //win
                String win=path.replaceFirst("/","C:/").replaceFirst("/home/user","/Users/User");
                test.run(win,p);
                test.run(win.replaceAll("/","\\\\"),p);
            } else {
                //not win
                test.run(path,p);
            }
        }

    }

    @Test
    public void basename() throws Exception {
        pathTest("/home/user/file.html", new PathTest() {
            @Override
            void run(String file, net.nodestyle.path.Path p) {
                assertEquals("path basename","file.html",p.basename(file));
                assertEquals("path basename with ext","file",p.basename(file,".html"));
            }
        });
    }
}
