package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    private ListView lvReservas;

    private int idusuario;
    private String nome;
    private String sobrenome;
    private RequestQueue mVolleyRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");

        }
        //Toast.makeText(getBaseContext(), "id usuário "+idusuario, Toast.LENGTH_LONG).show();

        getAgenda(idusuario);

    }

    private void getAgenda(final int idusuario) {

        int opcao = 3; //mostrar agenda do cliente
        final ArrayList<ConsultarPojo> agendaCliente = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<ConsultarPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php?" +
                "idcliente="+idusuario+"&opcao="+opcao, ConsultarPojo[].class, null, new Response.Listener<ConsultarPojo[]>() {

            @Override
            public void onResponse(final ConsultarPojo[] response) {
                //Log.d("TAG", "Retorno... " + response.toString());
                for (int i = 0; i < response.length; i++) {
                    ConsultarPojo agc = new ConsultarPojo();
                    agc.idagendamentoProfissional = response[i].idagendamentoProfissional;
                    agc.estabelecimento = response[i].estabelecimento;
                    agc.unidade = response[i].unidade;
                    agc.dia = response[i].dia;
                    agc.hora = response[i].hora;
                    agc.nome = response[i].nome;
                    agc.funcao = response[i].funcao;
                    agendaCliente.add(agc);
                }

                ArrayAdapter<ConsultarPojo> adapter = new ArrayAdapter<ConsultarPojo>(ConsultarActivity.this, android.R.layout.simple_list_item_1, agendaCliente);
                lvReservas = ((ListView)findViewById(R.id.lvReservas));
                lvReservas.setAdapter(adapter);
                lvReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Log.d("Mensagem", "Id "+response[i].idagendamentoProfissional);
                        String idagendamento = response[i].idagendamentoProfissional;

                        Intent intentDetalhesAgendamento = new Intent(ConsultarActivity.this, DetalhesAgendamentoActivity.class);
                        intentDetalhesAgendamento.putExtra("idagendamento", idagendamento);
                        intentDetalhesAgendamento.putExtra("idusuario", idusuario);
                        startActivity(intentDetalhesAgendamento);
                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        });
        queue.add(request);
    }

}
