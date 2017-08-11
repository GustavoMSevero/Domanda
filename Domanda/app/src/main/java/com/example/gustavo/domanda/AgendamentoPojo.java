package com.example.gustavo.domanda;

/**
 * Created by Gustavo on 11/08/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgendamentoPojo {

    @SerializedName("idagendamentoProfissional")
    @Expose
    public String idagendamentoProfissional;
    @SerializedName("idprofissional")
    @Expose
    public String idprofissional;
    @SerializedName("dia")
    @Expose
    public String dia;
    @SerializedName("hora")
    @Expose
    public String hora;

    public String toString(){
        return idagendamentoProfissional+"\n"+idprofissional+"\n"+dia+" - "+hora;
    }

    public String getIdagendamentoProfissional() {
        return idagendamentoProfissional;
    }

    public void setIdagendamentoProfissional(String idagendamentoProfissional) {
        this.idagendamentoProfissional = idagendamentoProfissional;
    }

    public String getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(String idprofissional) {
        this.idprofissional = idprofissional;
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
