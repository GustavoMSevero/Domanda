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
 * Created by Gustavo on 11/08/17.
 */

public class AgendamentoAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<AgendamentoPojo> agendamentos;

    public AgendamentoAdapter(@NonNull Context context, ArrayList<AgendamentoPojo> agendamentos) {
        super(context, 0, agendamentos);
        this.context = context;
        this.agendamentos = agendamentos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        AgendamentoPojo agendamento  = agendamentos.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_agendamento, null);
        }

        TextView dia = (TextView) convertView.findViewById(R.id.tvAgenda);
        TextView hora = (TextView) convertView.findViewById(R.id.tvHora);

        dia.setText(agendamento.getDia());
        hora.setText(agendamento.getHora());

        return convertView;

    }
}
