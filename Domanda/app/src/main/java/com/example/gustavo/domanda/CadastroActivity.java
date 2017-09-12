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

import java.io.IOException;
import java.net.HttpURLConnection;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroActivity extends AppCompatActivity {

    private HttpURLConnection conn;
    private RequestQueue mVolleyRequest;

    private EditText edtSenha;
    private EditText edtEmail;
    private TextView mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //final EditText edtCep = ((EditText) findViewById(R.id.edtCep));

        mVolleyRequest = Volley.newRequestQueue(this);



    }

    public void btnCadastrar(View view) throws JSONException, IOException {

        EditText edtNome = ((EditText) findViewById(R.id.edtNome));
        EditText edtSobrenome = ((EditText) findViewById(R.id.edtSobrenome));
        EditText edtEmail = ((EditText) findViewById(R.id.edtEmail));
        EditText edtSenha = ((EditText) findViewById(R.id.edtSenha));

        mensagem = (TextView) findViewById(R.id.tvMensagem);

        JSONObject dadosJsonObject = new JSONObject();
        dadosJsonObject.put("nome", edtNome.getText().toString());
        dadosJsonObject.put("sobrenome", edtSobrenome.getText().toString());
        dadosJsonObject.put("email", edtEmail.getText().toString());
        dadosJsonObject.put("senha", edtSenha.getText().toString());

        dadosJsonObject.toString();

        //Toast.makeText(getBaseContext(),"Dados retornados: "+dadosJsonObject.toString(), Toast.LENGTH_LONG).show();
        //Log.d("TAG", "Esse é o response: " + dadosJsonObject.toString());

            final JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                    "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/usuarios.php", dadosJsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TAG", "ISSO É O QUE VEM!!: " + response);
                            Toast.makeText(getBaseContext(),"Dados retornados: "+response, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(CadastroActivity.this, "Tente novamente"+error, Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "ERRO!!: " + error);
                    }
                });

                mVolleyRequest.add(json);

        };

    }
