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
package net.nodestyle.events;

import net.nodestyle.helper.Array;
import net.nodestyle.helper.Object;
import net.nodestyle.helper.Warn;

public class EventEmitter {
    private Object o=new Object();
    private Object once=new Object();
    private Integer max=10;

    private Array getArray(String name) {
        if (o.get(name)==null) {
            o.set(name,new Array<EventListener>());
        }
        return (Array) o.get(name);
    }

    private Array getOnce(String name) {
        if (once.get(name)==null) {
            once.set(name,new Array<EventListener>());
        }
        return (Array) once.get(name);
    }

    private void checkLeak(String name) {
        //Possible EventEmitter memory leak detected. COUNT NAME listeners added. Use emitter.setMaxListeners() to increase limit
        Integer count=0;
        for (Array a:new Array[]{getArray(name),getOnce(name)}) {
            count = count + a.length;
        }
        Integer max2=max+1;
        if (count.toString().equalsIgnoreCase(max2.toString()))
            Warn.warn("Possible EventEmitter memory leak detected. " + count + " " + name + " listeners added. Use emitter.setMaxListeners() to increase limit");
    }

    public EventEmitter addListener(String name,EventListener e) {
        getArray(name).push(e);
        checkLeak(name);
        return this;
    }

    public void emit(String name,java.lang.Object... o) {
        for (Array a:new Array[]{getArray(name),getOnce(name)}) {
            for (java.lang.Object el:a.getItems()) {
                EventListener e=(EventListener) el;
                e.onEvent(o);
            }
        }
        once.del(name);
    }

    public String[] eventNames() {
        Object res=new Object();
        for (Object ob:new Object[]{o,once}) {
            for (String s:ob.list()) {
                Array a=(Array) ob.get(s);
                if (!a.length.toString().equalsIgnoreCase("0")) {
                    res.set(s,true);
                }
            }
        }
        return res.list();
    }

    public EventEmitter on(String name,EventListener e) {
        getArray(name).push(e);
        checkLeak(name);
        return this;
    }

    public EventEmitter once(String name,EventListener e) {
        getOnce(name).push(e);
        checkLeak(name);
        return this;
    }

    public EventEmitter prependListener(String name,EventListener e) {
        getArray(name).shift(e);
        checkLeak(name);
        return this;
    }

    public EventEmitter prependOnceListener(String name,EventListener e) {
        getOnce(name).shift(e);
        checkLeak(name);
        return this;
    }

    public EventEmitter removeAllListeners() {
        o=new Object();
        once=new Object();
        return this;
    }

    public EventEmitter removeListener(String name) {
        for (Object a:new Object[]{o,once}) {
            a.del(name);
        }
        return this;
    }

    public EventEmitter setMaxListeners(Integer n) {
        max=n;
        return this;
    }

    public EventListener[] listeners(String name) {
        Array res=new Array();
        for (Array a:new Array[]{getArray(name),getOnce(name)}) {
            for (java.lang.Object o:a.getItems()) {
                res.push(o);
            }
        }
        EventListener[] all=new EventListener[res.length];
        Integer i=0;
        for (java.lang.Object o:res.getItems()) {
            all[i]=(EventListener) o;
            i++;
        }
        return all;
    }

    public Integer listenerCount(String name) {
        Integer count=0;
        for (Array a:new Array[]{getArray(name),getOnce(name)}) count = count + a.length;
        return count;
    }

    public Integer getMaxListeners() {
        return max;
    }
}
