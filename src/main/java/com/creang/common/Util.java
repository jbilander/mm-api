package com.creang.common;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
    public static final ZoneId ZONE_ID_STHLM = ZoneId.of("Europe/Stockholm");
    public static final ZoneId ZONE_ID_UTC_MINUS_TWO = ZoneId.of("Etc/GMT+2");

    public static String getPostTimeUtc(LocalDate raceDayDate, Time postTime, ZoneId zoneId) {
        ZonedDateTime zdt = ZonedDateTime.of(raceDayDate, postTime.toLocalTime(), zoneId);
        return zdt.format(DateTimeFormatter.ISO_INSTANT);
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
}
