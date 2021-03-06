package com.dcps.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dcps.tagebuch.model.pojo.Categoria;
import com.dcps.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDAO {

    @Query("SELECT * FROM pensamientos")
    List<Pensamiento> getAll();

    @Query("SELECT * FROM pensamientos WHERE fecha = :fecha")
    List<Pensamiento> getByFecha(String fecha);

    @Query("SELECT * FROM categorias WHERE nombre = :categoriaNombre")
    List<Categoria> getCategoria(String categoriaNombre);

    @Insert
    void insertMany(Pensamiento ... pensamientos);
    @Insert
    void insertOne(Pensamiento pensamiento);

    @Update
    void updateAll(Pensamiento ... pensamientos);
    @Update
    void updateList(List<Pensamiento> pensamientos);
    @Update
    void updateOne(Pensamiento pensamiento);

    @Delete
    void deleteMany(Pensamiento... pensamientos);
    @Query("DELETE FROM pensamientos WHERE fecha = :fecha")
    void deleteByFecha(String fecha);
    @Query("DELETE FROM pensamientos")
    void deleteAll();
}
