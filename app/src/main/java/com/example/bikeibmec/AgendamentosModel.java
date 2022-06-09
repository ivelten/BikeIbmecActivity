package com.example.bikeibmec;

import java.util.List;

public class AgendamentosModel {

    public static final String ID = "AGENDAMENTOS_MODEL";

    private List<AgendamentoModel> agendamentos;

    public AgendamentosModel(List<AgendamentoModel> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<AgendamentoModel> getAgendamentos() { return agendamentos; }
}
