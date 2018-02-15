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
    public static void main(String... args) throws InterruptedException {

        getEmptyObjectSize();
        getEmptyArraySize();
        getEmptyStringSize();
        getEmptyStringSizeNoStringPool();

    }


    private static void getEmptyObjectSize() throws InterruptedException {
        int size = 100_000;
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        Object[] array = new Object[size];
        long mem = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem);
        for (int i = 0; i < size; i++) {
            array[i] = new Object();
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem2);
        System.out.println("Object:" + (mem2 - mem) / size + " bytes");
    }

    private static void getEmptyArraySize() throws InterruptedException {
        int size = 100_000;
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem);
        Object[] array = new Object[size];
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem2);
        System.out.println("Object array:" + (mem2 - mem) / size + " bytes");
    }

    private static void getEmptyStringSize() throws InterruptedException {
        int size = 10_000_000;
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        Object[] array = new Object[size];
        long mem = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem);
        for (int i = 0; i < size; i++) {
            array[i] = new String(""); //String pool
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem2);
        System.out.println("String:" + (mem2 - mem) / size + " bytes");
    }

    private static void getEmptyStringSizeNoStringPool() throws InterruptedException {
        int size = 10_000_000;
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        Object[] array = new Object[size];
        long mem = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem);
        for (int i = 0; i < size; i++) {
            array[i] = new String(new char[0]); //without String pool
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println(mem2);
        System.out.println("String(with new char[0]):" + (mem2 - mem) / size + " bytes");
    }
}
