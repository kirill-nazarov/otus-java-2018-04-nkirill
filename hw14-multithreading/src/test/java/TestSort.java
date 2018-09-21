import org.apache.log4j.Logger;
import org.junit.Test;
import ru.otus.MergeSort;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class TestSort {

    private final static Logger logger = Logger.getLogger(TestSort.class);

    @Test
    public void testSort() throws InterruptedException {
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

        assertArrayEquals(sortedArray, sortedCopy);
    }

}
