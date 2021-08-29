package com.dcps.tagebuch.controller.comando;

import com.dcps.tagebuch.controller.MainActivityController;
import com.dcps.tagebuch.model.pojo.Pensamiento;

public class EditarComando extends Comando {

    public EditarComando(MainActivityController mainActivityController, Pensamiento pensamiento) {
        super(mainActivityController, pensamiento);
    }

    @Override
    public void ejecutar() {

    }

    @Override
    public void guardarSnapshot() {

    }

    @Override
    public void deshacer() {

    }
}
