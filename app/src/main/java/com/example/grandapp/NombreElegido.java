package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.CaseMap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NombreElegido extends AppCompatActivity {
    String[] vectitle,vecauthor,veccontent,vecurl;
    ArrayList<Libro> ListaLibros = new ArrayList<>();
    String title;
    TextView autor,titulo,content;
    TextView tituloBusqueda;
    ImageView imagen;
    private RecyclerView recyclerView;
    private RecyclerViewAdaptador recyclerViewAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // recyclerView=findViewById(R.id.recyclerViewLibro);//min 5:5
    recyclerView=(RecyclerView)findViewById(R.id.recyclerViewLibro);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewAdaptador=new RecyclerViewAdaptador(ListaLibros);
    recyclerView.setAdapter(recyclerViewAdaptador);
        setContentView(R.layout.activity_nombre_elegido);
        Bundle paquete;
        paquete = this.getIntent().getExtras();
         title = paquete.getString("Titulo");


        TareaAsincronicaNombreLibro mile= new TareaAsincronicaNombreLibro();
        mile.execute();
        Log.d("categoria", "onCreate: categ" + title);
        autor=findViewById(R.id.autor);
        titulo=findViewById(R.id.title);
        content=findViewById(R.id.content);
        imagen=findViewById(R.id.imgLibro);
        tituloBusqueda=findViewById(R.id.busqueda);
        tituloBusqueda.setText(title);
    }


    private class TareaAsincronicaNombreLibro extends AsyncTask<Void, Void, ArrayList<Libro>> {
        @Override
        protected void onPostExecute(ArrayList<Libro> aVoid) {
            super.onPostExecute(aVoid);

            TareaAsincronicaFoto mille= new TareaAsincronicaFoto();
            mille.execute(vecurl);
        }

        @Override
        protected ArrayList<Libro> doInBackground(Void... voids) {
            try {

                URL miRuta2 = new URL("https://www.etnassoft.com/api/v1/get/?book_title=" + title + "&?results_range=0,10");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta2.openConnection();
                if (miConexion.getResponseCode() == 200) {

                    InputStream RespuestaTemas = miConexion.getInputStream();
                    InputStreamReader JSonCrudos = new InputStreamReader(RespuestaTemas, "UTF-8");
                    ProcesarJsonLeidoLibro(JSonCrudos);
                } else {
                    Log.d("AccesoAPI", "Error en la conexion");
                }
                miConexion.disconnect();
            } catch (Exception error) {
                Log.d("AccesoAPI", "Hubo error al conectarme: " + error.getMessage());
            }
            return ListaLibros;
        }


        public void ProcesarJsonLeidoLibro(InputStreamReader JSonCrudos) {
            Log.d("Entra procesarJson", "Entro a procesar Json");
            JsonParser parseadorDeJSon;
            parseadorDeJSon = new JsonParser();
            JsonArray objJsonCrudo;
            objJsonCrudo = parseadorDeJSon.parse(JSonCrudos).getAsJsonArray();
            vectitle=new String[objJsonCrudo.size()];
            vecauthor=new String[objJsonCrudo.size()];
            veccontent=new String[objJsonCrudo.size()];
            vecurl=new String[objJsonCrudo.size()];
            for (int i = 0; i <= objJsonCrudo.size(); i++) {
                JsonObject unLibro = objJsonCrudo.get(i).getAsJsonObject();
                vectitle[i] = unLibro.get("title").getAsString();
                vecurl[i] = unLibro.get("thumbnail").getAsString();
                vecauthor[i] = unLibro.get("author").getAsString();
                veccontent[i] = unLibro.get("content").getAsString();

            }
        }
    }
    private class TareaAsincronicaFoto extends AsyncTask<String[], Void, Bitmap[]> {
        @Override
        protected void onPostExecute(Bitmap[] imagenConvertida) {

            for (int x=0;x<vecurl.length;x++) {
                ListaLibros.add(new Libro(vectitle[x], vecauthor[x], veccontent[x],imagenConvertida[x]));
                titulo.setText(ListaLibros.get(x).getTitulo());
                autor.setText(ListaLibros.get(x).getAutor());
                content.setText(ListaLibros.get(x).getDescripcion());
                imagen.setImageBitmap(imagenConvertida[x]);
            }
        }

        @Override
        protected Bitmap[] doInBackground(String[]... vecurl) {
            Bitmap[] ImagenConvertida = new Bitmap[vecurl.length];
            for (int i = 0; i <= vecurl.length-1; i++) {
                ImagenConvertida[i] = null;
                Bitmap imagenConvertida = ImagenConvertida[i];
                try {
                    URL miRuta;

                    miRuta = new URL(""+vecurl[0][i]);
                    Log.d("Descarga", "Mi ruta obtenida: " + miRuta);
                    HttpURLConnection conexionUrl;
                    conexionUrl = (HttpURLConnection) miRuta.openConnection();
                    if (conexionUrl.getResponseCode() == 200) {
                        InputStream cuerpoDatos = conexionUrl.getInputStream();
                        BufferedInputStream lectorEntrada = new BufferedInputStream(cuerpoDatos);
                        ImagenConvertida[i] = BitmapFactory.decodeStream(lectorEntrada);
                        conexionUrl.disconnect();
                    }
                } catch (Exception err) {
                    Log.d("Descarga", "Error: " + err);
                }


            }
            return ImagenConvertida;
        }


    }
}