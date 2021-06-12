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
    ArrayAdapter<String> Adaptador;
    ArrayAdapter<Tema> AdaptadorVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        TemasLista = new ArrayList();
        VideosLista = new ArrayList();
        Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TemasLista);
        Adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Log.d("array", "" + Adaptador);
        ListaTemas = findViewById(R.id.lista);
        ListaTemas.setAdapter(Adaptador);
        ListaTemas.setOnItemClickListener(escuchadorParaListViewNombre);
        TemasLista.add("Yoga");
        TemasLista.add("Meditacion");
        TemasLista.add("Zumba");
        TemasLista.add("Funcional");
        TemasLista.add("Stretching");
        TemasLista.add("Baile");
}
    AdapterView.OnItemClickListener escuchadorParaListViewNombre=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicionSeleccionada, long l) {
            posicionSeleccionada=ListaTemas.getSelectedItemPosition();

            String TextoSeleccionado;
            TextoSeleccionado= ListaTemas.getItemAtPosition(posicionSeleccionada).toString();
            Log.d("Posicion",TextoSeleccionado);
        }
    };

}
