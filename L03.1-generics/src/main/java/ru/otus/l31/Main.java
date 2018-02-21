package ru.otus.l31;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        MyArrayList<Integer> myList1 = new MyArrayList<>();

        myList1.add(9);
        myList1.add(8);
        myList1.add(7);

        Collections.addAll(myList1, 6, 5);

        MyArrayList<Integer> myList2 = new MyArrayList<>(myList1.size());

        Collections.copy(myList2, myList1);

        System.out.println("Printing elements of original list");
        System.out.println(myList1.get(0));
        System.out.println(myList1.get(1));

        System.out.println("Printing elements of copied list");
        System.out.println(myList2.get(0));
        System.out.println(myList2.get(1));

        Collections.sort(myList2);

        System.out.println("Printing elements of sorted list");
        System.out.println(myList2.get(0));
        System.out.println(myList2.get(1));

    }
}
