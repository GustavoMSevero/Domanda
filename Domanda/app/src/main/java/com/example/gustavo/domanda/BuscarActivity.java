package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import static com.example.gustavo.domanda.R.id.alertTitle;
import static com.example.gustavo.domanda.R.id.parent;

public class BuscarActivity extends AppCompatActivity {

    //private EditText campoBusca;
    private Button btnBuscar;
    private ListView lvEstabelecimentos;
    private RequestQueue mVolleyQueue;
    private Spinner spinnerEstabelecimento;
    private String dadoBusca;

    private int idusuario;
    private String nome;
    private String sobrenome;
    private String estabelecimento;

    private EstabelecimentoAdapter adapter;
    private ArrayList<EstabelecimentoPojo> estabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
        }
        //Toast.makeText(this, "id usuario "+idusuario, Toast.LENGTH_SHORT).show();


        spinnerEstabelecimento = (Spinner) findViewById(R.id.spBusca);
        //campoBusca = (EditText) findViewById(R.id.edtCampoBusca);
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
                estabelecimento = e.nome;
                it.putExtra("estab", e);
                it.putExtra("estabelecimento", estabelecimento);
                it.putExtra("idusuario", idusuario);
                it.putExtra("nome", nome);
                it.putExtra("sobrenome", sobrenome);
                startActivity(it);
                //Log.d("TAG","estabelecimento: "+it.toString());
            }
        });


        // Colocado os valores no spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spBusca, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstabelecimento.setAdapter(adapter);

        spinnerEstabelecimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0){
                    //Toast.makeText(getBaseContext(), "Cabeleireiro", Toast.LENGTH_LONG).show();
                    String dadoBusca = "Cabeleireiro";
                }else if (i == 1){
                    //Toast.makeText(getBaseContext(), "Restaurante", Toast.LENGTH_LONG).show();
                    String dadoBusca = "Restaurante";
                }else if (i == 2){
                    //Toast.makeText(getBaseContext(), "Casa de festa", Toast.LENGTH_LONG).show();
                    String dadoBusca = "Casa de festa";
                }else if (i == 3){
                    //Toast.makeText(getBaseContext(), "Quadra esportiva", Toast.LENGTH_LONG).show();
                    String dadoBusca = "Quadra esportiva";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void procurar(View view) {

        String dadoBusca = spinnerEstabelecimento.getSelectedItem().toString();
        //Toast.makeText(this,"Valor selecionado é: "+dadoBusca, Toast.LENGTH_SHORT).show();

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
