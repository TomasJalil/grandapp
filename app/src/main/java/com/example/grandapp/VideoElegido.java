package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoElegido extends AppCompatActivity {

TextView TituloVideo;
Video ListaVideos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_elegido);
        TituloVideo=findViewById(R.id.TituloVideo);
        YouTubePlayerView youTubePlayerView=findViewById(R.id.youtube_player_view);


        Intent intent = getIntent();
        ListaVideos = intent.getParcelableExtra("ArrayList");
        TituloVideo.setText(ListaVideos.getTitulo());

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String VideoId=ListaVideos.IdVideo;
                youTubePlayer.loadVideo(VideoId,0);
            }
        });

        youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {

            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                int  newHeight=160;
                int newWidth=800;
                youTubePlayerView.requestLayout();
                youTubePlayerView.getLayoutParams().height=newHeight;
                youTubePlayerView.getLayoutParams().width=newWidth;


            }
        });



 }
}