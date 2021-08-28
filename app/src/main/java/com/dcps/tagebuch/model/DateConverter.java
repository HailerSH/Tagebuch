package com.dcps.tagebuch.model;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDateFromLong(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long toLongFromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
