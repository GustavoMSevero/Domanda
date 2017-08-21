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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AgendamentoActivity extends AppCompatActivity {

    private ProfissionalPojo p;
    private ListView listAgendamentos;
    private Spinner spinnerHorario;
    private String dadoDia;
    private String dadoHora;

    //private horario adapter;

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

        spinnerHorario = (Spinner) findViewById(R.id.spHorario);

        // Colocado os valores no spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spHorario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapter);

        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0){
                    Toast.makeText(getBaseContext(), "8:00 - 9:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "8:00 - 9:00";
                }else if (i == 1){
                    Toast.makeText(getBaseContext(), "9:00 - 10:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "9:00 - 10:00";
                }else if (i == 2){
                    Toast.makeText(getBaseContext(), "10:00 - 11:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "10:00 - 11:00";
                }else if (i == 3){
                    Toast.makeText(getBaseContext(), "11:00 - 12:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "11:00 - 12:00";
                }else if (i == 4){
                    Toast.makeText(getBaseContext(), "12:00 - 13:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "12:00 - 13:00";
                }else if (i == 5){
                    Toast.makeText(getBaseContext(), "13:00 - 14:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "13:00 - 14:00";
                }else if (i == 6){
                    Toast.makeText(getBaseContext(), "14:00 - 15:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "14:00 - 15:00";
                }else if (i == 7){
                    Toast.makeText(getBaseContext(), "15:00 - 16:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "15:00 - 16:00";
                }else if (i == 8){
                    Toast.makeText(getBaseContext(), "16:00 - 17:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "16:00 - 17:00";
                }else if (i == 9){
                    Toast.makeText(getBaseContext(), "17:00 - 18:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "17:00 - 18:00";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void setAgendar(){
        //String dadoDia =
        String dadoHora = spinnerHorario.getSelectedItem().toString();
    }


    public void getAgenda(final String idprofissional){

        final ArrayList<AgendamentoPojo> agenda = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<AgendamentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php?" +
                "idpro="+idprofissional, AgendamentoPojo[].class, null, new Response.Listener<AgendamentoPojo[]>() {
            @Override
            public void onResponse(AgendamentoPojo[] response) {
                //Log.d("TAG", "Esse é o response: " + response);
                for (int i = 0; i < response.length; i++) {
                    AgendamentoPojo ag = new AgendamentoPojo();
                    ag.dia = response[i].dia;
                    ag.hora = response[i].hora;
                    agenda.add(ag);
                }
                //Log.d("TAG", "array apos for: " + agenda.toString());

                ArrayAdapter<AgendamentoPojo> adapter = new ArrayAdapter<AgendamentoPojo>(AgendamentoActivity.this, android.R.layout.simple_list_item_1, agenda);
                listAgendamentos = ((ListView)findViewById(R.id.lvAgendamentos));
                listAgendamentos.setAdapter(adapter);
                adapter.notifyDataSetChanged();

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
