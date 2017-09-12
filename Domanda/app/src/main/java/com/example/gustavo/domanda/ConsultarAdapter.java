package com.example.gustavo.domanda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustavo on 06/09/17.
 */

public class ConsultarAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ConsultarPojo> consultas;

    public ConsultarAdapter(@NonNull Context context, ArrayList<ConsultarPojo> consultas) {
        super(context, 0, consultas);
        this.context = context;
        this.consultas = consultas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        ConsultarPojo consulta  = consultas.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_consultar, null);
        }

        TextView dia = (TextView) convertView.findViewById(R.id.tvDia);
        TextView hora = (TextView) convertView.findViewById(R.id.tvHora);

        dia.setText(consulta.getDia());
        hora.setText(consulta.getHora());

        return convertView;

    }
}
