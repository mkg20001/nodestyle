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
    private Object lisID=new Object(); //ID => event
    private Integer max=10;

    private Array getArray(String name) {
        if (o.get(name)==null) {
            o.set(name,new Array<EventListener>());
        }
        return (Array) o.get(name);
    }

    private EventEmitter register(String name,EventListener e) {
        checkLeak(name);
        lisID.set(e.listenerId.toString(),name);
        emit("newListener",name,e);
        return this;
    }

    private EventEmitter unregister(EventListener e) {
        String event=(String) lisID.get(e.listenerId.toString());
        emit("removeListener",event,e);
        return this;
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
        return register(name,e);
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
        return addListener(name,e);
    }

    public EventEmitter once(String name,EventListener e) {
        getOnce(name).push(e);
        return register(name,e);
    }

    public EventEmitter prependListener(String name,EventListener e) {
        getArray(name).shift(e);
        return register(name,e);
    }

    public EventEmitter prependOnceListener(String name,EventListener e) {
        getOnce(name).shift(e);
        return register(name,e);
    }

    public EventEmitter removeAllListeners() {
        for (String name:eventNames())
            for (EventListener el : listeners(name))
                unregister(el);
        o=new Object();
        once=new Object();
        return this;
    }

    public EventEmitter removeListener(String name,EventListener e) {
        if (lisID.get(e.listenerId.toString()) == null) return this;
        for (Object ob:new Object[]{o,once}) {
            if (ob.get(name)!=null) {
                Array a=new Array();
                Array b=(Array) ob.get(name);
                for (java.lang.Object b2:b.getItems()) {
                    EventListener el=(EventListener) b2;
                    if (!el.listenerId.toString().equalsIgnoreCase(e.listenerId.toString())) a.push(el);
                }
                ob.set(name,a);
            }
        }
        return unregister(e);
    }

    public EventEmitter removeListener(EventListener e) {
        if (lisID.get(e.listenerId.toString()) == null) return this; else return removeListener((String) lisID.get(e.listenerId.toString()),e);
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
