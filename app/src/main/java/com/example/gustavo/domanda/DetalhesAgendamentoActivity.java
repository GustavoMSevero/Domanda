package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalhesAgendamentoActivity extends AppCompatActivity {

    private Button botaoCancelar;
    private String idagendamento;
    private RequestQueue mVolleyRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_agendamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        mVolleyRequest = Volley.newRequestQueue(this);

        if(extra != null){
            idagendamento = extra.getString("idagendamento");
        }

        botaoCancelar = (Button) findViewById(R.id.btnCancelarAgendamento);

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("TAG","Apagar agendamento "+idagendamento);

                JSONObject dadoJson = new JSONObject();
                try {
                    dadoJson.put("idagendamento",idagendamento);
                    dadoJson.put("opcao",4);
                    dadoJson.toString();
                    //Log.d("Dado que vai", dadoJson.toString());

                    JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                            "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqScheduleProJson.php", dadoJson, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Dado que vem ", response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    mVolleyRequest.add(json);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void voltar(View view) {
        Intent intentVoltar = new Intent(DetalhesAgendamentoActivity.this, ConsultarActivity.class);
        startActivity(intentVoltar);
    }
}
