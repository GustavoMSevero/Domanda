package com.example.gustavo.domanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UnidadeActivity extends AppCompatActivity {

    private EstabelecimentoPojo e;
    private ListView lvUni;

    private int idusuario;
    private String nome;
    private String sobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
        }
        Toast.makeText(this, "id usuario "+idusuario, Toast.LENGTH_SHORT).show();

        e = (EstabelecimentoPojo) getIntent().getSerializableExtra("estab");
        //Log.d("regys",e.toString());
        //Log.d("ID ESTAB: ",e.getIdestabelecimento());

        getUnidades(e.getIdestabelecimento());

        lvUni = (ListView)findViewById(R.id.lvUnidades);

        lvUni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UnidadePojo u = (UnidadePojo) lvUni.getItemAtPosition(position);
                Intent it = new Intent(UnidadeActivity.this, ProfissionalActivity.class);
                it.putExtra("unid", u);
                it.putExtra("idusuario", idusuario);
                it.putExtra("nome", nome);
                it.putExtra("sobrenome", sobrenome);
                startActivity(it);
            }
        });
    }

    public void getUnidades(final String idestabelecimento){
        final ArrayList<UnidadePojo> uni = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<UnidadePojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqUnitJson.php?" +
                "idestabelecimento=" + idestabelecimento, UnidadePojo[].class, null, new Response.Listener<UnidadePojo[]>() {
            @Override
            public void onResponse(UnidadePojo[] response) {

                for (int i = 0; i < response.length; i++) {
                    UnidadePojo un = new UnidadePojo();
                    un.idunidade = response[i].idunidade;
                    un.unidade = response[i].unidade;
                    un.endereco = response[i].endereco;
                    un.numero = response[i].numero;
                    uni.add(un);
                    Log.d("TAG","dentro for unidade: "+un.toString());
                }
                Log.d("TAG","array apos for: "+uni.toString());

                ArrayAdapter<UnidadePojo> adapter = new ArrayAdapter<UnidadePojo>(UnidadeActivity.this, android.R.layout.simple_list_item_1, uni);
                lvUni = (ListView) findViewById(R.id.lvUnidades);
                lvUni.setAdapter(adapter);
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
