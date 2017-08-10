package com.example.gustavo.domanda;

import java.io.Serializable;

/**
 * Created by Gustavo on 10/08/17.
 */

public class Estabelecimento implements Serializable {
    private Integer idEstabelecimento;
    private String estabelecimento;
    private String endereco;
    private Integer numbero;
    private String cidade;
    private String UF;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer idEstabelecimento, String estabelecimento, String endereco, Integer numbero, String cidade, String UF) {
        this.idEstabelecimento = idEstabelecimento;
        this.estabelecimento = estabelecimento;
        this.endereco = endereco;
        this.numbero = numbero;
        this.cidade = cidade;
        this.UF = UF;
    }

    @Override
    public String toString(){
        return idEstabelecimento+"\n"+estabelecimento+"\n"+endereco+", "+numbero+"\n"+cidade+"/"+UF;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumbero() {
        return numbero;
    }

    public void setNumbero(Integer numbero) {
        this.numbero = numbero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
}
