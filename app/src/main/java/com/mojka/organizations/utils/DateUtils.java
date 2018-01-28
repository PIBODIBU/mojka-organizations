package com.mojka.organizations.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final String PATTERN_DEFAULT = "dd MMMM yyyy HH:mm";
    public static final String PATTERN_DATE = "dd.MM.yyyy";
    public static final String PATTERN_DATE_TIME_WITH_BRACKETS = "dd.MM.yyyy (HH:mm)";
    public static final String PATTERN_MIN_SEC = "mm:ss";
    public static final String PATTERN_HOUR_MIN_SEC = "HH:mm:ss";
    public static final String PATTERN_HOUR_MIN = "HH:mm";
    public static final Long HOUR = 60 * 60 * 1000L;

    @SuppressLint("SimpleDateFormat")
    public static String millisToPattern(Long millis, String pattern) {
        if (millis == null)
            return "Дата не указана";

        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String millisToPatternWithUTC(Long millis, String pattern) {
        if (millis == null)
            return "Дата не указана";

        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String millisToPatternWithLocal(Long millis, String pattern) {
        if (millis == null)
            return "Дата не указана";

        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }
}