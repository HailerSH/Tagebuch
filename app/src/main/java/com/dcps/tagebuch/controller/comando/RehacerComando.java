package com.dcps.tagebuch.controller.comando;

import com.dcps.tagebuch.controller.MainActivityController;
import com.dcps.tagebuch.model.pojo.Pensamiento;

public class RehacerComando extends Comando {

    public RehacerComando(MainActivityController mainActivityController, Pensamiento pensamiento) {
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
