package com.example.crist.mobileandroid.model;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by crist on 02-Dec-17.
 */
public class DateConverter {
    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date.getTime();
    }
}
