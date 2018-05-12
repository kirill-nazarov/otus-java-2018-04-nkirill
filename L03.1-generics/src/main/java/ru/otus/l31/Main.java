package ru.otus.l31;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {


        MyArrayList<Integer> myList1 = new MyArrayList<>();

        for (int i = 11; i > 6; i--) {
            myList1.add(i);
        }

        Collections.addAll(myList1, 6, 5, 4, 3, 2, 1);

        System.out.println("Printing elements of original list");
        for (Integer integer : myList1) {
            System.out.print(integer + " ");
        }


        MyArrayList<Integer> myList2 = new MyArrayList<>(myList1.size());

        Collections.copy(myList2, myList1);


        System.out.println("\nPrinting elements of copied list");
        for (Integer integer : myList2) {
            System.out.print(integer + " ");
        }

        Collections.sort(myList1);

        System.out.println("\nPrinting elements of sorted list");
        for (Integer integer : myList1) {
            System.out.print(integer + " ");
        }
    }
}
