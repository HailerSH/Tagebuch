package com.dcps.tagebuch.model;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({DateConverter.class})
public abstract class LocalStorage extends RoomDatabase {

    private static LocalStorage localStorage;

    public static LocalStorage getLocalStorage(final Context context) {
        if (localStorage == null) {
            localStorage = Room.databaseBuilder(context, LocalStorage.class,"tagebuch_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return localStorage;
    }
}
