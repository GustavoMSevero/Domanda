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
    @SerializedName("estabelecimento")
    @Expose
    public String estabelecimento;
    @SerializedName("unidade")
    @Expose
    public String unidade;
    @SerializedName("nome")
    @Expose
    public String nome;
    @SerializedName("funcao")
    @Expose
    public String funcao;
    @SerializedName("idcliente")
    @Expose
    public String idcliente;
    @SerializedName("dia")
    @Expose
    public String dia;
    @SerializedName("hora")
    @Expose
    public String hora;
    private final static long serialVersionUID = 1186977356818629915L;

    public String toString(){
        return estabelecimento+" - "+unidade+"\n"+"Dia "+dia+" das "+hora+"\n"+"Hora com "+nome+" - "+funcao;
    }

    public String getIdagendamentoProfissional() {
        return idagendamentoProfissional;
    }

    public void setIdagendamentoProfissional(String idagendamentoProfissional) {
        this.idagendamentoProfissional = idagendamentoProfissional;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
