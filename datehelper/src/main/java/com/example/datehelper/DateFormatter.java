package com.example.datehelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm";

    public static String formatNow() {
        return format(new Date());
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault());
        return sdf.format(date);
    }
}
