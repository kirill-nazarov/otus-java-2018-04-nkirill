package ru.otus.l41;


import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Benchmark implements BenchmarkMBean {
    private volatile int size = 100;
    private static GcStats stats = new GcStats();

    void run() throws InterruptedException {

        installGCMonitoring();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                stats.printData();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 10000);

        List<String> array = new ArrayList<>();
        while (true) {
            for (int i = 0; i < size; i++) {
                array.add(new String(new char[i]));
            }
            for (int i = 0; i < size / 2; i++) {
                array.remove(i);
            }
            Thread.sleep(1000);
        }
    }

    static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = new NotificationListener() {
                @Override
                public void handleNotification(Notification notification, Object handback) {
                    if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                        stats.addNotification(info);
                    }
                }
            };

            emitter.addNotificationListener(listener, null, null);
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
