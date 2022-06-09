package com.example.bikeibmec;

import java.util.List;

public class PedaladasModel {

    public static final String ID = "PEDALADAS_MODEL";

    private List<PedaladaModel> pedaladas;

    public PedaladasModel(List<PedaladaModel> pedaladas) {
        this.pedaladas = pedaladas;
    }

    public List<PedaladaModel> getPedaladas() { return pedaladas; }

}
