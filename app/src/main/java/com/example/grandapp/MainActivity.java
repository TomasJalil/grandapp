package com.example.grandapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button IrRegistro, IrLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IrLogin = findViewById(R.id.IrLgn);
        IrRegistro = findViewById(R.id.irRgstr);

        TareaAsincronica miTarea=new TareaAsincronica();
        miTarea.execute();
    }

        public class TareaAsincronica extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL miRuta = new URL(" http://epok.buenosaires.gob.ar/getCategorias%22");
                            HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                            Log.d("AccesoAPI","Me conecto");
                    if (miConexion.getResponseCode() == 200) {

                        Log.d("AccesoAPI", "todo perfecto");
                        InputStream cuerpoRespuesta = miConexion.getInputStream();
                        InputStreamReader respLeida = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                        }
                    else {
                        Log.d("AccesoAPI","Error en la conexion");
                    }
                    miConexion.disconnect();
                } catch (Exception error) {
                    Log.d("PresionoBoton", "no se creo una URL" + error.getMessage());
                }
                return null;
            }
        }

   public void irRegistro(View view){
       Intent intent = new Intent(this, Registro.class);
       startActivity(intent);
   }
   public void irLogin(View view){
       Intent intent = new Intent(this, Login.class);
       startActivity(intent);
   }


}


