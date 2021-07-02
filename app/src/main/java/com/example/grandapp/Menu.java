package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    ImageButton IrMovilidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        IrMovilidad=findViewById(R.id.BtnMovilidad);
    }
    public void irMovimiento(View view){
        Intent intent = new Intent(this, Movimiento.class);
        startActivity(intent);
    }
    public void irLibros(View view){
        Intent intent = new Intent(this, LibrosMain.class);
        startActivity(intent);
    }
}