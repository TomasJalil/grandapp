package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    String claveYoutube="AIzaSyCj4yWmuJsWcLWTDzOgh0V79qnGtxilTSc";
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
       YouTubePlayerView.initialize(claveYoutube,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fueRestaurado) {
        if(!fueRestaurado){
            youTubePlayer.cueVideo("a01D1PzTVFc"); //https://www.youtube.com/watch?v=a01D1PzTVFc
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else{
            String Error="Error al inicializar youtube" + youTubeInitializationResult.toString();
            Toast.makeText(getApplication(), Error, Toast.LENGTH_SHORT).show();
        }
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==1){
            getYoutubePlayerProvider().initialize(claveYoutube,this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return YouTubePlayerView;
    }
}
