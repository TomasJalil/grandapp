package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LibrosMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_main);
    }
    public void buscarXNombre(View view){
        Intent intent = new Intent(this, LibrosBusquedaNombre.class);
        startActivity(intent);
    }
    public void buscarXCategoria(View view){
        Intent intent = new Intent(this, LibrosBusquedaCategoria.class);
        startActivity(intent);
    }
}