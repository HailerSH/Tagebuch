package com.dcps.tagebuch.controller.comando;

import com.dcps.tagebuch.controller.MainActivityController;
import com.dcps.tagebuch.model.pojo.Pensamiento;

public class ReportarComando extends Comando {

    public ReportarComando(MainActivityController mainActivityController, Pensamiento pensamiento) {
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
