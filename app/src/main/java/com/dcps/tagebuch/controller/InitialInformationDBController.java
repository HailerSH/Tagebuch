package com.dcps.tagebuch.controller;

import android.app.Activity;

import com.dcps.tagebuch.model.LocalStorage;
import com.dcps.tagebuch.model.dao.CategoriaRoomDAO;
import com.dcps.tagebuch.model.dao.PensamientoRoomDAO;
import com.dcps.tagebuch.model.pojo.Categoria;
import com.dcps.tagebuch.model.pojo.Pensamiento;

import java.util.Date;

public class InitialInformationDBController {

    CategoriaRoomDAO categoriaRoomDAO;
    PensamientoRoomDAO pensamientoRoomDAO;

    public InitialInformationDBController(Activity activity) {
        LocalStorage localStorage = LocalStorage.getLocalStorage(activity.getApplicationContext());

        categoriaRoomDAO = localStorage.categoriaRoomDAO();
        pensamientoRoomDAO = localStorage.pensamientoRoomDAO();
    }

    private void addInitialInformation() throws InterruptedException {
        Categoria categoriaAmbiciones = new Categoria();
        categoriaAmbiciones.setNombre("Ambiciones");
        categoriaAmbiciones.setDescripcion("Todos tenemos ambiciones, ¡asegúrate de no olvidarlas!");
        categoriaAmbiciones.setColor("#e8fca1");

        Categoria categoriaRomance = new Categoria();
        categoriaRomance.setNombre("Romance");
        categoriaRomance.setDescripcion("¿Te sientes enamorado?, háblale, ¡no la dejes ir!");
        categoriaRomance.setColor("#ff6694");

        Categoria categoriaTristezas = new Categoria();
        categoriaTristezas.setNombre("Tristezas");
        categoriaTristezas.setDescripcion("Úsala cuando las circunstancias de la vida son más dolorosas que alegres.");
        categoriaTristezas.setColor("#b2afb4");

        categoriaRoomDAO.insertMany(categoriaAmbiciones, categoriaRomance, categoriaTristezas);

        Pensamiento pensamientoEstoyEnamorado = new Pensamiento();
        pensamientoEstoyEnamorado.setCategoriaNombre(categoriaRomance.getNombre());
        pensamientoEstoyEnamorado.setTitulo("Estoy enamorado");
        pensamientoEstoyEnamorado.setDescripcion("Hoy conocí a una mujer cuya mirada me cautivó y no puedo dejar de pensar en ella, ¡fue amor a primera vista!");
        pensamientoEstoyEnamorado.setFecha(new Date());

        pensamientoRoomDAO.insertOne(pensamientoEstoyEnamorado);
        Thread.sleep(1000);

        Pensamiento pensamientoSereMillonario = new Pensamiento();
        pensamientoSereMillonario.setCategoriaNombre(categoriaAmbiciones.getNombre());
        pensamientoSereMillonario.setTitulo("Seré millonario");
        pensamientoSereMillonario.setDescripcion("A partir de mañana seré mi propio jefe, puesto que estaré vendiendo productos de Yanbal, ¡me saldré de la universidad!");
        pensamientoSereMillonario.setFecha(new Date());

        pensamientoRoomDAO.insertOne(pensamientoSereMillonario);
        Thread.sleep(1000);

        Pensamiento pensamientoEllaTieneNovio = new Pensamiento();
        pensamientoEllaTieneNovio.setCategoriaNombre(categoriaTristezas.getNombre());
        pensamientoEllaTieneNovio.setTitulo("Ella tiene novio");
        pensamientoEllaTieneNovio.setDescripcion("Hoy vi a la mujer que me cautivó besándose con otro chico, mi corazón se rompió en mil pedazos. Creo que estaré solo toda la vida.");
        pensamientoEllaTieneNovio.setFecha(new Date());

        pensamientoRoomDAO.insertOne(pensamientoEllaTieneNovio);
        Thread.sleep(1000);
    }

    public void addInformation() throws InterruptedException {
        if (categoriaRoomDAO.getAll().size() == 0 || pensamientoRoomDAO.getAll().size() == 0) {
            addInitialInformation();
        }
    }
}
