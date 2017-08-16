package com.example.gustavo.domanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buscar = ((Button)findViewById(R.id.btnBuscar));
    }

    public void buscar(View view){
        Intent buscarIntent = new Intent(MenuActivity.this, BuscarActivity.class);
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
