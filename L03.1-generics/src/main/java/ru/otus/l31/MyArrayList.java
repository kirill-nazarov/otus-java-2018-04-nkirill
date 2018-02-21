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
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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
        Object[] newDataArray = new Object[DATA.length + 1];
        System.arraycopy(DATA, 0, newDataArray, 0, DATA.length);
        DATA = newDataArray;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return (T) DATA[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = (T) DATA[index];
        DATA[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }




    private class MyIterator implements Iterator<T> {

        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return (T) DATA[currentIndex++];
        }

    }
}
