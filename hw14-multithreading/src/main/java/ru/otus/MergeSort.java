package ru.otus;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    private final static Logger logger = Logger.getLogger(MergeSort.class);

    public int[] sortArray(int[] array) throws InterruptedException {

        int[] sortedArray = new int[array.length];

        //в зависимисти от длины входного массива количество потоков будет от 1 до 4
        int threadsNum = 1;
        if (array.length % 4 == 0) {
            threadsNum = 4;
        } else if (array.length % 3 == 0) {
            threadsNum = 3;
        } else if (array.length % 2 == 0) {
            threadsNum = 2;
        }

        logger.info("Threads number: " + threadsNum);

        int subArrayLength = array.length / threadsNum;

        //Составим список для разделения на подмассивы
        List<int[]> subArrayList = new ArrayList<>();
        for (int i = 0; i < threadsNum; i++) {
            subArrayList.add(new int[subArrayLength]);
        }


        //разделим  массив на threadsNum частей
        for (int i = 0; i < threadsNum; i++) {
            System.arraycopy(array, subArrayLength * i, subArrayList.get(i), 0, subArrayLength);
        }

        //проведем сортировку частей в 4 потоках
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsNum; i++) {
            threads.add(new Thread(new Sort(subArrayList.get(i))));
        }
        for (int i = 0; i < threadsNum; i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threadsNum; i++) {
            threads.get(i).join();
        }

        for (int i = 0; i < threadsNum; i++) {
            logger.info(Arrays.toString(subArrayList.get(i)));
        }

        //объединим части массива
        for (int i = 0; i < threadsNum; i++) {
            System.arraycopy(subArrayList.get(i), 0, sortedArray, subArrayLength * i, subArrayLength);
        }

        //отсортируем объединенный массив
        ArraySort.mergeSort(sortedArray);

        return sortedArray;
    }
}

