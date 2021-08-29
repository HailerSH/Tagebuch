package com.dcps.tagebuch.controller;

import android.app.Activity;

import com.dcps.tagebuch.model.LocalStorage;
import com.dcps.tagebuch.model.dao.CategoriaRoomDAO;
import com.dcps.tagebuch.model.dao.PensamientoRoomDAO;
import com.dcps.tagebuch.model.pojo.Categoria;
import com.dcps.tagebuch.model.pojo.Pensamiento;

import java.util.Date;
import java.util.List;

public class MainActivityController {

    CategoriaRoomDAO categoriaRoomDAO;
    PensamientoRoomDAO pensamientoRoomDAO;

    public MainActivityController(Activity activity) {
        LocalStorage localStorage = LocalStorage.getLocalStorage(activity.getApplicationContext());

        categoriaRoomDAO = localStorage.categoriaRoomDAO();
        pensamientoRoomDAO = localStorage.pensamientoRoomDAO();
    }

    public List<Categoria> getCategorias() {
        return categoriaRoomDAO.getAll();
    }

    public List<Pensamiento> getPensamientos() {
        return pensamientoRoomDAO.getAll();
    }

    public Categoria getCategoria(String categoriaNombre) {
        List<Categoria> categoria = pensamientoRoomDAO.getCategoria(categoriaNombre);
        if (categoria.size() > 0) {
            return  categoria.get(0);
        }
        return null;
    }

    public Pensamiento reportarPensamiento(String titulo, String descripcion, String nombreCategoria) {
        Pensamiento pensamiento = new Pensamiento();
        pensamiento.setTitulo(titulo);
        pensamiento.setDescripcion(descripcion);
        pensamiento.setCategoriaNombre(nombreCategoria);
        pensamiento.setFecha(new Date());

        pensamientoRoomDAO.insertOne(pensamiento);

        return pensamiento;
    }
}
