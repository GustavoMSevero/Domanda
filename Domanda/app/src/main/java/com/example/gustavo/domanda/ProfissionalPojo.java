package com.example.gustavo.domanda;

/**
 * Created by Gustavo on 11/08/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfissionalPojo {

    @SerializedName("idprofissional")
    @Expose
    public String idprofissional;
    @SerializedName("nome")
    @Expose
    public String nome;
    @SerializedName("funcao")
    @Expose
    public String funcao;

    public String toString(){
        return idprofissional+"\n"+nome +" - "+funcao;
    }

    public String getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(String idprofissional) {
        this.idprofissional = idprofissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
