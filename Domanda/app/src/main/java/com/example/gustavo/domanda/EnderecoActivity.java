package com.example.gustavo.domanda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EnderecoActivity extends AppCompatActivity {

    private int idusuario;

    private RequestQueue mVolleyRequest;
    private TextView mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
        }

        checkAddress(idusuario);

        final EditText edtCep = ((EditText) findViewById(R.id.edtCep));

        mVolleyRequest = Volley.newRequestQueue(this);

        //Dispara quando campo perder o foco
        edtCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String cep = edtCep.getText().toString();
                    getCEP(cep);
                    //Toast.makeText(getBaseContext(), "CEP: "+cep, Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void checkAddress(int idusuario) {

    }

    private void getCEP(String cep) {
        RequestParams rp = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://viacep.com.br/ws/" + cep + "/json/", rp, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getBaseContext(), "Problema na conexao!"+statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                try {
                    JSONObject obj = new JSONObject(responseString);

                    String retorno = "";

                    if (!obj.has("erro")) {
                        retorno += "\n" + obj.getString("cep");
                        retorno += "\n" + obj.getString("logradouro");
                        retorno += "\n" + obj.getString("complemento");
                        retorno += "\n" + obj.getString("bairro");
                        retorno += "\n" + obj.getString("localidade");
                        retorno += "\n" + obj.getString("uf");

                        //Toast.makeText(getBaseContext(),"Dados retornados: "+retorno, Toast.LENGTH_LONG).show();

                        EditText edtCep = ((EditText) findViewById(R.id.edtCep));
                        EditText edtLogradouro = ((EditText) findViewById(R.id.edtLogradouro));
                        EditText edtBairro = ((EditText) findViewById(R.id.edtBairro));
                        EditText edtCidade = ((EditText) findViewById(R.id.edtLocalidade));
                        EditText edtUF = ((EditText) findViewById(R.id.edtUF));

                        edtLogradouro.setEnabled(false);
                        edtBairro.setEnabled(false);
                        edtCidade.setEnabled(false);
                        edtUF.setEnabled(false);

                        edtCep.setText(obj.optString("cep"), TextView.BufferType.EDITABLE);
                        edtLogradouro.setText(obj.optString("logradouro"), TextView.BufferType.EDITABLE);
                        edtBairro.setText(obj.optString("bairro"), TextView.BufferType.EDITABLE);
                        edtCidade.setText(obj.optString("localidade"), TextView.BufferType.EDITABLE);
                        edtUF.setText(obj.optString("uf"), TextView.BufferType.EDITABLE);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void salvarEndereco(View view) throws JSONException {
        //Toast.makeText(this, "id usuario "+idusuario, Toast.LENGTH_SHORT).show();
        EditText edtCep = ((EditText) findViewById(R.id.edtCep));
        EditText edtLogradouro = ((EditText) findViewById(R.id.edtLogradouro));
        EditText edtBairro = ((EditText) findViewById(R.id.edtBairro));
        EditText edtCidade = ((EditText) findViewById(R.id.edtLocalidade));
        EditText edtUF = ((EditText) findViewById(R.id.edtUF));

        mensagem = (TextView) findViewById(R.id.tvMensagem);

        JSONObject enderecoJsonObject = new JSONObject();
        enderecoJsonObject.put("cep", edtCep.getText().toString());
        enderecoJsonObject.put("logradouro", edtLogradouro.getText().toString());
        enderecoJsonObject.put("bairro", edtBairro.getText().toString());
        enderecoJsonObject.put("cidade", edtCidade.getText().toString());
        enderecoJsonObject.put("uf", edtUF.getText().toString());
        enderecoJsonObject.put("idusuario", idusuario);

        enderecoJsonObject.toString();
        //Log.d("TAG", "Isso é o que vai: " + enderecoJsonObject.toString());

        final JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/enderecoUsuario.php", enderecoJsonObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("TAG", "ISSO É O QUE VEM!!: " + response);
                //Toast.makeText(getBaseContext(),"Dados retornados: "+response, Toast.LENGTH_LONG).show();
                try {
                    mensagem.setText(response.getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EnderecoActivity.this, "Tente novamente"+error, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "ERRO!!: " + error);
            }
        });

        mVolleyRequest.add(json);

    }
}
