package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Movimiento extends AppCompatActivity {
    Spinner ListaTemas;
    ArrayList<Tema> TemasLista;
    ArrayAdapter<Tema> Adaptador;
    String TemaSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        TemasLista = new ArrayList<>();
        Adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TemasLista);
        Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Log.d("array", "" + Adaptador);
        ListaTemas = findViewById(R.id.lista);
        ListaTemas.setAdapter(Adaptador);
        TareaAsincronicaTema LecturaTemas;
        LecturaTemas = new TareaAsincronicaTema();
        LecturaTemas.execute();

     ListaTemas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             TemaSeleccionado=parent.getItemAtPosition(position).toString();
             if (!TemaSeleccionado.equals("Elige tu tema")){
                 Log.d("Spinner","Seleccionado: "+TemaSeleccionado) ;

                 Bundle PaqueteDeDatos;
                 PaqueteDeDatos=new Bundle();
                 PaqueteDeDatos.putString("Tema",TemaSeleccionado);

                 Intent i = new Intent(Movimiento.this, MovimientoElegido.class);
                 i.putExtras(PaqueteDeDatos);
                     startActivity(i);


             }

         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });

}
    private class TareaAsincronicaTema extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = ("localhost:5000/temas");
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

            ListaTemas.setAdapter(Adaptador);
            Adaptador.notifyDataSetChanged();

            Log.d("postExecute", "onpost eexecute bien");
        }
    }

    public void cargarLista(JsonArray TemasListas) {
      String tema;
      Integer idTema;
        for (int x = 0; x < TemasListas.size(); x++) {
            JsonObject unaCategoria = TemasListas.get(x).getAsJsonObject();
            tema = unaCategoria.get("nombreTema").getAsString();
            idTema=unaCategoria.get("idTema").getAsInt();
            Log.d("jsonobjectlista2", "nombre categoria ");

            TemasLista.add(new Tema(idTema,tema));

        }

    }

    public void ProcesarJsonLeidoNombres(InputStreamReader respLeidaNombre) {
        Log.d("andaprocesar", "ProcesarJsonLeidoNombres: ");
        JsonParser ProcesadorDeJson;
        ProcesadorDeJson = new JsonParser();
        JsonObject objetoJson;
        JsonArray TemasListas;
        objetoJson = ProcesadorDeJson.parse(respLeidaNombre).getAsJsonObject();
        TemasListas= objetoJson.get("nombre").getAsJsonArray();
        cargarLista(TemasListas);
    }



}
