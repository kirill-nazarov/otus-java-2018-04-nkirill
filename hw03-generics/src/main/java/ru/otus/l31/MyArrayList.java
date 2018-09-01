package ru.otus.l31;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private static final int INCREASE_CAPACITY_BY = 1;
    private int capacity = 10;
    private int numberOfItemsInList = 0;
    private Object[] data;


    public MyArrayList() {
        data = new Object[capacity];
    }

    public MyArrayList(MyArrayList list) {
        data = list.toArray();
        numberOfItemsInList = list.size();
    }

    public MyArrayList(int initCapacity) {
        data = new Object[initCapacity];
    }

    @Override
    public int size() {
        return numberOfItemsInList;
    }

    @Override
    public boolean isEmpty() {
        return numberOfItemsInList == 0;
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
        if (numberOfItemsInList >= capacity) {
            ensureCapacity();
        }
        data[numberOfItemsInList++] = t;
        return true;
    }

    private void ensureCapacity() {
        int old_data_capacity = capacity;
        capacity = capacity + INCREASE_CAPACITY_BY;
        Object[] new_data_array = new Object[capacity];
        System.arraycopy(data, 0, new_data_array, 0, old_data_capacity);
        data = new_data_array;
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
