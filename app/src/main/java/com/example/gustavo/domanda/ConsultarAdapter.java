package com.example.gustavo.domanda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Gustavo on 06/09/17.
 */

public class ConsultarAdapter extends ArrayAdapter {

    private Context context;
    //private ArrayList<ConsultarPojo> consultas;
    private List<ConsultarPojo> consultas;

    public ConsultarAdapter(@NonNull Context context, List<ConsultarPojo> consultas) {
        super(context, 0, consultas);
        this.context = context;
        this.consultas = consultas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        final ConsultarPojo consulta  = consultas.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linha_consultar, null);
        }

        TextView estabelecimento = (TextView) convertView.findViewById(R.id.tvEstabelecimento);
        TextView unidade = (TextView) convertView.findViewById(R.id.tvUnidade);
        TextView dia = (TextView) convertView.findViewById(R.id.tvDia);
        TextView hora = (TextView) convertView.findViewById(R.id.tvHora);
        TextView nome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView funcao = (TextView) convertView.findViewById(R.id.tvFuncao);

        dia.setText(consulta.getDia());
        hora.setText(consulta.getHora());
        unidade.setText(consulta.getUnidade());
        estabelecimento.setText(consulta.getEstabelecimento());
        nome.setText(consulta.getNome());
        funcao.setText(consulta.getFuncao());


        return convertView;

    }
}
