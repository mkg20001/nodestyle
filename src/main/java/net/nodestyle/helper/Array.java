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
package net.nodestyle.helper;

import java.lang.*;
import java.lang.Object;

class ArrayItem<Type> {
    Integer id;
    Type content;
    public ArrayItem(Integer id,Type content) {
        this.id=id;
        this.content=content;
    }
    public Type get() {
        return content;
    }
    public boolean match(Integer id2) {
        return id.toString().equalsIgnoreCase(id2.toString());
    }
    public boolean set(Type content) {
        this.content=content;
        return true;
    }
}

public class Array<Type> { //js like Array
    ArrayItem[] items=new ArrayItem[0];
    private Integer uuid=0;
    public Integer length=0;
    private Integer getUUID() {
        uuid++;
        return uuid;
    }
    private boolean set(Integer name,Type content) {
        for (ArrayItem i:items)
            if (i.match(name))
                return i.set(content);
        rebuild(new ArrayItem(name,content),true,false);
        return false;
    }
    public java.lang.Object get(Integer name) {
        for (ArrayItem i:items)
            if (i.match(name))
                return i.get();
        return null;
    }
    private boolean contains(Integer name) {
        for (ArrayItem i:items)
            if (i.match(name))
                return true;
        return false;
    }
    private boolean rebuild(ArrayItem i,boolean add,boolean shift) {
        final ArrayItem[] i2=items;
        items=new ArrayItem[add?i2.length+1:i2.length-1];
        Integer ii=0;
        if (shift&&add) {
            items[0]=i;
            ii++;
        }
        for (ArrayItem iii:i2)
            if (add||!iii.match(i.id)) {
                items[ii]=iii;
                ii++;
            }
        if (add&&!shift) items[items.length-1]=i;
        this.length=items.length;
        return !add;
    }
    private boolean del(Integer name) {
        for (ArrayItem i:items)
            if (i.match(name))
                return rebuild(i,false,false);
        return false;
    }
    public void clear() {
        items=new ArrayItem[0];
    }

    /* REAL METHODS */
    public boolean push(java.lang.Object... o) {
        for (Object o1:o) rebuild(new ArrayItem(getUUID(),o1),true,false);
        return true;
    }
    public java.lang.Object pop() {
        ArrayItem last=null;
        for (ArrayItem i:items) last=i;
        if (last==null) return last;
        rebuild(last,false,false);
        return last.get();
    }
    public boolean shift(java.lang.Object... o) {
        for (Object o1:o) rebuild(new ArrayItem(getUUID(),o1),true,true);
        return true;
    }
    public Object unshift() {
        ArrayItem i=items[0];
        if (i!=null) rebuild(i,false,false);
        return i;
    }
    public Object[] getItems() {
        Object[] t=new Object[items.length];
        int i=0;
        for (ArrayItem ii:items) {
            t[i]=ii.get();
            i++;
        }
        return t;
    }
}
