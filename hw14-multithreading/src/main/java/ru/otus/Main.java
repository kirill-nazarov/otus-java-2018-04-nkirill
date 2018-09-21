package ru.otus;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by knazarov.
 */

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String... args) throws InterruptedException {
        //Из за того что массив делится на 4 части  размер массива должен быть кратен 4
        int[] testArray = new int[12];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (int) (Math.random() * 100);
        }
        int[] sortedCopy = testArray.clone();
        //сортировка библиотечным методом
        Arrays.sort(sortedCopy);
        //сортировка вручную
        MergeSort mergeSort = new MergeSort();
        int[] sortedArray = mergeSort.sortArray(testArray);

        logger.info("sort 1: " + Arrays.toString(sortedArray));
        logger.info("sort 2: " + Arrays.toString(sortedCopy));
    }
}
