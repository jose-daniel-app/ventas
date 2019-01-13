package com.business.ventas.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class Lista<T> extends ArrayList<T> {

    Filtar filter;
    Foreach foreach;

    public Lista() {
        super();
    }

    public Lista(List<T> list) {
        super(list);
    }

    public Lista<T> agregar(T t) {
        super.add(t);
        return this;
    }

    public Lista<T> filtrar(Filtar<T> filter) {
        Lista<T> lista = new Lista<>();
        ListIterator<T> iterar = super.listIterator();
        while (iterar.hasNext()) {
            T t = iterar.next();
            if (filter.iterar(t)) {
                lista.add(t);
            }
        }
        return lista;
    }

    public Lista<T> editarItemDelaLista(Foreach<T> iterador) {
        Lista<T> lista = new Lista<>();
        ListIterator<T> iterar = super.listIterator();
        while (iterar.hasNext()) {
            T t = iterar.next();
            iterador.iterar(t);
            lista.add(t);
        }
        return lista;
    }

    public Lista<T> foreach(Foreach<T> foreach) {
        ListIterator<T> iterar = super.listIterator();
        while (iterar.hasNext()) {
            foreach.iterar(iterar.next());
        }
        return this;
    }

    @FunctionalInterface
    public interface Filtar<T> {
        boolean iterar(T item);
    }

    @FunctionalInterface
    public interface Foreach<T> {
        void iterar(T item);
    }

}
