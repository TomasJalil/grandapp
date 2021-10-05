package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class juego extends AppCompatActivity {

    Button boton;
    EditText nombre;
    LinearLayout juego;
    TextView mostrarNombre;
    ImageButton[] vecImg;
    ImageView ganaste;
    TextView contJugadas;
    Integer contadorJugadas=-1;
    TextView jugadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        boton=findViewById(R.id.Boton);
        juego=findViewById(R.id.Cartas);
        ganaste=findViewById(R.id.Win);
        nombre=findViewById(R.id.Nombre);
        contJugadas=findViewById(R.id.ContJugadas);
        mostrarNombre=findViewById(R.id.MostrarNombre);
        jugadas=findViewById(R.id.jugadas);
        vecImg=new ImageButton[9];
        vecImg[0]=findViewById(R.id.img1);
        vecImg[1]=findViewById(R.id.img2);
        vecImg[2]=findViewById(R.id.img3);
        vecImg[3]=findViewById(R.id.img4);
        vecImg[4]=findViewById(R.id.img5);
        vecImg[5]=findViewById(R.id.img6);
        vecImg[6]=findViewById(R.id.img7);
        vecImg[7]=findViewById(R.id.img8);
        vecImg[8]=findViewById(R.id.img9);

        Integer numRandom;

        for (int x=0; x<=8; x++){
            Random RandomGen;
            RandomGen=new Random();
            numRandom=RandomGen.nextInt(2);
            if (numRandom==0){
                vecImg[x].setImageResource(R.drawable.banderaarg);
                vecImg[x].setTag(0);
            }
            else {
                vecImg[x].setImageResource(R.drawable.banderauy);
                vecImg[x].setTag(1);
            }
        }
    }




    public void validarGanar(Integer contadorJugadas){
        Integer contadorGanar=0;
        for (int x=0; x<=8; x++){
            contadorGanar+=(Integer)vecImg[x].getTag();
        }
        if (contadorGanar==0||contadorGanar==9){
            juego.setVisibility(View.GONE);
            ganaste.setVisibility(View.VISIBLE);
            jugadas.setText("Hiciste "+contadorJugadas+" jugadas");
        }
        else {contadorGanar = 0;}

    }



    public void IngresarNombre(View Vista){
        String nombreIng;
        nombreIng=nombre.getText().toString().trim();
        mostrarNombre.setText(nombreIng);
    }

    public void cambioImagen(int n)
    {
        n = n-1;
        if ((Integer)vecImg[n].getTag() == 1)
        {
            vecImg[n].setTag(0);
            vecImg[n].setImageResource(R.drawable.banderaarg);
        }
        else {
            vecImg[n].setTag(1);
            vecImg[n].setImageResource(R.drawable.banderauy);
        }
    }


    public void IniciarJuego(View Vista){
        IngresarNombre(Vista);
        juego.setVisibility(View.VISIBLE);
        boton.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
        int id = Vista.getId();
        CambiarImagenes(id);
        validarGanar(contadorJugadas);
        contadorJugadas++;
        contJugadas.setText("Cantidad jugadas: "+contadorJugadas+"");


    }


    public void reiniciarJuego(View Vista){
        IniciarJuego(Vista);
    }

    public void CambiarImagenes(int id)
    {
        switch(id)
        {
            case R.id.img1:
                cambioImagen(1);
                cambioImagen(2);
                cambioImagen(4);
                cambioImagen(5);
                break;
            case R.id.img2:
                cambioImagen(1);
                cambioImagen(2);
                cambioImagen(3);
                cambioImagen(5);
                break;
            case R.id.img3:
                cambioImagen(2);
                cambioImagen(3);
                cambioImagen(5);
                cambioImagen(6);
                break;
            case R.id.img4:
                cambioImagen(1);
                cambioImagen(4);
                cambioImagen(5);
                cambioImagen(7);
                break;
            case R.id.img5:
                cambioImagen(2);
                cambioImagen(4);
                cambioImagen(5);
                cambioImagen(6);
                cambioImagen(8);
                break;
            case R.id.img6:
                cambioImagen(3);
                cambioImagen(5);
                cambioImagen(6);
                cambioImagen(9);
                break;
            case R.id.img7:
                cambioImagen(4);
                cambioImagen(5);
                cambioImagen(7);
                cambioImagen(8);
                break;
            case R.id.img8:
                cambioImagen(5);
                cambioImagen(7);
                cambioImagen(8);
                cambioImagen(9);
                break;
            case R.id.img9:
                cambioImagen(5);
                cambioImagen(6);
                cambioImagen(8);
                cambioImagen(9);
                break;
        }
    }
}