package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class libroEncontrado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_encontrado);
        Bundle libroRecibido;
        libroRecibido=this .getIntent().getExtras();
        String tituloLibro=libroRecibido.getString("Libro");
        Log.d("LibroRecibido",tituloLibro);
    }
}