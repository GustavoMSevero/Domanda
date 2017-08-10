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

public class ProfissionalAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Profissional> profissionais;

    public ProfissionalAdapter(@NonNull Context context, ArrayList<Profissional> profissionais) {
        super(context, 0, profissionais);
        this.context = context;
        this.profissionais = profissionais;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Profissional profissional = profissionais.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_profissional, null);
        }

        //ENTRAR
        TextView nomeProfissional = (TextView) convertView.findViewById(R.id.tvNomeProfissional);
        TextView funcao = (TextView) convertView.findViewById(R.id.tvFuncao);
        TextView unidade = (TextView) convertView.findViewById(R.id.tvUnidade);

        nomeProfissional.setText(profissional.getNomeProfissional());
        funcao.setText(profissional.getFuncao());
        unidade.setText(profissional.getUnidade());

        return convertView;
    }
}
