package ru.otus.l41;


import java.util.ArrayList;
import java.util.List;

class Benchmark implements BenchmarkMBean {
    private volatile int size = 100;

    @SuppressWarnings("InfiniteLoopStatement")
    void run() throws InterruptedException {

        List<String> array = new ArrayList<>();
        System.out.println("Starting the loop");
        while (true) {
            for (int i = 0; i < size; i++) {
                array.add(new String(new char[i]));
            }
            System.out.println("Created " + size + " objects.");
            for (int i = 0; i < size / 2; i++) {
                array.remove(i);
            }
            Thread.sleep(1000);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
