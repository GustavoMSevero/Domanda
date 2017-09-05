package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.gustavo.domanda.R.id.edtSenha;

public class MainActivity extends AppCompatActivity {

    private TextView email;
    private TextView senha;
    private Button logar;
    private RequestQueue mVolleyRequest;
    private TextView msgErro;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logar = (Button)findViewById(R.id.btnEntrar);

        mVolleyRequest = Volley.newRequestQueue(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void entrar(View view) throws JSONException {

        email = (TextView)findViewById(R.id.edtEmail);
        senha = (TextView)findViewById(edtSenha);

        JSONObject dadosJsonObject = new JSONObject();
        dadosJsonObject.put("email", email.getText().toString());
        dadosJsonObject.put("senha", senha.getText().toString());
        dadosJsonObject.toString();

        //Toast.makeText(this, dadosJsonObject.toString(), Toast.LENGTH_SHORT).show();
        //Log.d("Dados", dadosJsonObject.toString());

        final JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/pegaUsuario.php", dadosJsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int idusuario = 0;
                        String nomeUsuario = null;
                        String sobrenomeUsuario = null;

                        try {
                            String status = response.getString("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == null) {
                            try {
                                msgErro = (TextView) findViewById(R.id.tvErro);
                                msgErro.setText(response.getString("msg"));
                                return;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        try {
                            idusuario = response.getInt("idusuario");
                            nomeUsuario = response.getString("nome");
                            sobrenomeUsuario = response.getString("sobrenome");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intentEntrar = new Intent(MainActivity.this, MenuActivity.class);
                        intentEntrar.putExtra("idusuario", idusuario);
                        intentEntrar.putExtra("nome", nomeUsuario);
                        intentEntrar.putExtra("sobrenome", sobrenomeUsuario);

                        startActivity(intentEntrar);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, "Tente novamente"+error, Toast.LENGTH_SHORT).show();
                //Log.d("TAG", "ERRO!!: " + error);

            }
        }
        );

        mVolleyRequest.add(json);

    }

    public void cadastrar(View view) {
        //Toast.makeText(this, "Cadastrar", Toast.LENGTH_SHORT).show();
        Intent intentCadastro = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intentCadastro);
    }

    public void experimentar(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
