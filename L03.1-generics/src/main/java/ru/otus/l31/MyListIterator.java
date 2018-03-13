package ru.otus.l31;

import java.util.ListIterator;

public class MyListIterator<T> extends MyIterator<T> implements ListIterator<T> {

    MyListIterator(MyArrayList<T> arrayList, int cursor) {
        super(arrayList, cursor);
    }

    @Override
    public boolean hasPrevious() {
        return cursor != 0;
    }

    @Override
    public T previous() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int nextIndex() {
        return cursor;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    //todo implement set method
    @Override
    public void set(T t) {
        arrayList.set(cursor, t);
    }

    @Override
    public void add(T t) {

    }
}
