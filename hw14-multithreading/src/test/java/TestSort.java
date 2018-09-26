import org.apache.log4j.Logger;
import org.junit.Test;
import ru.otus.MergeSort;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class TestSort {

    private final static Logger logger = Logger.getLogger(TestSort.class);

    @Test
    public void testSortFourThreas() throws InterruptedException {
        int[] testArray = new int[20];
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

        assertArrayEquals(sortedArray, sortedCopy);
    }

    @Test
    public void testSortThreeThreads() throws InterruptedException {
        int[] testArray = new int[21];
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

        assertArrayEquals(sortedArray, sortedCopy);
    }
    @Test
    public void testSortTwoThreads() throws InterruptedException {
        int[] testArray = new int[14];
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

        assertArrayEquals(sortedArray, sortedCopy);
    }

    @Test
    public void testSortOneThread() throws InterruptedException {
        int[] testArray = new int[13];
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

        assertArrayEquals(sortedArray, sortedCopy);
    }
}
