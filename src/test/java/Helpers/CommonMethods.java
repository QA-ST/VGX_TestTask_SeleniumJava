package Helpers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CommonMethods {
    protected static String timestamp_hh_mm_ss;

    public static void wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateTimestamp_hh_mm_ss() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        return currentTime.format(formatter);
    }

    static {
        timestamp_hh_mm_ss = generateTimestamp_hh_mm_ss();
    }

    public static String getTimestamp_hh_mm_ss() {
        return timestamp_hh_mm_ss;
    }
}