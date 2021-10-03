package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.net.URI;

public class Diarios extends AppCompatActivity {
ImageButton clarin,lanacion,pagina12;
String Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarios);
        clarin.findViewById(R.id.clarin);
        lanacion.findViewById(R.id.lanacion);
        pagina12.findViewById(R.id.pagina12);
        clarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url="https://www.clarin.com/";
                 Uri _link= Uri.parse(Url);
                Intent intent= new Intent(Intent.ACTION_VIEW,_link);
                startActivity(intent);

            }
        });
        lanacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url="https://www.lanacion.com.ar/";
                Uri _link= Uri.parse(Url);
                Intent intent= new Intent(Intent.ACTION_VIEW,_link);
                startActivity(intent);

            }
        });
        pagina12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url="https://www.pagina12.com.ar/";
                Uri _link= Uri.parse(Url);
                Intent intent= new Intent(Intent.ACTION_VIEW,_link);
                startActivity(intent);

            }
        });
    }
}