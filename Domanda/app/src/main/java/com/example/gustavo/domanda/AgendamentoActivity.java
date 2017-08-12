package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AgendamentoActivity extends AppCompatActivity {

    private ProfissionalPojo p;
    private ListView listAgendamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        p = (ProfissionalPojo) getIntent().getSerializableExtra("pro");
        //Log.d("regys",e.toString());
        Log.d("ID PRO: ",p.getIdprofissional());

        getAgenda(p.getIdprofissional());

        listAgendamentos = ((ListView)findViewById(R.id.lvAgendamentos));


    }

    public void getAgenda(final String idprofissional){
        final ArrayList<AgendamentoPojo> agenda = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<AgendamentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php?" +
                "idpro="+idprofissional, AgendamentoPojo[].class, null, new Response.Listener<AgendamentoPojo[]>() {
            @Override
            public void onResponse(AgendamentoPojo[] response) {
                for (int i = 0; i < response.length; i++) {
                    AgendamentoPojo ag = new AgendamentoPojo();
                    ag.dia = response[i].dia;
                    ag.hora = response[i].hora;
                    agenda.add(ag);
                }
                Log.d("TAG", "array apos for: " + agenda.toString());

                ArrayAdapter<AgendamentoPojo> adapter = new ArrayAdapter<AgendamentoPojo>(AgendamentoActivity.this, android.R.layout.simple_list_item_1, agenda);
                listAgendamentos = ((ListView)findViewById(R.id.lvAgendamentos));
                listAgendamentos.setAdapter(adapter);

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
