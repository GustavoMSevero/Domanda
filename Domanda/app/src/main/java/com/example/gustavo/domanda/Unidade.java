package com.example.gustavo.domanda;

import java.io.Serializable;

/**
 * Created by Gustavo on 04/08/17.
 */

public class Unidade implements Serializable {

    private Integer idUnidade;
    private Integer idEstabelecimento;
    private String unidade;
    private String endereco;
    private Integer numero;

    public Unidade(Integer idEstabelecimento, String unidade, String endereco, Integer numero) {
        this.idEstabelecimento = idEstabelecimento;
        this.unidade = unidade;
        this.endereco = endereco;
        this.numero = numero;
    }

    @Override
    public String toString(){
        return unidade="\n"+endereco+", "+numero;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
