package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class libroInicio extends AppCompatActivity {
Button btnLibro;
EditText EditTextLibroIngresado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        btnLibro=findViewById(R.id.btnBusquedaLibro);
        EditTextLibroIngresado=findViewById(R.id.tituloLibroIngresado);


    }
    public void buscarLibro(View view){
        String LibroIngresado=EditTextLibroIngresado.getText().toString().trim();
        Bundle paqueteLibro;
        paqueteLibro=new Bundle();
        paqueteLibro.putString("Libro",LibroIngresado);

        Intent intent;
        intent=new Intent(this,libroEncontrado.class);
        intent.putExtras(paqueteLibro);
        startActivity(intent);
    }
}