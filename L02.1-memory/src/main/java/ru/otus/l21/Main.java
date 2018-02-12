package ru.otus.l21;

/**
 * To run:
 * java -javaagent:target\L02.1-memory-1.0-SNAPSHOT.jar  -jar target\L02.1-memory-1.0-SNAPSHOT.jar
 * <p>
 */

public class Main {
    public static void main(String... args) {

        System.out.println("Memory size for 1 empty object");
        printObjectSize(new Object());
        System.out.println("Memory size for empty array for 1 object");
        Object[] array = new Object[1];
        printObjectSize(array);
        System.out.println("Memory size for empty array for 10 object");
        Object[] arrayOf10 = new Object[10];
        printObjectSize(arrayOf10);
        System.out.println("Memory size for String  object");
        printObjectSize(new String(""));
        System.out.println("Memory size for array of 10 String objects");
        for (int i = 0; i < 10; i++) {
            arrayOf10[i] = new String(new char[0]);
        }
        printObjectSize(arrayOf10);
    }

    private static void printObjectSize(Object obj) {
        System.out.println("Object size = " + InstrumentationAgent.getObjectSize(obj) + " bytes");

    }
}
