package com.example.gustavo.domanda;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class DiaActivity extends AppCompatActivity {

    public String d;
    public int dia;
    public int mes;
    public int ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void confirmar(View view) {
        DatePicker date = ((DatePicker) findViewById(R.id.dpData));
        int dia = date.getDayOfMonth();
        int mes = date.getMonth() + 1;
        int ano = date.getYear();
        String d = dia+"/"+mes+"/"+ano;
        Toast.makeText(this,d, Toast.LENGTH_SHORT).show();
    }
}
