package com.dcps.tagebuch.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dcps.tagebuch.model.dao.CategoriaRoomDAO;
import com.dcps.tagebuch.model.dao.PensamientoRoomDAO;
import com.dcps.tagebuch.model.pojo.Categoria;
import com.dcps.tagebuch.model.pojo.Pensamiento;

@Database(entities = {Categoria.class,
                      Pensamiento.class},
        version = 1)
@TypeConverters({DateConverter.class})
public abstract class LocalStorage extends RoomDatabase {

    public abstract CategoriaRoomDAO categoriaRoomDAO();
    public abstract PensamientoRoomDAO pensamientoRoomDAO();

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
