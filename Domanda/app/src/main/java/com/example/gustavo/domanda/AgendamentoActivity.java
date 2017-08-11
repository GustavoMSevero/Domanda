package com.example.gustavo.domanda;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AgendamentoActivity extends AppCompatActivity {

    private ListView listAgendamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        listAgendamentos = ((ListView)findViewById(R.id.lvAgendamentos));

    }

    public void getAgenda(){
        final ArrayList<AgendamentoPojo> agenda = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<AgendamentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php?" +
                "idpro=", AgendamentoPojo[].class, null, new Response.Listener<AgendamentoPojo[]>() {
            @Override
            public void onResponse(AgendamentoPojo[] response) {
                for (int i = 0; i < response.length; i++) {
                    AgendamentoPojo ag = new AgendamentoPojo();
                    ag.dia = response[i].dia;
                    ag.hora = response[i].hora;
                    agenda.add(ag);
                }

                ArrayAdapter<AgendamentoPojo> adapter = new ArrayAdapter<AgendamentoPojo>(AgendamentoActivity.this, android.R.layout.simple_expandable_list_item_1, agenda);
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
