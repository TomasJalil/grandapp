package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.internal.v;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Movimiento extends AppCompatActivity {
    ArrayList<Tema> TemasLista;
    Button Tema1,Tema2,Tema3,Tema4,Tema5,Tema6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        Tema1=findViewById(R.id.tema1);
        Tema2=findViewById(R.id.tema2);
        Tema3=findViewById(R.id.tema3);
        Tema4=findViewById(R.id.tema4);
        Tema5=findViewById(R.id.tema5);
        Tema6=findViewById(R.id.tema6);
        TemasLista = new ArrayList<>();

        TareaAsincronicaTema LecturaTemas = new TareaAsincronicaTema();
        LecturaTemas.execute();



}

    private class TareaAsincronicaTema extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL miRuta = new URL("http://10.0.2.2:5000/API/temas" );
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();

                if (miConexion.getResponseCode() == 200) {

                    InputStream RespuestaTemas = miConexion.getInputStream();
                    InputStreamReader JSonCrudoTemas = new InputStreamReader(RespuestaTemas, "UTF-8");
                    ProcesarJsonLeidoTemas(JSonCrudoTemas);
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

    public void ProcesarJsonLeidoTemas(InputStreamReader JSonCrudoTemas){
        Log.d("Antes obj","No se convierte en json object");

        JsonParser parseadorDeJSon;
        parseadorDeJSon=new JsonParser();
        JsonArray objJsonCrudo;
        objJsonCrudo=parseadorDeJSon.parse(JSonCrudoTemas).getAsJsonArray();
        Integer IdTema;
        String Nombre;
        Log.d("Antes for","No entra al for");
        for (int i=0;i<=5;i++){
            JsonObject objUnaInstancia;
            objUnaInstancia=objJsonCrudo.get(i).getAsJsonObject();
            Nombre=objUnaInstancia.get("nombre").getAsString();
            IdTema=objUnaInstancia.get("idTema").getAsInt();

            Log.d("AccesoAPI",""+Nombre+IdTema);
            TemasLista.add(new Tema(IdTema,Nombre));
        }
        Tema1.setText(TemasLista.get(0).getNombre());
        Tema2.setText(TemasLista.get(1).getNombre());
        Tema3.setText(TemasLista.get(2).getNombre());
        Tema4.setText(TemasLista.get(3).getNombre());
        Tema5.setText(TemasLista.get(4).getNombre());
        Tema6.setText(TemasLista.get(5).getNombre());

    }
    public void onClick(View v){
        if (v.getId() == R.id.tema1){
            Integer x=0;
           Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);

        }
        if (v.getId() == R.id.tema2){
            Integer x=1;
            Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);

        }
        if (v.getId() == R.id.tema3){
            Integer x=2;
            Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);
        }
        if (v.getId() == R.id.tema4){
            Integer x=3;
            Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);
        }
        if (v.getId() == R.id.tema5){
            Integer x=4;
            Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);
        }
        if (v.getId() == R.id.tema6){
            Integer x=5;
            Intent intent = new Intent(Movimiento.this,MovimientoElegido.class);
            intent.putExtra("ArrayList", TemasLista.get(x));
            startActivity(intent);
        }






    }
    }

