package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LibrosBusquedaNombre extends AppCompatActivity {
EditText titulo;
String title;
Button t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_busqueda_nombre);
       titulo=findViewById(R.id.editText);
       t=findViewById(R.id.button);
    }

public void nombreLibro(View v){
        Bundle Paquete;
        Paquete=new Bundle();
        title=titulo.getText().toString();
        Paquete.putString("titulo",title);
        Intent intent = new Intent(this, NombreElegido.class);
        startActivity(intent);
        }

    }

