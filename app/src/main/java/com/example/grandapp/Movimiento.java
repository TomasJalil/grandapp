package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Movimiento extends AppCompatActivity {
    Spinner ListaTemas;
    ArrayList<String> TemasLista;
    ArrayList<Video> VideosLista;
    ArrayAdapter<CharSequence> Adaptador;
    ArrayAdapter<Tema> AdaptadorVideos;
    String TemaSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        TemasLista = new ArrayList();
        VideosLista = new ArrayList();
        Adaptador = ArrayAdapter.createFromResource(this,R.array.Temas, android.R.layout.simple_spinner_item);
        Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Log.d("array", "" + Adaptador);
        ListaTemas = findViewById(R.id.lista);
        ListaTemas.setAdapter(Adaptador);

     ListaTemas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             TemaSeleccionado=parent.getItemAtPosition(position).toString();
             if (!TemaSeleccionado.equals("Elige tu tema")){
                 Log.d("Spinner","Seleccionado: "+TemaSeleccionado) ;

             }

         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
}


}
