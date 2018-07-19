package ru.otus.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.ArrayList;
import java.util.List;

public class GcStats {

    public GcStats() {
    }

    private List<GarbageCollectionNotificationInfo> notifications = new ArrayList<>();

    public void addNotification(GarbageCollectionNotificationInfo info) {
        notifications.add(info);
    }

    private void notificationsInfo() {
        for (GarbageCollectionNotificationInfo info : notifications) {
            System.out.println(info.getGcAction());
            System.out.println(info.getGcName());
            System.out.println(info.getGcInfo().getDuration());
            System.out.printf(info.getGcCause());
        }
    }

    @Override
    public String toString() {
        return "GcStats{" +
                "notifications=" + notifications.size() + '}';
    }

    public void printData() {
        notificationsInfo();
    }
}
