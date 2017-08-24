package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView email;
    private TextView senha;
    private Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        email = (TextView)findViewById(R.id.edtEmail);
//        senha = (TextView)findViewById(R.id.edtSenha);
        logar = (Button)findViewById(R.id.btnEntrar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void entrar(View view) {
        email = (TextView)findViewById(R.id.edtEmail);
        senha = (TextView)findViewById(R.id.edtSenha);

        String dadoEmail = email.getText().toString();
        String dadoSenha = senha.getText().toString();
        //String mens = "Email "+dadoEmail+ " e Senha "+dadoSenha ;
        //Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();

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
