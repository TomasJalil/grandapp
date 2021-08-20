package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class libroElegido extends AppCompatActivity {
ImageView portadaElegida;
TextView tituloElegido,idiomaElegido,Fechadesalida,descripcionElegida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_elegido);
        portadaElegida=findViewById(R.id.fotoPortadaElegida);
        tituloElegido=findViewById(R.id.TituloLibroElegido);
        idiomaElegido=findViewById(R.id.idiomaElegido);
        Fechadesalida=findViewById(R.id.fechadesalida);
        descripcionElegida=findViewById(R.id.cuerpo);
        Bundle paquete = this.getIntent().getExtras();
        String Titulo = paquete.getString("titulo");
        String Fecha = paquete.getString("fecha");
        String Descripcion = paquete.getString("descripcion");
        String Idioma = paquete.getString("idioma");
        String Url=paquete.getString("url");
        tituloElegido.setText(Titulo);
        idiomaElegido.setText(Idioma);
        Fechadesalida.setText(Fecha);
        descripcionElegida.setText(Descripcion);
        TareaAsincronicaFotoLibro miTarea=new TareaAsincronicaFotoLibro();
        miTarea.execute(Url);
    }

    private class TareaAsincronicaFotoLibro extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            portadaElegida.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... urlFoto) {
            Bitmap ImagenConvertida=null;


                try {
                    URL miRuta;
                    miRuta = new URL(urlFoto[0]);
                    Log.d("Descarga", "Mi ruta obtenida: " + miRuta);
                    HttpURLConnection conexionUrl;
                    conexionUrl = (HttpURLConnection) miRuta.openConnection();
                    if (conexionUrl.getResponseCode() == 200) {
                        InputStream cuerpoDatos = conexionUrl.getInputStream();
                        BufferedInputStream lectorEntrada = new BufferedInputStream(cuerpoDatos);
                        ImagenConvertida = BitmapFactory.decodeStream(lectorEntrada);
                        conexionUrl.disconnect();

                    }


                } catch (Exception err) {

                    Log.d("Descarga", "Error: " + err);
                }

            return ImagenConvertida;
        }
    }
}