package com.dcps.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dcps.tagebuch.model.pojo.Categoria;

import java.util.List;

@Dao
public interface CategoriaRoomDAO {

    @Query("SELECT * FROM categorias")
    List<Categoria> getAll();

    @Query("SELECT * FROM categorias WHERE nombre = :nombre")
    List<Categoria> getByNombre(String nombre);

    @Insert
    void insertMany(Categoria ... categorias);
    @Insert
    void insertOne(Categoria categoria);

    @Update
    void updateAll(Categoria ... categorias);
    @Update
    void updateList(List<Categoria> categorias);
    @Update
    void updateOne(Categoria categoria);

    @Delete
    void deleteMany(Categoria... categorias);
    @Query("DELETE FROM categorias WHERE nombre = :nombre")
    void deleteByNombre(String nombre);
    @Query("DELETE FROM categorias")
    void deleteAll();
}
