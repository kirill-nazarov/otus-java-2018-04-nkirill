package ru.otus.l31;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

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

        MyArrayList<Integer> myList2 = new MyArrayList<>(myList1);

        Collections.copy(myList2, myList1);

        System.out.println("\nPrinting elements of copied list");
        for (Integer integer : myList2) {
            System.out.print(integer + " ");
        }

        Collections.sort(myList2);

        System.out.println("\nPrinting elements of sorted list");
        for (Integer integer : myList2) {
            System.out.print(integer + " ");
        }
    }

    @Test
    public void testMyArrayList() {

        MyArrayList<Integer> emptyList = new MyArrayList<>();
        assertTrue(emptyList.size() == 0); //должен быть 0
        assertTrue(emptyList.isEmpty()); // должно быть true

        MyArrayList<Integer> list = new MyArrayList<>(10);
        assertTrue(list.isEmpty()); // должно быть true
        assertTrue(list.size() == 0); //должен быть 0

        Collections.addAll(list, 1, 2, 3);
        assertTrue(list.size() == 3); //должно быть 3
        assertTrue(!list.isEmpty()); // должно быть false

        MyArrayList<Integer> listWihNulls = new MyArrayList<>();
        listWihNulls.add(null);
        assertTrue(listWihNulls.size() == 1); //должен быть 1
        assertTrue(!listWihNulls.isEmpty()); // должно быть false
    }
}
