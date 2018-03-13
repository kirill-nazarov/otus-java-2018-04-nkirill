package ru.otus.l31;

import java.util.*;

public class MyArrayList<T> implements List<T> {


    private static int DEFAULT_CAPACITY = 20;

    private Object[] DATA;

    private int size = 0;


    public MyArrayList() {
        DATA = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int size) {
        this.size = size;
        DATA = new Object[size];
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
        return DATA;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size + 1 > DATA.length) {
            ensureCapacity();
        }
        DATA[size++] = t;
        return true;
    }

    private void ensureCapacity() {
        Object[] newDataArray = new Object[DATA.length + 10];
        System.arraycopy(DATA, 0, newDataArray, 0, DATA.length);
        DATA = newDataArray;
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
        return (T) DATA[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = (T) DATA[index - 1];
        DATA[index - 1] = element;
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
