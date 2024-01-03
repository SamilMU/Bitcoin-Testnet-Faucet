package com.faucet.bitcointestnetfaucet.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public TimeUtils() {
    }

    public static void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public static Date getFutureInHours(int hours) {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fourhourslater = localDateTime.plusHours(hours);
        return Date.from(fourhourslater.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static long calculateInitialDelay(int hour, int minute, int second) {
        LocalTime currentTime = LocalTime.now();
        LocalTime desiredTime = LocalTime.of(hour, minute, second);
        long initialDelay = currentTime.until(desiredTime, TimeUnit.MINUTES.toChronoUnit());
        if (initialDelay < 0L) {
            initialDelay += 1440L;
        }

        return initialDelay;
    }
}
