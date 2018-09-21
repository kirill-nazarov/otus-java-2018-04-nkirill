package ru.otus;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class MergeSort {

    private final static Logger logger = Logger.getLogger(MergeSort.class);

    public int[] sortArray(int[] array) throws InterruptedException {

        int subArrayLength = array.length / 4;
        int[] sortedArray = new int[array.length];

        int[] subArray1 = new int[subArrayLength];
        int[] subArray2 = new int[subArrayLength];
        int[] subArray3 = new int[subArrayLength];
        int[] subArray4 = new int[subArrayLength];

        //разделим первоначальный массив на 4 части
        System.arraycopy(array, 0, subArray1, 0, subArrayLength);
        System.arraycopy(array, subArrayLength, subArray2, 0, subArrayLength);
        System.arraycopy(array, subArrayLength * 2, subArray3, 0, subArrayLength);
        System.arraycopy(array, subArrayLength * 3, subArray4, 0, subArrayLength);

        //проведем сортировку частей в 4 потоках
        Sort worker1 = new Sort(subArray1);
        Sort worker2 = new Sort(subArray2);
        Sort worker3 = new Sort(subArray3);
        Sort worker4 = new Sort(subArray4);
        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
        worker1.join();
        worker2.join();
        worker3.join();
        worker4.join();
        logger.info(Arrays.toString(subArray1));
        logger.info(Arrays.toString(subArray2));
        logger.info(Arrays.toString(subArray3));
        logger.info(Arrays.toString(subArray4));

        //объединим снова части массива
        System.arraycopy(subArray1, 0, sortedArray, 0, subArrayLength);
        System.arraycopy(subArray2, 0, sortedArray, subArrayLength, subArrayLength);
        System.arraycopy(subArray3, 0, sortedArray, subArrayLength * 2, subArrayLength);
        System.arraycopy(subArray4, 0, sortedArray, subArrayLength * 3, subArrayLength);

        //отсортируем объединенный массив
        new ArraySort().mergeSort(sortedArray);

        return sortedArray;
    }


    class Sort extends Thread {
        private int[] array;

        public Sort(int[] array) {
            this.array = array;
        }

        public void run() {
            new ArraySort().mergeSort(array);
        }
    }
}

