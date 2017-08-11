package com.example.gustavo.domanda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        e = (EstabelecimentoPojo) getIntent().getSerializableExtra("estab");
        Log.d("regys",e.toString());
        Log.d("ID ESTAB: ",e.getIdestabelecimento());

        getUnidades(e.getIdestabelecimento());

        lvUni = (ListView)findViewById(R.id.lvUnidades);
    }

    public void getUnidades(final String idestabelecimento){
        final ArrayList<UnidadePojo> uni = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        GsonRequest<UnidadePojo[]> request = new GsonRequest<>("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqUnitJson.php?" +
                "idestabelecimento=" + e.idestabelecimento, UnidadePojo[].class, null, new Response.Listener<UnidadePojo[]>() {
            @Override
            public void onResponse(UnidadePojo[] response) {

                for (int i = 0; i < response.length; i++) {
                    UnidadePojo un = new UnidadePojo();
                    un.idunidade = response[i].idunidade;
                    un.unidade = response[i].unidade;
                    un.endereco = response[i].endereco;
                    un.numero = response[i].numero;
                    uni.add(un);
                }

                ArrayAdapter<UnidadePojo> adapter = new ArrayAdapter<UnidadePojo>(UnidadeActivity.this, android.R.layout.simple_expandable_list_item_1, uni);
                lvUni = (ListView) findViewById(R.id.lvUnidades);
                lvUni.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        });
        queue.add(request);

//        String url = "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqUnitJson.php?";
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<UnidadePojo>() {
//
//            @Override
//            public void onResponse(UnidadePojo[] response) {
//                //Log.d("Response EU ACHO: ", response);
//                for (int i = 0; i < response.length; i++){
//                    UnidadePojo un = new UnidadePojo();
//                    un.unidade = response[i].unidade;
//                    un.endereco = response[i].endereco;
//                    un.numero = response[i].numero;
//                    uni.add(un);
//                }
//                ArrayAdapter<UnidadePojo> adapter = new ArrayAdapter<UnidadePojo>(UnidadeActivity.this, android.R.layout.simple_expandable_list_item_1, uni);
//                lvUni = (ListView) findViewById(R.id.lvUnidades);
//                lvUni.setAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Error.Response", String.valueOf(error));
//            }
//        }
//        ){
//            protected Map<String, String> getParams(){
//                Map<String, String>  params = new HashMap<String, String>();
//
//                params.put("idestabelecimento", idestabelecimento);
//
//                return params;
//            }
//        };
//        queue.add(postRequest);
    }
}
