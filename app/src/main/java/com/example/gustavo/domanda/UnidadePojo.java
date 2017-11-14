package com.example.gustavo.domanda;

/**
 * Created by Gustavo on 10/08/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnidadePojo implements Serializable {

    @SerializedName("idestabelecimento")
    @Expose
    public String idestabelecimento;
    @SerializedName("idunidade")
    @Expose
    public String idunidade;
    @SerializedName("unidade")
    @Expose
    public String unidade;
    @SerializedName("endereco")
    @Expose
    public String endereco;
    @SerializedName("numero")
    @Expose
    public String numero;

    public String toString(){
        return unidade+"\n"+endereco+", "+numero;
    }

    public String getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(String idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public String getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(String idunidade) {
        this.idunidade = idunidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
