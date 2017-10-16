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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class EstabelecimentoActivity extends AppCompatActivity {

    private ListView listEstabelecimentos;

    private int idusuario;
    private String nome;
    private String sobrenome;
    private String estabelecimento;
    private String unidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
            //it.putExtra("estab", e);
            estabelecimento = extra.getString("estabelecimento");
        }
        //Toast.makeText(this, "id usuario "+idusuario+" estabelecimento "+estabelecimento, Toast.LENGTH_LONG).show();

        listEstabelecimentos = ((ListView)findViewById(R.id.lvEstabelecimentos));

        listEstabelecimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                EstabelecimentoPojo u = (EstabelecimentoPojo) listEstabelecimentos.getItemAtPosition(position);
                Intent it = new Intent(EstabelecimentoActivity.this, UnidadeActivity.class);
                unidade = u.nome;
                it.putExtra("unid", u);
                it.putExtra("unidade", unidade);
                it.putExtra("idusuario", idusuario);
                it.putExtra("nome", nome);
                it.putExtra("sobrenome", sobrenome);
                it.putExtra("estabelecimento", estabelecimento);
                startActivity(it);
            }
        });

    }

    public void getEstabelecimento(){
        final ArrayList<EstabelecimentoPojo> cia = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<EstabelecimentoPojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqCiaJson.php?" +
                "tipo=", EstabelecimentoPojo[].class, null, new Response.Listener<EstabelecimentoPojo[]>() {
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
                    //Log.d("TAG","estabelecimento: "+esta.toString());
                }

                ArrayAdapter<EstabelecimentoPojo> adapter = new ArrayAdapter<EstabelecimentoPojo>(EstabelecimentoActivity.this, android.R.layout.simple_expandable_list_item_1, cia);
                listEstabelecimentos = ((ListView)findViewById(R.id.lvEstabelecimentos));
                listEstabelecimentos.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ESTABELECIMENTO", "asdfasdf"+error.getMessage());
            }
        });
        queue.add(request);
    }

}
