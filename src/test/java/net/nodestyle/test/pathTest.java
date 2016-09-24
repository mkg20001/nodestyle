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
