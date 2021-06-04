package com.example.grandapp;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;



public class TemasController {

    public void async(View view){
        TareaAsincronicaTemas LecturaTemas;
        LecturaTemas=new TareaAsincronicaTemas();
        LecturaTemas.execute();
    }


    ArrayList<Tema> listaTemas;

    private class TareaAsincronicaTemas extends AsyncTask<Void, Void, ArrayList<Tema>> {


        @Override
        protected ArrayList<Tema> doInBackground(Void... voids) {

            try {
                String url = ("localhost:5000/API/temas");
                Log.d("url", "doInBackground:Url y NombreCta " + url);
                URL miRuta = new URL(url);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                if (miConexion.getResponseCode() == 200) {
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader respLeidaTemas = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                    listaTemas = ProcesarJsonLeido(respLeidaTemas);
                }

            } catch (Exception error) {
                Log.d("PresionoBoton", "no se creo una URL" + error.getMessage());
            }

            return listaTemas;
        }


        @Override
        protected void onPostExecute(ArrayList<Tema> lista) {
            super.onPostExecute(lista);
            Log.d("postExecute", "onpost eexecute bien");
        }
    }

    public ArrayList<Tema> ProcesarJsonLeido(InputStreamReader respLeidaNombre) {
        JsonArray ArrayTema;
        Log.d("andaprocesar", "ProcesarJsonLeidoNombres: ");
        JsonParser ProcesadorDeJson;
        ProcesadorDeJson = new JsonParser();
        JsonObject objetoJson;
        objetoJson = ProcesadorDeJson.parse(respLeidaNombre).getAsJsonObject();
        ArrayTema = objetoJson.get("instancias").getAsJsonArray();
        listaTemas = CargarLista(ArrayTema);
        return listaTemas;

    }

    public ArrayList<Tema> CargarLista(JsonArray vector) {
        String NombreTema;
        Tema temaobjeto = new Tema();
        for (int x = 0; x < vector.size(); x++) {
            JsonObject objetoJson = vector.get(x).getAsJsonObject();
            NombreTema = objetoJson.get("nombre").getAsString();
            NombreTema = temaobjeto.getNombre();
            temaobjeto.setNombre(NombreTema);
            listaTemas.add(temaobjeto);

        }
        return listaTemas;


    }


}
