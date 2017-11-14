package com.example.gustavo.domanda;

import java.io.Serializable;

/**
 * Created by Gustavo on 11/08/17.
 */

public class Agendamento implements Serializable {

    private Integer idAgemdanto;
    private Integer idProfissional;
    private String dia;
    private String hora;

    public Agendamento(Integer idAgemdanto, Integer idProfissional, String dia, String hora) {
        this.idAgemdanto = idAgemdanto;
        this.idProfissional = idProfissional;
        this.dia = dia;
        this.hora = hora;
    }

    public Agendamento(){
    }

    public String toString(){
        return idAgemdanto+"\n"+idProfissional+"\n"+dia+", "+hora;
    }

    public Integer getIdAgemdanto() {
        return idAgemdanto;
    }

    public void setIdAgemdanto(Integer idAgemdanto) {
        this.idAgemdanto = idAgemdanto;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
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
