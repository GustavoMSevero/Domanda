package com.example.gustavo.domanda;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AtualizarDadosLoginActivity extends AppCompatActivity {

    private int idusuario;
    private EditText nome;
    private EditText sobrenome;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
           idusuario = extra.getInt("idusuario");
        }
        Toast.makeText(this, "id usuario "+idusuario, Toast.LENGTH_SHORT).show();

        getDados(idusuario);

    }

    private void getDados(int idusuario) {

        int opcao = 1; //mostrar dados usuário
        RequestParams rp = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/reqDataCliJson.php?opcao="+opcao+"&idusuario="+idusuario, rp, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getBaseContext(), "Problema na conexao!"+statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject obj = new JSONObject(responseString);
                    String retorno = "";
                    Toast.makeText(AtualizarDadosLoginActivity.this, "response: "+responseString, Toast.LENGTH_SHORT).show();
                    if (!obj.has("erro")) {
                        retorno += "\n" + obj.getString("idusuario");
                        retorno += "\n" + obj.getString("nome");
                        retorno += "\n" + obj.getString("sobrenome");
                        retorno += "\n" + obj.getString("email");
                        //Toast.makeText(getBaseContext(),"Dados retornados: "+retorno, Toast.LENGTH_LONG).show();

                        EditText nome = (EditText) findViewById(R.id.edtNome);
                        EditText sobrenome = (EditText) findViewById(R.id.edtSobrenome);
                        EditText email = (EditText) findViewById(R.id.edtEmail);

                        nome.setText(obj.optString("nome").toString(), TextView.BufferType.EDITABLE);
                        sobrenome.setText(obj.optString("sobrenome"), TextView.BufferType.EDITABLE);
                        email.setText(obj.optString("email"), TextView.BufferType.EDITABLE);
                    }

                } catch (JSONException e){

                }
            }

        });
    }

}
