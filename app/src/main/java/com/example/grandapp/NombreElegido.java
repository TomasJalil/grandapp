package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NombreElegido extends AppCompatActivity {
    String title;
    ArrayList<Libro> ListaLibros = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_elegido);
        Bundle paquete;
        paquete = this.getIntent().getExtras();
         title = paquete.getString("Titulo");
        TareaAsincronicaNombreLibro mile= new TareaAsincronicaNombreLibro();
        mile.execute();
        Log.d("categoria", "onCreate: categ" + title);
    }


    private class TareaAsincronicaNombreLibro extends AsyncTask<Void, Void, ArrayList<Libro>> {
        @Override
        protected void onPostExecute(ArrayList<Libro> aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected ArrayList<Libro> doInBackground(Void... voids) {
            try {

                URL miRuta2 = new URL("https://www.etnassoft.com/api/v1/get/?book_title=" + title + "&?results_range=0,10");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta2.openConnection();
                if (miConexion.getResponseCode() == 200) {

                    InputStream RespuestaTemas = miConexion.getInputStream();
                    InputStreamReader JSonCrudos = new InputStreamReader(RespuestaTemas, "UTF-8");
                    ProcesarJsonLeidoLibro(JSonCrudos);
                } else {
                    Log.d("AccesoAPI", "Error en la conexion");
                }
                miConexion.disconnect();
            } catch (Exception error) {
                Log.d("AccesoAPI", "Hubo error al conectarme: " + error.getMessage());
            }
            return ListaLibros;
        }


        public void ProcesarJsonLeidoLibro(InputStreamReader JSonCrudos) {
            Log.d("Entra procesarJson", "Entro a procesar Json");
            JsonParser parseadorDeJSon;
            parseadorDeJSon = new JsonParser();
            JsonArray objJsonCrudo;
            objJsonCrudo = parseadorDeJSon.parse(JSonCrudos).getAsJsonArray();
            for (int i = 0; i <= objJsonCrudo.size(); i++) {
                JsonObject unLibro = objJsonCrudo.get(i).getAsJsonObject();
                String title = unLibro.get("title").getAsString();
                String UrlMiniatura = unLibro.get("thumbnail").getAsString();
                String author = unLibro.get("author").getAsString();
                String content = unLibro.get("content").getAsString();
                ListaLibros.add(new Libro(title, author, content, UrlMiniatura));
            }
        }
    }
}