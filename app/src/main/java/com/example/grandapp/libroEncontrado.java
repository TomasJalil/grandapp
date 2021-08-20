package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class libroEncontrado extends AppCompatActivity {
    String tituloLibro;
    String[] vecUrlFoto;
    ArrayList<Libro> ListaLibro = new ArrayList<>();
    private RecyclerView rvLista;
    private RecyclerAdapterLibro adapter;
    private List<itemListLibro> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_encontrado);
        initViews();
        Bundle libroRecibido;
        libroRecibido = this.getIntent().getExtras();
        tituloLibro = libroRecibido.getString("Libro");
        Log.d("LibroRecibido", tituloLibro);
        TareaAsincronicaNombreLibro miTarea = new TareaAsincronicaNombreLibro();
        miTarea.execute();
    }
    private void initViews(){rvLista=findViewById(R.id.rvLista);}

    private void initValues(Bitmap[] imagenConvertida){
        GridLayoutManager manager = new GridLayoutManager(this,2);
        rvLista.setLayoutManager(manager);
    items=getItems(imagenConvertida);
    adapter=new RecyclerAdapterLibro(items);

    adapter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"Seleccion: "+ListaLibro.get(rvLista.getChildAdapterPosition(v)).getTitulo(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(libroEncontrado.this,libroElegido.class);
            Bundle paquete = new Bundle();
            paquete.putString("titulo", ListaLibro.get(rvLista.getChildAdapterPosition(v)).getTitulo());
            paquete.putString("descripcion", ListaLibro.get(rvLista.getChildAdapterPosition(v)).getDescripcion());
            paquete.putString("fecha", ListaLibro.get(rvLista.getChildAdapterPosition(v)).getFecha());
            paquete.putString("idioma", ListaLibro.get(rvLista.getChildAdapterPosition(v)).getIdioma());
            paquete.putString("url",vecUrlFoto[rvLista.getChildAdapterPosition(v)]);
            intent.putExtras(paquete);
            startActivity(intent);
        }
    });

    rvLista.setAdapter(adapter);
    }

    private List<itemListLibro> getItems(Bitmap[] imagenConvertida){
        List<itemListLibro>itemList=new ArrayList<>();
        for (int i=0;i<=imagenConvertida.length-1;i++){
            itemList.add(new itemListLibro(ListaLibro.get(i).getTitulo(),imagenConvertida[i]));
        }
        return itemList;
    }

    private class TareaAsincronicaNombreLibro extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


                TareaAsincronicaFotoLibro miTarea = new TareaAsincronicaFotoLibro();
                miTarea.execute(vecUrlFoto);
            }


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                URL miRuta2 = new URL("https://www.googleapis.com/books/v1/volumes?q=" + tituloLibro + "&key=AIzaSyCO0fc2R_1POdkMbhdrzQpQzGX7bT5ppec");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta2.openConnection();
                if (miConexion.getResponseCode() == 200) {

                    InputStream RespuestaLibro = miConexion.getInputStream();
                    InputStreamReader JSonCrudos = new InputStreamReader(RespuestaLibro, "UTF-8");
                    ProcesarJsonLeidoLibro(JSonCrudos);
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

    public void ProcesarJsonLeidoLibro(InputStreamReader JSonCrudos) {
        String descripcion;
        Log.d("Entra procesarJson", "Entro a procesar Json");
        JsonParser parseadorDeJSon;
        parseadorDeJSon = new JsonParser();
        JsonObject objJsonCrudo;
        objJsonCrudo = parseadorDeJSon.parse(JSonCrudos).getAsJsonObject();
        JsonArray itemsLibro = objJsonCrudo.get("items").getAsJsonArray();
        vecUrlFoto=new String[itemsLibro.size()];
        String tituloLibro;
        String fecha;
        String idioma;
        for (int i = 0; i <= itemsLibro.size() - 1; i++) {
            JsonObject unLibro = itemsLibro.get(i).getAsJsonObject();
            JsonObject infoLibro = unLibro.get("volumeInfo").getAsJsonObject();
            try {
                tituloLibro = infoLibro.get("title").getAsString();

            }
            catch (Exception errorTitulo){
                tituloLibro="null";
            }

            try {
                JsonObject imagenes = infoLibro.get("imageLinks").getAsJsonObject();
                vecUrlFoto[i] = imagenes.get("thumbnail").getAsString();

            } catch (Exception errorImg) {
                vecUrlFoto[i] = "https://i.ibb.co/Yp3jQBJ/Captura.png";
            }




                try {
                    descripcion=infoLibro.get("description").getAsString();

                }catch (Exception errorDescripcion){
                    descripcion="null";
                }


                try {
                    fecha=infoLibro.get("publishedDate").getAsString();
                }
                catch (Exception errorFecha){
                    fecha="null";
                }
                   try {
                        idioma=infoLibro.get("language").getAsString();
                   }catch (Exception errorIdioma){
                       idioma="null";
                   }

                 ListaLibro.add(new Libro(tituloLibro,descripcion,fecha,idioma));
            }
        }

//Tarea que descarga la portada del libro llamada desde el procesado del JSON del libro
    private class TareaAsincronicaFotoLibro extends AsyncTask<String[], Void, Bitmap[]> {
    @Override
    protected void onPostExecute(Bitmap[] imagenConvertida) {
        super.onPostExecute(imagenConvertida);
    initValues(imagenConvertida);
    }

    @Override
        protected Bitmap[] doInBackground(String[]... vecUrlFoto) {
            Bitmap[] ImagenConvertida=new Bitmap[vecUrlFoto[0].length];
            for (int i=0;i<=vecUrlFoto[0].length-1;i++){

                try {
                    URL miRuta;
                    miRuta = new URL(vecUrlFoto[0][i]);
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
