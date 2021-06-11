package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class Movimiento extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Spinner ListaTemas;
    ArrayList<String> TemasLista;
    ArrayAdapter<String> Adaptador;
    YouTubePlayerView YouTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        TemasLista=new ArrayList();
        Adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, TemasLista);
        Adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Log.d("array", "" + Adaptador);
        ListaTemas=(Spinner)findViewById(R.id.lista);
        ListaTemas.setAdapter(Adaptador);
        YouTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
