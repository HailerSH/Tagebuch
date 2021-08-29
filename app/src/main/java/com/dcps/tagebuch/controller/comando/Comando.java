package com.dcps.tagebuch.controller.comando;

import com.dcps.tagebuch.controller.MainActivityController;
import com.dcps.tagebuch.model.pojo.Pensamiento;

import java.util.LinkedList;

public abstract class Comando {

    private MainActivityController mainActivityController;
    private Pensamiento pensamiento;
    private LinkedList<Pensamiento> snapshot;

    public Comando(MainActivityController mainActivityController, Pensamiento pensamiento) {
        this.mainActivityController = mainActivityController;
        this.pensamiento = pensamiento;
    }

    public abstract void ejecutar();
    public abstract void guardarSnapshot();
    public abstract void deshacer();
}
