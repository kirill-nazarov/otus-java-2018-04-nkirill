package ru.otus.l31;

import java.util.*;

public class MyArrayList<T> implements List<T> {


    private static final int DEFAULT_CAPACITY = 10;
    private static final int INCREASE_CAPACITY = 1;

    private Object[] data = {};

    /**
     * The size of the MyArrayList (the number of elements it contains).
     */
    private int size = 0;


    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initSize) {
        size = initSize;
        data = new Object[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this, 0);
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size + 1 > data.length) {
            ensureCapacity();
        }
        data[size++] = t;
        return true;
    }

    private void ensureCapacity() {
        Object[] newDataArray = new Object[data.length + INCREASE_CAPACITY];
        System.arraycopy(data, 0, newDataArray, 0, data.length);
        data = newDataArray;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = (T) data[index - 1];
        data[index - 1] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<>(this, 0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<>(this, index);
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


}
