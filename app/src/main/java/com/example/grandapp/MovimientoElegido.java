package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovimientoElegido extends AppCompatActivity {
    TextView TextoTema;
    int idTema;
    String nombreTema;
    Tema ListaTemas;
    ArrayList<Video> ListaVideos;
    String[] IdVideos = new String[5];
    Integer puntero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        TextoTema = findViewById(R.id.textoTema);

        Intent intent = getIntent();
        ListaTemas = intent.getParcelableExtra("ArrayList");
        nombreTema = ListaTemas.getNombre();
        idTema = ListaTemas.getId();
        TextoTema.setText(nombreTema);
        TareaAsincronicaVideos MiTarea = new TareaAsincronicaVideos();
        MiTarea.execute();

    }

    private class TareaAsincronicaVideos extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i <= 4; i++) {
                Log.d("getVideos", "El id del video es " + IdVideos[i] + " del tema ");
            }
            llamarSegundaAsync(IdVideos);

        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL miRuta = new URL("http://10.0.2.2:5000/API/videos/" + idTema);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();

                if (miConexion.getResponseCode() == 200) {

                    InputStream RespuestaTemas = miConexion.getInputStream();
                    InputStreamReader JSonCrudoVideos = new InputStreamReader(RespuestaTemas, "UTF-8");
                    ProcesarJsonLeidoVideos(JSonCrudoVideos);
                } else {
                    Log.d("AccesoAPI", "Error en la conexion");
                }
                miConexion.disconnect();
            } catch (Exception error) {
                Log.d("AccesoAPI", "Hubo error al conectarme: " + error.getMessage());
            }
            return null;
        }

    }

    public void ProcesarJsonLeidoVideos(InputStreamReader JSonCrudoVideos) {
        Log.d("Antes obj", "No se convierte en json object");

        JsonParser parseadorDeJSon;
        parseadorDeJSon = new JsonParser();
        JsonArray objJsonCrudo;
        objJsonCrudo = parseadorDeJSon.parse(JSonCrudoVideos).getAsJsonArray();
        String IdVideo;
        Log.d("Antes for", "No entra al for");
        for (int i = 0; i <= 4; i++) {
            JsonObject objUnaInstancia;
            objUnaInstancia = objJsonCrudo.get(i).getAsJsonObject();
            IdVideo = objUnaInstancia.get("idVideo").getAsString();
            IdVideos[i] = IdVideo;

        }
    }

    public void llamarSegundaAsync(String[] IdVideos) {
        TareaAsincronicaApiYoutube ApiYoutube = new TareaAsincronicaApiYoutube();
            ApiYoutube.execute();


    }


    private class TareaAsincronicaApiYoutube extends AsyncTask<Integer, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }

        @Override
        protected Void doInBackground(Integer... l) {
            for (int i = 0; i <= 4; i++) {
                try {

                    Log.d("quepasa", "Funciona" + IdVideos[i]);

                    URL miRuta2 = new URL("https://www.googleapis.com/youtube/v3/search?q=" + IdVideos[i] + "&key=AIzaSyDFQ7exRc_5wsPQOQ5cqWVx0OklyC6eaDg&part=snippet&type=video");
                    HttpURLConnection miConexion = (HttpURLConnection) miRuta2.openConnection();
                    if (miConexion.getResponseCode() == 200) {

                        InputStream RespuestaTemas = miConexion.getInputStream();
                        InputStreamReader JSonCrudoVideos = new InputStreamReader(RespuestaTemas, "UTF-8");
                        ProcesarJsonLeidoVideosYoutube(JSonCrudoVideos);
                    } else {
                        Log.d("AccesoAPI", "Error en la conexion");
                    }
                    miConexion.disconnect();
                } catch (Exception error) {
                    Log.d("AccesoAPI", "Hubo error al conectarme: " + error.getMessage());
                }
            }
            return null;


        }

        public Void ProcesarJsonLeidoVideosYoutube(InputStreamReader JSonCrudoVideos) {
            Log.d("Antes obj", "No se convierte en json object");

            JsonParser parseadorDeJSon;
            parseadorDeJSon = new JsonParser();
            JsonObject objJsonCrudo;
            objJsonCrudo = parseadorDeJSon.parse(JSonCrudoVideos).getAsJsonObject();
            JsonArray arrItems = objJsonCrudo.get("items").getAsJsonArray();
            JsonObject unItem = arrItems.get(0).getAsJsonObject();
            JsonObject unId=unItem.get("id").getAsJsonObject();
            String IdVideo=unId.get("videoId").getAsString();
            JsonObject SnippetVideo=unItem.get("snippet").getAsJsonObject();
            String titulo=SnippetVideo.get("title").getAsString();
            String descripcion=SnippetVideo.get("description").getAsString();
            String nombreCanal=SnippetVideo.get("channelTitle").getAsString();
Log.d("hola","funciona= "+titulo+descripcion+nombreCanal+IdVideo);
// ListaVideos.add(new Video(titulo,IdVideo,descripcion,nombreCanal));
            return null;
        }


    }
}
