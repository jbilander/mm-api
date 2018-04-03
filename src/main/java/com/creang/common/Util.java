package com.creang.common;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
    public static final ZoneId ZONE_ID_STHLM = ZoneId.of("Europe/Stockholm");
    public static final ZoneId ZONE_ID_UTC_MINUS_TWO = ZoneId.of("Etc/GMT+2");

    public static String getDateTimeUtc(LocalDate localDate, LocalTime localTime, ZoneId zoneId) {
        return getDateTimeUtc(LocalDateTime.of(localDate, localTime), zoneId);
    }

    public static String getDateTimeUtc(LocalDateTime localDateTime, ZoneId zoneId) {
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, zoneId);
        return zdt.format(DateTimeFormatter.ISO_INSTANT);
    }

    public static String getDateTimeUtc(Timestamp timestamp, ZoneId zoneId) {
        return timestamp.toInstant().atZone(zoneId).format(DateTimeFormatter.ISO_INSTANT);
    }

    public static String getDateTimeLocal(Timestamp timestamp, ZoneId zoneId) {
        return timestamp.toInstant().atZone(zoneId).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static int getBetTypeCode(String betType) {

        switch (betType) {
            case "V3":
                return 1;
            case "V4":
                return 2;
            case "V5":
                return 3;
            case "V64":
                return 4;
            case "V65":
                return 5;
            case "V75":
                return 6;
            case "GS75":
                return 7;
            case "V86":
                return 8;
        }

        return 0;
    }

    public static int getHorseGenderCode(String gender) {

        switch (gender) {
            case "H":
                return 1;
            case "S":
                return 2;
            case "V":
                return 3;
        }

        return 0;
    }
}
