package ru.otus.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.ArrayList;
import java.util.List;

public class GcInfo {


    public GcInfo() {
    }

    private List<GarbageCollectionNotificationInfo> notifications = new ArrayList<>();

    public void addNotification(GarbageCollectionNotificationInfo info) {
        notifications.add(info);
    }

    public void printData() {

        System.out.println("Printing stats:");

        long gcTotalDuration = 0;

        for (GarbageCollectionNotificationInfo info : notifications) {
            long duration = info.getGcInfo().getDuration();
            gcTotalDuration += duration;
        }

        for (GarbageCollectionNotificationInfo info : notifications) {

            String type = info.getGcAction();
            if (type.equals("end of minor GC")) {
                type = "Young Generation";
            } else if (type.equals("end of major GC")) {
                type = "Old Generation";
            }
            String name = info.getGcName();
            long id = info.getGcInfo().getId();
            long endTime = info.getGcInfo().getEndTime();

            System.out.println(String.format("%s. Name : %s, Count %s, Total duration : %s ms",
                    type, name, id, gcTotalDuration));

            System.out.println(String.format("Total duration %s m, End time : %s m",
                    (float) gcTotalDuration / 1000 / 60, (float) endTime / 1000 / 60));

            System.out.println(String.format("Average duration per minute : %s m",
                    ((float) gcTotalDuration / endTime)));
        }
    }
}
