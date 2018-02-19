package ru.otus.l21;

import java.lang.management.ManagementFactory;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */
//@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {

    private static final int SIZE = 10_000_000;
    private static final Object[] array = new Object[SIZE];


    public static void main(String... args) throws InterruptedException {


        getEmptyObjectSize();
        getEmptyArraySize();
        getEmptyStringSize();
        getEmptyStringSizeNoStringPool();

    }


    private static void getEmptyObjectSize() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        Thread.sleep(10);
        long mem = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < SIZE; i++) {
            array[i] = new Object();
        }
        System.gc();
        Thread.sleep(10);
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Object:" + (mem2 - mem) / SIZE + " bytes");
    }

    private static void getEmptyArraySize() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        Thread.sleep(10);
        long mem = runtime.totalMemory() - runtime.freeMemory();
        Object[] arrayLocal = new Object[SIZE];
        System.gc();
        Thread.sleep(10);
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Object reference in array:" + (mem2 - mem) / SIZE + " bytes");
    }

    private static void getEmptyStringSize() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        Thread.sleep(10);
        long mem = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < SIZE; i++) {
            array[i] = new String(""); //String pool
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("String:" + (mem2 - mem) / SIZE + " bytes");
    }

    private static void getEmptyStringSizeNoStringPool() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        Thread.sleep(10);
        long mem = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < SIZE; i++) {
            array[i] = new String(new char[0]); //without String pool
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("String(with new char[0]):" + (mem2 - mem) / SIZE + " bytes");
    }
}
