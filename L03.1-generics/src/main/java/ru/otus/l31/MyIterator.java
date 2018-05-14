package ru.otus.l31;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {

    MyArrayList arrayList;
    int size;
    int cursor;       // index of next element to return

    MyIterator(MyArrayList<T> arrayList, int cursor) {
        this.arrayList = arrayList;
        this.cursor = cursor;
        this.size = arrayList.size();
    }


    @Override
    public boolean hasNext() {
        return cursor != size;
    }

    @Override
    public T next() {
        Object[] elementData = arrayList.toArray();
        return (T) elementData[cursor++];
    }
}

