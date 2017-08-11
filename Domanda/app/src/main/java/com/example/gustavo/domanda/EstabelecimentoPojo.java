package com.example.gustavo.domanda;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstabelecimentoPojo implements Serializable
{
    @SerializedName("idestabelecimento")
    @Expose
    public String idestabelecimento;
    @SerializedName("nome")
    @Expose
    public String nome;
    @SerializedName("end")
    @Expose
    public String end;
    @SerializedName("num")
    @Expose
    public String num;
    @SerializedName("cid")
    @Expose
    public String cid;
    @SerializedName("uf")
    @Expose
    public String uf;
    private final static long serialVersionUID = -5159666548259993357L;

    public String toString(){
        return idestabelecimento+"\n"+nome+"\n"+end+", "+num+"\n"+cid+"/"+uf;
    }

    public String getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(String idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}