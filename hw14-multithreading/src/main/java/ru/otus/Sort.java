package ru.otus;

public class Sort implements Runnable {
    private int[] array;

    public Sort(int[] array) {
        this.array = array;
    }

    public void run() {
        ArraySort.mergeSort(array);
    }
}