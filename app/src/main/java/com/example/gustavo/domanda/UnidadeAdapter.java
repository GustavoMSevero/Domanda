package com.example.gustavo.domanda;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustavo on 10/08/17.
 */

public class UnidadeAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Unidade> unidades;

    public UnidadeAdapter(@NonNull Context context, ArrayList<Unidade> unidades) {
        super(context, 0, unidades);
        this.context = context;
        this.unidades = unidades;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Unidade unidade = unidades.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_unidade, null);
        }

        //ENTRAR
        TextView nomeUnidade = (TextView) convertView.findViewById(R.id.tvUnidade);
        TextView endereco = (TextView) convertView.findViewById(R.id.tvEndereco);
        TextView numero = (TextView) convertView.findViewById(R.id.tvNumero);

        nomeUnidade.setText(unidade.getUnidade());
        endereco.setText(unidade.getEndereco());
        numero.setText(unidade.getNumero());

        return convertView;
    }
}
