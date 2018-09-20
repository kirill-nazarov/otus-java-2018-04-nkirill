package ru.otus;

import java.util.Arrays;

/**
 * Created by knazarov.
 */

public class Main {

    public static void main(String... args) {
        int[] array = new int[30];
        for (int i = 0; i < 30; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        System.out.println("Not sorted array:");
        for (int i = 0; i < 30; i++) {
            System.out.print(" " + array[i]);
        }

        Arrays.sort(array);
        System.out.println("");
        System.out.println("Sorted array:");
        for (int i = 0; i < 30; i++) {
            System.out.print(" " + array[i]);
        }

    }
}
