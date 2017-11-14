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

public class EstabelecimentoAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<EstabelecimentoPojo> estabelecimentos;

    public EstabelecimentoAdapter(@NonNull Context context, ArrayList<EstabelecimentoPojo> estabelecimentos) {
        super(context, 0, estabelecimentos);
        this.context = context;
        this.estabelecimentos = estabelecimentos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EstabelecimentoPojo estabelecimento = estabelecimentos.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_estabelecimento, null);
        }

        //ENTRAR
        TextView nomeEstabelecimento = (TextView) convertView.findViewById(R.id.tvEstabelecimento);
        TextView endereco = (TextView) convertView.findViewById(R.id.tvEndereco);
        TextView numero = (TextView) convertView.findViewById(R.id.tvNumero);
        TextView cidade = (TextView) convertView.findViewById(R.id.tvCidade);
        TextView UF = (TextView) convertView.findViewById(R.id.tvUF);

        nomeEstabelecimento.setText(estabelecimento.getNome());
        endereco.setText(estabelecimento.getEnd());
        numero.setText(String.valueOf(estabelecimento.getNum()));
        cidade.setText(estabelecimento.getCid());
        UF.setText(estabelecimento.getUf());

        return convertView;
    }
}
