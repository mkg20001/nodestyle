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

import net.nodestyle.events.EventEmitter;
import net.nodestyle.events.EventListener;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maciej on 24.09.16.
 */
public class eventEmitterTest {
    EventEmitter e=new EventEmitter();
    boolean res=false;
    Integer count=0;

    @Test
    public void listenerAndEmit() throws Exception {
        res=false;
        count=0;
        e.addListener("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                if (!res) {
                    res=true;
                    assertEquals("test event first execute",true,count.toString().equalsIgnoreCase("0"));
                    count++;
                } else {
                    assertEquals("test event second execute",true,count.toString().equalsIgnoreCase("1"));
                    count++;
                }
            }
        });
        e.emit("test",true);
        e.emit("test",true);
        assertEquals("count is 2","2",count.toString());
        assertEquals("test is anything fails?!","1",count.toString());
    }

    @Test
    public void onceAndEmit() throws Exception {
        res=false;
        count=0;
        e.once("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                if (!res) {
                    res=true;
                    assertEquals("test event first execute",true,count.toString().equalsIgnoreCase("0"));
                    count++;
                } else {
                    assertEquals("test event second execute - should not happen",true,false);
                }
            }
        });
        e.emit("test",true);
        e.emit("test",true);
        assertEquals("count is 1","1",count.toString());
    }

    @Test
    public void eventNames() throws Exception {
        e.removeAllListeners();
        e.on("test", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        e.once("test2", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        e.on("test2", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        assertArrayEquals("contains test and test2",new String[]{"test","test2"},e.eventNames());
    }

    @Test
    public void prependListener() throws Exception {
        count=0;
        e.addListener("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                count++;
                assertEquals("event is second","2",count.toString());
            }
        });
        e.prependListener("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                count++;
                assertEquals("event is first","1",count.toString());
            }
        });
        e.emit("test",true);
        count=0;
        e.emit("test",true);
        assertEquals("count is 2","2",count.toString());
    }

    @Test
    public void prependOnceListener() throws Exception {
        count=0;
        e.once("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                count++;
                assertEquals("event is second","2",count.toString());
            }
        });
        e.prependOnceListener("test", new EventListener() {
            @Override
            public void onEvent(java.lang.Object... o) {
                count++;
                assertEquals("event is first","1",count.toString());
            }
        });
        e.emit("test",true);
        e.emit("test",true);
        assertEquals("count is 2","2",count.toString());
    }

    @Test
    public void removeAllListeners() throws Exception {
        e.on("test", new EventListener() {
            @Override
            public void onEvent(Object... o) {
                assertEquals("should not be executed",true,false);
            }
        });
        e.removeAllListeners();
        assertArrayEquals("eventNames is empty",new String[0],e.eventNames());
        e.emit("test");
    }

    @Test
    public void removeListener() throws Exception {
        e.removeAllListeners();
        count=0;
        EventListener el=new EventListener() {
            @Override
            public void onEvent(Object... o) {
                assertEquals("should not be executed",true,false);
            }
        };
        EventListener el2=new EventListener() {
            @Override
            public void onEvent(Object... o) {
                count++;
            }
        };
        e.on("test", el);
        e.on("test",el2);
        e.removeListener("test",el);
        e.emit("test");
        assertEquals("count is 1","1",count.toString());
    }

    @Test
    public void setMaxListeners() throws Exception {
        e.setMaxListeners(20);
        assertEquals("max listeners is 20","20",e.getMaxListeners().toString());
    }

    @Test
    public void listeners() throws Exception {
        e.removeAllListeners();
        EventListener e1=new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        };
        EventListener e2=new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        };
        e.on("test",e1);
        e.once("test",e2);
        assertArrayEquals("e1 and e2 as listeners for test",new EventListener[]{e1,e2},e.listeners("test"));
    }

    @Test
    public void listenerCount() throws Exception {
        e.removeAllListeners();
        e.on("test", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        e.once("test2", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        e.on("test2", new EventListener() {
            @Override
            public void onEvent(Object... o) {

            }
        });
        assertEquals("2 listeners for test2","2",e.listenerCount("test2").toString());
        assertEquals("1 listener for test","1",e.listenerCount("test").toString());
    }

}