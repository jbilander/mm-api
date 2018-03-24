package com.creang.common;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
    public static final ZoneId ZONE_ID_UTC_MINUS_TWO = ZoneId.of("Etc/GMT+2");

    public static String getPostTimeUtc(LocalDate raceDayDate, Time postTime, ZoneId zoneId) {
        ZonedDateTime zdt = ZonedDateTime.of(raceDayDate, postTime.toLocalTime(), zoneId);
        return zdt.format(DateTimeFormatter.ISO_INSTANT);
    }
}
