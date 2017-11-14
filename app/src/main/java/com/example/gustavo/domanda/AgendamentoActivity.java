package com.example.gustavo.domanda;

import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.gustavo.domanda.R.id.edtNome;
import static com.example.gustavo.domanda.R.id.edtSobrenome;

public class AgendamentoActivity extends AppCompatActivity {

    private ProfissionalPojo p;
    private ListView listAgendamentos;
    private Spinner spinnerHorario;

    private String dadoHora;
    private StringCharacterIterator editText;
    private Calendar myCalendar;
    private String diaAgenda;

    private int idusuario;
    private String nome;
    private String sobrenome;

    private Button botaoAgenda;
    private String idprofissional;
    private RequestQueue mVolleyRequest;
    private String estabelecimento;
    private String unidade;
    private String profissional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
            estabelecimento = extra.getString("estabelecimento");
            idprofissional = extra.getString("pro");
            profissional = extra.getString("profissinal");
            unidade = extra.getString("unid");
        }
        //Toast.makeText(this, "extra "+extra, Toast.LENGTH_SHORT).show();
        Log.d("DADOS AA: ",extra.toString());

        final Calendar myCalendar = Calendar.getInstance();

        p = (ProfissionalPojo) getIntent().getSerializableExtra("pro");
        //Log.d("ID PRO: ",p.getIdprofissional());
        idprofissional = p.getIdprofissional();

        mVolleyRequest = Volley.newRequestQueue(this);

        getAgenda(idprofissional);

        listAgendamentos = ((ListView)findViewById(R.id.lvAgendamentos));
        botaoAgenda = ((Button) findViewById(R.id.btnAgendar));

        EditText editText = ((EditText)findViewById(R.id.edtDia));

        spinnerHorario = (Spinner) findViewById(R.id.spHorario);

        // Colocado os valores no spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spHorario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapter);

        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 1){
                    //Toast.makeText(getBaseContext(), "8:00 - 9:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "8:00 - 9:00";
                }else if (i == 2){
                    //Toast.makeText(getBaseContext(), "9:00 - 10:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "9:00 - 10:00";
                }else if (i == 3){
                    //Toast.makeText(getBaseContext(), "10:00 - 11:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "10:00 - 11:00";
                }else if (i == 4){
                    //Toast.makeText(getBaseContext(), "11:00 - 12:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "11:00 - 12:00";
                }else if (i == 5){
                    //Toast.makeText(getBaseContext(), "12:00 - 13:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "12:00 - 13:00";
                }else if (i == 6){
                    //Toast.makeText(getBaseContext(), "13:00 - 14:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "13:00 - 14:00";
                }else if (i == 7){
                    //Toast.makeText(getBaseContext(), "14:00 - 15:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "14:00 - 15:00";
                }else if (i == 8){
                    //Toast.makeText(getBaseContext(), "15:00 - 16:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "15:00 - 16:00";
                }else if (i == 9){
                    //Toast.makeText(getBaseContext(), "16:00 - 17:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "16:00 - 17:00";
                }else if (i == 10){
                    //Toast.makeText(getBaseContext(), "17:00 - 18:00", Toast.LENGTH_LONG).show();
                    String dadoHora = "17:00 - 18:00";
                }else{
                    String dadoHora = "";
                }

                if(spinnerHorario.getSelectedItemPosition()!=0) {
                    dadoHora = spinnerHorario.getSelectedItem().toString();
                    exibeHora();
                }
                //Log.d("HORA", dadoHora);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }



        });


        //Cria o Calendário
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar);

            }

        };

        //Método que pega a data quando clicado
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AgendamentoActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(myCalendar.getTime());



    //Final do onCreate
    }


    public void exibeDia(){
        final TextView tvDia = ((TextView) findViewById(R.id.tvDia));
        tvDia.setText(diaAgenda);
        //dia = tvDia.toString();
    }

    public void exibeHora(){
        final TextView tvHora = ((TextView) findViewById(R.id.tvHora));
        tvHora.setText(dadoHora);
        //hora = tvHora.toString();
    }


    private void updateLabel(Calendar myCalendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        diaAgenda = sdf.format(myCalendar.getTime());
        exibeDia();
        //Log.d("TAG", "DATA " + diaAgenda);
    }

    public void agendar(View view) throws JSONException {
        //Log.d("TAG", "idpro " + idprofissional + " idusuario " + idusuario + " nome " +nome+ " sobrenome " +sobrenome+ " estabelecimento " + estabelecimento + " unidade " + unid);
        if(idusuario != 0){
            int opcao = 1; //Agendar
            JSONObject agendaJsonObject = new JSONObject();
            agendaJsonObject.put("idpro", idprofissional);
            agendaJsonObject.put("idusuario", idusuario);
            agendaJsonObject.put("nome", nome);
            agendaJsonObject.put("sobrenome", sobrenome);
            agendaJsonObject.put("estabelecimento", estabelecimento);
            agendaJsonObject.put("unidade", unidade);
            agendaJsonObject.put("dia", diaAgenda);
            agendaJsonObject.put("hora", dadoHora);
            agendaJsonObject.put("opcao", opcao);
            agendaJsonObject.toString();
            //Toast.makeText(getBaseContext(),"OBJETO: "+agendaJsonObject.toString(), Toast.LENGTH_LONG).show();
           // Log.d("TAG", "Isso é o que vai: " + agendaJsonObject.toString());

            final JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                    "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php", agendaJsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TAG", "Esse é o response: " + response.toString());
                            Toast.makeText(AgendamentoActivity.this, "Agendamento realizado com sucesso!", Toast.LENGTH_LONG).show();
                            Intent intentConsulta = new Intent(AgendamentoActivity.this, MenuActivity.class);
                            intentConsulta.putExtra("idusuario", idusuario);
                            startActivity(intentConsulta);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgendamentoActivity.this, "Tente novamente"+error, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "ERRO!!: " + error);
                }
            });

            mVolleyRequest.add(json);
        }else{
            Toast.makeText(getBaseContext(),"Desculpe, você precisa estar logado para fazer agendamento", Toast.LENGTH_LONG).show();
            Log.d("TAG", "Desculpe, você precisa estar logado para fazer agendamento");
        }
    }


    private void getAgenda(String idprofissional) {
        //Toast.makeText(getBaseContext(), "Chamou getAgenda", Toast.LENGTH_LONG).show();
        int opcao = 2; //mostrar agenda
        final ArrayList<AgendamentoPojo> agenda = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<AgendamentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php?" +
                "idpro="+idprofissional+"&opcao="+opcao, AgendamentoPojo[].class, null, new Response.Listener<AgendamentoPojo[]>() {
            @Override
            public void onResponse(AgendamentoPojo[] response) {
                //Log.d("TAG", "Esse é o response: " + response);
                for (int i = 0; i < response.length; i++) {
                    AgendamentoPojo ag = new AgendamentoPojo();
                    ag.dia = response[i].dia;
                    ag.hora = response[i].hora;
                    agenda.add(ag);
                }

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
