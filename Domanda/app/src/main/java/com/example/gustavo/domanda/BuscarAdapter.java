package com.example.gustavo.domanda;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustavo on 25/10/17.
 */

public class BuscarAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<BuscarPojo> buscas;

    public BuscarAdapter(@NonNull Context context, ArrayList<BuscarPojo> buscas) {
        super(context, 0, buscas);
        this.context = context;
        this.buscas = buscas;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        BuscarPojo busca  = buscas.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_estabelecimento, null);
        }

        TextView estabelecimento = (TextView) convertView.findViewById(R.id.tvEstabelecimento);
        TextView endereco = (TextView) convertView.findViewById(R.id.tvEndereco);
        TextView numero = (TextView) convertView.findViewById(R.id.tvNumero);
        TextView cidade = (TextView) convertView.findViewById(R.id.tvCidade);
        TextView uf = (TextView) convertView.findViewById(R.id.tvUF);
        Button cancelar = (Button) convertView.findViewById(R.id.btnCancelar);

        return convertView;
    }
}
