package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigator;

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
import java.util.List;

public class MovimientoElegido extends AppCompatActivity {
    TextView TextoTema;
    int idTema;
    String nombreTema;
    Tema ListaTemas;
    ArrayList<Tema> ListaVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        TextoTema=findViewById(R.id.textoTema);
        ListaVideos = new ArrayList<>();

        Intent intent= getIntent();
        ListaTemas=intent.getParcelableExtra("ArrayList");
        nombreTema=ListaTemas.getNombre();
        idTema=ListaTemas.getId();
        TextoTema.setText(nombreTema);
        TareaAsincronicaVideos MiTarea = new TareaAsincronicaVideos();
        MiTarea.execute();


    }

    private class TareaAsincronicaVideos extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL miRuta = new URL("http://10.0.2.2:5000/API/videos/"+idTema);
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
        Integer IdTema;
        String IdVideo;
        Log.d("Antes for", "No entra al for");
        for (int i = 0; i <= 4; i++) {
            JsonObject objUnaInstancia;
            objUnaInstancia = objJsonCrudo.get(i).getAsJsonObject();
            IdVideo = objUnaInstancia.get("idVideo").getAsString();
            IdTema = objUnaInstancia.get("temaVideos").getAsInt();
            Log.d("getVideos","El id del video es "+IdVideo+" del tema "+IdTema);
    //LLamar a una asynctask que llame a la api de youtube para que devuelva todos los atributos del objeto video, le tengo que pasar por parametro, el id del video
        }
    }

}