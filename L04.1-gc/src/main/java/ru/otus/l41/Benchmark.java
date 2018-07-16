package ru.otus.l41;


import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Benchmark implements BenchmarkMBean {
    private volatile int size = 100;

    @SuppressWarnings("InfiniteLoopStatement")
    void run() throws InterruptedException {

        installGCMonitoring();

        List<String> array = new ArrayList<>();
        //System.out.println("Starting the loop");
        while (true) {
            for (int i = 0; i < size; i++) {
                array.add(new String(new char[i]));
            }
            //System.out.println("Created " + size + " objects.");
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

                long totalGcDuration = 0;

                @Override
                public void handleNotification(Notification notification, Object handback) {
                    if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                        long duration = info.getGcInfo().getDuration();
                        String gcType = info.getGcAction();
                        long gcId = info.getGcInfo().getId();
                        String gcName = info.getGcName();
                        if ("end of minor GC".equals(gcType)) {
                            gcType = "Young Gen GC";
                        } else if ("end of major GC".equals(gcType)) {
                            gcType = "Old Gen GC";
                        }
                        System.out.println(gcType + ": - " + info.getGcInfo().getId() + " " + info.getGcName() + " (from " + info.getGcCause() + ") " + duration + " milliseconds; start-end times " + info.getGcInfo().getStartTime() + "-" + info.getGcInfo().getEndTime());

                        //Get the information about each memory space, and pretty print it
                        Map<String, MemoryUsage> memoryUsageBeforeGc = info.getGcInfo().getMemoryUsageBeforeGc();
                        Map<String, MemoryUsage> memoryUsageAfterGc = info.getGcInfo().getMemoryUsageAfterGc();
                        for (Map.Entry<String, MemoryUsage> entry : memoryUsageAfterGc.entrySet()) {
                            String name = entry.getKey();
                            MemoryUsage memdetail = entry.getValue();
                            long memInit = memdetail.getInit();
                            long memCommitted = memdetail.getCommitted();
                            long memMax = memdetail.getMax();
                            long memUsed = memdetail.getUsed();
                            MemoryUsage before = memoryUsageBeforeGc.get(name);
                            long beforepercent = ((before.getUsed() * 1000L) / before.getCommitted());
                            long percent = ((memUsed * 1000L) / before.getCommitted()); //>100% when it gets expanded

                            System.out.print(name + (memCommitted == memMax ? "(fully expanded)" : "(still expandable)") + "used: " + (beforepercent / 10) + "." + (beforepercent % 10) + "%->" + (percent / 10) + "." + (percent % 10) + "%(" + ((memUsed / 1048576) + 1) + "MB) / ");
                        }
                        System.out.println();
                        totalGcDuration += info.getGcInfo().getDuration();
                        long percent = totalGcDuration * 1000L / info.getGcInfo().getEndTime();
                        System.out.println("GC cumulated overhead " + (percent / 10) + "." + (percent % 10) + "%");
                    }
                }
            };

            //Add the listener
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
