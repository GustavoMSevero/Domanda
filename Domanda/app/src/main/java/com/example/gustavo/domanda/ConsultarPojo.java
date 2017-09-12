package com.example.gustavo.domanda;

/**
 * Created by Gustavo on 06/09/17.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarPojo implements Serializable {

    @SerializedName("idagendamentoProfissional")
    @Expose
    public String idagendamentoProfissional;
    @SerializedName("idcliente")
    @Expose
    public String idcliente;
    @SerializedName("dia")
    @Expose
    public String dia;
    @SerializedName("hora")
    @Expose
    public String hora;
    private final static long serialVersionUID = 5989947660239077050L;

    public String toString(){
        return "Dia "+dia+" as "+hora;
    }

    public String getIdagendamentoProfissional() {
        return idagendamentoProfissional;
    }

    public void setIdagendamentoProfissional(String idagendamentoProfissional) {
        this.idagendamentoProfissional = idagendamentoProfissional;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
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

