package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibrosBusquedaCategoria extends AppCompatActivity {
    Button Tema1,Tema2,Tema3,Tema4,Tema5,Tema6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_busqueda_categoria);
        Tema1=findViewById(R.id.tema1);
        Tema2=findViewById(R.id.tema2);
        Tema3=findViewById(R.id.tema3);
        Tema4=findViewById(R.id.tema4);
        Tema5=findViewById(R.id.tema5);
        Tema6=findViewById(R.id.tema6);



    }
    public void onClick(View v){
        Bundle Paquete;
        Paquete=new Bundle();
        String Categoria;
        if (v.getId() == R.id.tema1){
            Categoria=Tema1.getText().toString();
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.tema2){
            Categoria=Tema2.getText().toString();
            Log.d("tema", "onClick: tema"+Categoria);
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            intent.putExtras(Paquete);
            startActivity(intent);

        }
        if (v.getId() == R.id.tema3){
            Categoria=Tema3.getText().toString();
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            intent.putExtras(Paquete);
            startActivity(intent);

        }
        if (v.getId() == R.id.tema4){
            Categoria=Tema4.getText().toString();
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            intent.putExtras(Paquete);
            startActivity(intent);
        }
        if (v.getId() == R.id.tema5){
            Categoria=Tema5.getText().toString();
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            intent.putExtras(Paquete);
            startActivity(intent);
        }
        if (v.getId() == R.id.tema6){
            Categoria=Tema6.getText().toString();
            Paquete.putString("Categoria",Categoria);
            Intent intent = new Intent(this, CategoriaElegida.class);
            intent.putExtras(Paquete);
            startActivity(intent);

        }



    }
}

