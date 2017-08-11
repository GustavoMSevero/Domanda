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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class EstabelecimentoActivity extends AppCompatActivity {

    private ListView listEstabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);
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

        listEstabelecimentos = ((ListView)findViewById(R.id.lvEstabelecimentos));

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
