package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovimientoElegido extends AppCompatActivity {
    TextView TextoTema,titulo1,titulo2,titulo3,titulo4,titulo5;
    String VideoKey="AIzaSyCm-7uqrWacMSxZQXYMbMvwfzXFu7rToNI";
    int idTema;
    String nombreTema;
    Tema ListaTemas;
    ArrayList<Video> ListaVideos=new ArrayList<>();
    String[] IdVideos = new String[5];
    String[] UrlMiniaturas=new String[5];
    ImageButton Miniatura1,Miniatura2,Miniatura3,Miniatura4,Miniatura5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        TextoTema = findViewById(R.id.textoTema);
        titulo1=findViewById(R.id.Titulo1);
        titulo2=findViewById(R.id.Titulo2);
        titulo3=findViewById(R.id.Titulo3);
        titulo4=findViewById(R.id.Titulo4);
        titulo5=findViewById(R.id.Titulo5);
        Miniatura1=findViewById(R.id.Miniatura1);
        Miniatura2=findViewById(R.id.Miniatura2);
        Miniatura3=findViewById(R.id.Miniatura3);
        Miniatura4=findViewById(R.id.Miniatura4);
        Miniatura5=findViewById(R.id.Miniatura5);
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
            llamarSegundaAsync();

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
        Log.d("Entra procesarJson","Entro a procesar Json");

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

    public void llamarSegundaAsync() {
        TareaAsincronicaApiYoutube ApiYoutube = new TareaAsincronicaApiYoutube();
            ApiYoutube.execute();


    }


    private class TareaAsincronicaApiYoutube extends AsyncTask<Void, Void, ArrayList<Video>> {
        @Override
        protected void onPostExecute(ArrayList<Video> aVoid) {
            super.onPostExecute(aVoid);


            titulo1.setText(ListaVideos.get(0).getTitulo());
            titulo2.setText(ListaVideos.get(1).getTitulo());
            titulo3.setText(ListaVideos.get(2).getTitulo());
            titulo4.setText(ListaVideos.get(3).getTitulo());
            titulo5.setText(ListaVideos.get(4).getTitulo());
            Glide.with(getApplicationContext())
                    .load(ListaVideos.get(0).UrlMiniatura)
                    .into(Miniatura1);
            Glide.with(getApplicationContext())
                    .load(ListaVideos.get(1).UrlMiniatura)
                    .into(Miniatura2);
            Glide.with(getApplicationContext())
                    .load(ListaVideos.get(2).UrlMiniatura)
                    .into(Miniatura3);
            Glide.with(getApplicationContext())
                    .load(ListaVideos.get(3).UrlMiniatura)
                    .into(Miniatura4);
            Glide.with(getApplicationContext())
                    .load(ListaVideos.get(4).UrlMiniatura)
                    .into(Miniatura5);



        }

        @Override
        protected ArrayList<Video> doInBackground(Void... voids) {
            for (int i = 0; i <= 4; i++) {
                try {

                    URL miRuta2 = new URL("https://www.googleapis.com/youtube/v3/search?q=" + IdVideos[i] + "&key="+VideoKey+"&part=snippet&type=video");
                    HttpURLConnection miConexion = (HttpURLConnection) miRuta2.openConnection();
                    if (miConexion.getResponseCode() == 200) {

                        InputStream RespuestaTemas = miConexion.getInputStream();
                        InputStreamReader JSonCrudoVideos = new InputStreamReader(RespuestaTemas, "UTF-8");
                        ProcesarJsonLeidoVideosYoutube(JSonCrudoVideos,i);
                    } else {
                        Log.d("AccesoAPI", "Error en la conexion");
                    }
                    miConexion.disconnect();
                } catch (Exception error) {
                    Log.d("AccesoAPI", "Hubo error al conectarme: " + error.getMessage());
                }
            }
            return ListaVideos;


        }
    }
        public void ProcesarJsonLeidoVideosYoutube(InputStreamReader JSonCrudoVideos, Integer i) {
Log.d("Entra procesarJson","Entro a procesar Json");
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
            JsonObject objMiniatura=SnippetVideo.get("thumbnails").getAsJsonObject();
            JsonObject MiniaturaDefault=objMiniatura.get("high").getAsJsonObject();
            String UrlMiniatura=MiniaturaDefault.get("url").getAsString();
            UrlMiniaturas[i]=UrlMiniatura;
     ListaVideos.add(new Video(titulo,IdVideo,descripcion,nombreCanal,UrlMiniatura));
     Log.d("url",UrlMiniatura);
        }

        public void onClick1(View v){
                if (v.getId() == R.id.Miniatura1){
                    Integer x=0;
                    Intent intent = new Intent(MovimientoElegido.this,VideoElegido.class);
                    intent.putExtra("ArrayList", ListaVideos.get(x));
                    startActivity(intent);

                }
                if (v.getId() == R.id.Miniatura2){
                    Integer x=1;
                    Intent intent = new Intent(MovimientoElegido.this,VideoElegido.class);
                    intent.putExtra("ArrayList", ListaVideos.get(x));
                    startActivity(intent);

                }
                if (v.getId() == R.id.Miniatura3){
                    Integer x=2;
                    Intent intent = new Intent(MovimientoElegido.this,VideoElegido.class);
                    intent.putExtra("ArrayList", ListaVideos.get(x));
                    startActivity(intent);
                }
                if (v.getId() == R.id.Miniatura4){
                    Integer x=3;
                    Intent intent = new Intent(MovimientoElegido.this,VideoElegido.class);
                    intent.putExtra("ArrayList", ListaVideos.get(x));
                    startActivity(intent);
                }
                if (v.getId() == R.id.Miniatura5){
                    Integer x=4;
                    Intent intent = new Intent(MovimientoElegido.this,VideoElegido.class);
                    intent.putExtra("ArrayList", ListaVideos.get(x));
                    startActivity(intent);
                }
        }

}
