package net.nodestyle.helper;

/**
 * Created by maciej on 23.09.16.
 */
class Item<Type> {
    String id;
    Type content;
    public Item(String id,Type content) {
        this.id=id;
        this.content=content;
    }
    public Type get() {
        return content;
    }
    public boolean match(String id2) {
        return id.equalsIgnoreCase(id2);
    }
    public boolean set(Type content) {
        this.content=content;
        return true;
    }
}

public class Object<Type> {
    Item[] items=new Item[0];
    public boolean set(String name,Type content) {
        for (Item i:items)
            if (i.match(name))
                return i.set(content);
        rebuild(new Item(name,content),true);
        return false;
    }
    public java.lang.Object get(String name) {
        for (Item i:items)
            if (i.match(name))
                return i.get();
        return null;
    }
    public boolean contains(String name) {
        for (Item i:items)
            if (i.match(name))
                return true;
        return false;
    }
    private boolean rebuild(Item i,boolean add) {
        final Item[] i2=items;
        items=new Item[add?i2.length+1:i2.length-1];
        Integer ii=0;
        for (Item iii:i2)
            if (add||!iii.match(i.id)) {
                items[ii]=iii;
                ii++;
            }
        if (add) items[items.length-1]=i;
        return !add;
    }
    public boolean del(String name) {
        for (Item i:items)
            if (i.match(name))
                return rebuild(i,false);
        return false;
    }
    public String[] list() {
        String[] s=new String[items.length];
        int i=0;
        for (Item ii:items) {
            s[i]=ii.id;
            i++;
        }
        return s;
    }
    public void clear() {
        items=new Item[0];
    }
}
