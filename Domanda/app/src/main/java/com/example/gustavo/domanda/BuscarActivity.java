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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity {

    private EditText campoBusca;
    private Button btnBuscar;
    private ListView lvEstabelecimentos;
    private RequestQueue mVolleyQueue;

    private EstabelecimentoAdapter adapter;
    private ArrayList<EstabelecimentoPojo> estabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoBusca = (EditText) findViewById(R.id.edtCampoBusca);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        lvEstabelecimentos = (ListView) findViewById(R.id.lvEstabelecimentos);

        mVolleyQueue = Volley.newRequestQueue(this);

        estabelecimentos = new ArrayList<>();
        adapter = new EstabelecimentoAdapter(BuscarActivity.this, estabelecimentos);
        lvEstabelecimentos.setAdapter(adapter);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurar(v);
            }
        });

        lvEstabelecimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EstabelecimentoPojo e = (EstabelecimentoPojo) lvEstabelecimentos.getItemAtPosition(position);
                Intent it = new Intent(BuscarActivity.this, UnidadeActivity.class);
                it.putExtra("estab", e);
                startActivity(it);
            }
        });
    }

    public void procurar(View view) {
        //Toast.makeText(this,"Clicou", Toast.LENGTH_SHORT).show();
        String dadoBusca = campoBusca.getText().toString();
        //Toast.makeText(this,"entrou no procurar: "+dadoBusca, Toast.LENGTH_SHORT).show();

        final ArrayList<EstabelecimentoPojo> cia = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(this);
        GsonRequest<EstabelecimentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqCiaJson.php?" +
                "tipo="+dadoBusca, EstabelecimentoPojo[].class, null, new Response.Listener<EstabelecimentoPojo[]>() {
            @Override
            public void onResponse(EstabelecimentoPojo[] response) {
                for (int i = 0; i < response.length; i++){
                    EstabelecimentoPojo esta = new EstabelecimentoPojo();
                    esta.idestabelecimento = response[i].idestabelecimento;
                    esta.nome = response[i].nome;
                    esta.end = response[i].end;
                    esta.num = response[i].num;
                    esta.cid = response[i].cid;
                    esta.uf = response[i].uf;
                    cia.add(esta);
                }

                ArrayAdapter<EstabelecimentoPojo> adapter = new ArrayAdapter<EstabelecimentoPojo>(BuscarActivity.this, android.R.layout.simple_expandable_list_item_1, cia);
                lvEstabelecimentos = ((ListView)findViewById(R.id.lvEstabelecimentos));
                lvEstabelecimentos.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ESTABELECIMENTO", "asdfasdf"+error.getMessage());
            }
        });
        rq.add(request);

    }

}
