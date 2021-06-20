package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovimientoElegido extends AppCompatActivity {
TextView TextoTema;
    ArrayList<Tema> TemasLista;
Integer x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        Bundle PaqueteRecibido;
        PaqueteRecibido = this.getIntent().getExtras();
         TemasLista = (ArrayList<Tema>) PaqueteRecibido.getSerializable("Tema");
        TextoTema=findViewById(R.id.textoTema);
        TextoTema.setText(TemasLista.get(x).getNombre());

    }
}