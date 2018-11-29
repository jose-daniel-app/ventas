package com.business.ventas.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public class AppHashSet<T> extends HashSet<T> {

    public AppHashSet() {
    }

    public AppHashSet(Collection<? extends T> c) {
        super(c);
    }

    public AppHashSet<T> foreach(ForEach<T> foreach) {
        Iterator<T> iterar = super.iterator();
        while (iterar.hasNext()) {
            foreach.foreach(iterar.next());
        }
        return this;
    }

    public T getItem(GetItem getItem){
        Iterator<T> iterar = super.iterator();
        while (iterar.hasNext()) {
            return (T)getItem.getItem(iterar.next());
        }
        return null;
    }

    @FunctionalInterface
    public interface ForEach<T> {
        void foreach(T t);
    }

    public interface GetItem<T> {
        T getItem(T t);
    }

}
