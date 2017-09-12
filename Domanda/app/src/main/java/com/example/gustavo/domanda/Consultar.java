package com.example.gustavo.domanda;

import java.io.Serializable;

/**
 * Created by Gustavo on 06/09/17.
 */

public class Consultar implements Serializable {

    private Integer idAgendamento;
    private Integer idCliente;
    private String dia;
    private String hora;

    public Consultar(Integer idAgendamento, Integer idCliente, String dia, String hora) {
        this.idAgendamento = idAgendamento;
        this.idCliente = idCliente;
        this.dia = dia;
        this.hora = hora;
    }

    public Consultar(){
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProfissional() {
        return idCliente;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idCliente = idProfissional;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

