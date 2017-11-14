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

public class ProfissionalActivity extends AppCompatActivity {

    private UnidadePojo u;
    private ListView listPro;

    private int idusuario;
    private String nome;
    private String sobrenome;
    private String unid;
    private String estabelecimento;
    private String profissional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
            unid = extra.getString("unidade");
            sobrenome = extra.getString("sobrenome");
            estabelecimento = extra.getString("estabelecimento");
        }
        //Toast.makeText(this, "dados no ProfissionalActivity "+extra.toString(), Toast.LENGTH_LONG).show();

        listPro =  ((ListView)findViewById(R.id.lvProfissional));

        u = (UnidadePojo) getIntent().getSerializableExtra("unid");
        Log.d("DADOS PA",extra.toString());
        //Log.d("ID UNID: ",u.getIdunidade());

        getProfs(u.getIdunidade());

        listPro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProfissionalPojo p = (ProfissionalPojo) listPro.getItemAtPosition(i);
                Intent it = new Intent(ProfissionalActivity.this, AgendamentoActivity.class);
                profissional = p.nome.toString();
                it.putExtra("pro", p);
                it.putExtra("idusuario", idusuario);
                it.putExtra("nome", nome);
                it.putExtra("sobrenome", sobrenome);
                it.putExtra("unid", unid);
                it.putExtra("estabelecimento", estabelecimento);
                startActivity(it);

            }
        });


    }

    public void getProfs(final String idunidade) {
        final ArrayList<ProfissionalPojo> alpro = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<ProfissionalPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqProJson.php?" +
                "idpro=" + idunidade, ProfissionalPojo[].class, null, new Response.Listener<ProfissionalPojo[]>() {
            @Override
            public void onResponse(ProfissionalPojo[] response) {

                Log.d("TAG","antes do for");
                for (int i = 0; i < response.length; i++) {
                    ProfissionalPojo pro = new ProfissionalPojo();
                    pro.idprofissional = response[i].idprofissional;
                    pro.nome = response[i].nome;
                    pro.funcao = response[i].funcao;
                    alpro.add(pro);
                }
                Log.d("TAG", "array apos for: " + alpro.toString());

                ArrayAdapter<ProfissionalPojo> adapter = new ArrayAdapter<ProfissionalPojo>(ProfissionalActivity.this, android.R.layout.simple_list_item_1, alpro);
                listPro = ((ListView)findViewById(R.id.lvProfissional));
                listPro.setAdapter(adapter);
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
