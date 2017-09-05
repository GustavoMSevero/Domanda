package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button buscar;
    private int idusuario;
    private String nome;
    private String sobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            idusuario = extra.getInt("idusuario");
            nome = extra.getString("nome");
            sobrenome = extra.getString("sobrenome");
        }
        Toast.makeText(this, "id usuario "+idusuario, Toast.LENGTH_SHORT).show();

        buscar = ((Button)findViewById(R.id.btnBuscar));
    }

    public void buscar(View view){
        Intent buscarIntent = new Intent(MenuActivity.this, BuscarActivity.class);
        buscarIntent.putExtra("idusuario", idusuario);
        buscarIntent.putExtra("nome", nome);
        buscarIntent.putExtra("sobrenome", sobrenome);

        startActivity(buscarIntent);
    }


    public void sobre(View view) {
        Intent intentSobre = new Intent(MenuActivity.this, SobreActivity.class);
        startActivity(intentSobre);
    }

    public void consultarAgenda(View view) {
        Intent intentConsultarAgenda = new Intent(MenuActivity.this, ConsultarActivity.class);
        startActivity(intentConsultarAgenda);
    }
}
