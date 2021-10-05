package com.example.grandapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    Button IrRegistro, IrLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IrLogin = findViewById(R.id.IrLgn);
        IrRegistro = findViewById(R.id.irRgstr);

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


