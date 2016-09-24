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

import net.nodestyle.helper.Object;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maciej on 23.09.16.
 */
public class v8ObjectTest {
    Object o=new Object();

    @Test
    public void setAndGet() throws Exception {
        o.set("test.flag","test");
        assertEquals("test.flag is 'test'","test",o.get("test.flag"));
        o.set("test.flag",24);
        assertEquals("test.flag is 24",24,o.get("test.flag"));
        o.set("test.flag.2",25);
        assertEquals("test.flag.2 is 25",25,o.get("test.flag.2"));
    }

    @Test
    public void del() throws Exception {
        o.set("test.flag.del","should be deleted");
        assertEquals("test.flag.del is 'should be deleted'","should be deleted",o.get("test.flag.del"));
        o.del("test.flag.del");
        assertEquals("test.flag.del is null",null,o.get("test.flag.del"));
    }

    @Test
    public void clear() throws Exception {
        o.set("test.flag.del","should be deleted");
        assertEquals("test.flag.del is 'should be deleted'","should be deleted",o.get("test.flag.del"));
        o.clear();
        assertEquals("test.flag.del is null",null,o.get("test.flag.del"));
    }

    @Test
    public void list() throws Exception {
        o.clear();
        o.set("test.1",true);
        o.set("test2","hi");
        assertEquals("contains test2 and test.1",new String[]{"test.1","test2"},o.list());
    }
}