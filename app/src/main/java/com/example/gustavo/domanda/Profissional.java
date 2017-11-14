package com.example.gustavo.domanda;

import java.io.Serializable;

/**
 * Created by Gustavo on 10/08/17.
 */

public class Profissional implements Serializable {

    private Integer idProfissional;
    private String unidade;
    private Integer idUnidade;
    private String nomeProfissional;
    private String funcao;

    public Profissional(Integer idProfissional, String unidade, Integer idUnidade, String nomeProfissional, String funcao) {
        this.idProfissional = idProfissional;
        this.unidade = unidade;
        this.idUnidade = idUnidade;
        this.nomeProfissional = nomeProfissional;
        this.funcao = funcao;
    }

    public String toString(){
        return nomeProfissional+"\n"+funcao+"\n"+unidade;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
