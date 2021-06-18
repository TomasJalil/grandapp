package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovimientoElegido extends AppCompatActivity {
    Spinner ListaVideos;
    ArrayList<Tema> VideosLista;
    ArrayAdapter<Tema> Adaptador;
    String TemaSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        Bundle PaqueteRecibido;
        PaqueteRecibido = this.getIntent().getExtras();
        String TemaSeleccionado = PaqueteRecibido.getString("Tema");
        Log.d("Bundle", "Me llego " + TemaSeleccionado);
        VideosLista = new ArrayList<>();
        Adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, VideosLista);
        Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Log.d("array", "" + Adaptador);
        ListaVideos = findViewById(R.id.lista);
        ListaVideos.setAdapter(Adaptador);

    }
    private class TareaAsincronicaVideo extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = ("localhost:5000/videos/:idTema");
                Log.d("url", "doInBackground:Url y NombreCta " + url);
                URL miRuta = new URL(url);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                if (miConexion.getResponseCode() == 200) {

                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader respLeidaNombre = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                    ProcesarJsonLeidoNombres(respLeidaNombre);
                }
            } catch (Exception error) {
                Log.d("PresionoBoton", "no se creo una URL" + error.getMessage());
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ListaVideos.setAdapter(Adaptador);
            Adaptador.notifyDataSetChanged();

            Log.d("postExecute", "onpost eexecute bien");
        }
    }

    public void cargarLista(JsonArray VideoLista) {
        String video;
        for (int x = 0; x < VideoLista.size(); x++) {
            JsonObject unaCategoria = VideoLista.get(x).getAsJsonObject();
            video = unaCategoria.get("video").getAsString();
          VideosLista.add(new Tema(idTema,tema));

        }

    }

    public void ProcesarJsonLeidoNombres(InputStreamReader respLeidaNombre) {
        Log.d("andaprocesar", "ProcesarJsonLeidoNombres: ");
        JsonParser ProcesadorDeJson;
        ProcesadorDeJson = new JsonParser();
        JsonObject objetoJson;
        JsonArray VideoLista;
        objetoJson = ProcesadorDeJson.parse(respLeidaNombre).getAsJsonObject();
        VideoLista= objetoJson.get("video").getAsJsonArray();
        cargarLista(VideoLista);
    }


    }

