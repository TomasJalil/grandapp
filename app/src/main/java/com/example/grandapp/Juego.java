package com.example.grandapp;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {
        ImageButton[] ArrBotones;
        Button Jugar;
        EditText Nombre;
        TextView TextJugadas;
        int CantJugadas;
        int JugadaMax=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ArrBotones = new ImageButton[9];
            ArrBotones[0] = (ImageButton) findViewById(R.id.imgBtn0);
            ArrBotones[1] = (ImageButton) findViewById(R.id.imgBtn1);
            ArrBotones[2] = (ImageButton) findViewById(R.id.imgBtn2);
            ArrBotones[3] = (ImageButton) findViewById(R.id.imgBtn3);
            ArrBotones[4] = (ImageButton) findViewById(R.id.imgBtn4);
            ArrBotones[5] = (ImageButton) findViewById(R.id.imgBtn5);
            ArrBotones[6] = (ImageButton) findViewById(R.id.imgBtn6);
            ArrBotones[7] = (ImageButton) findViewById(R.id.imgBtn7);
            ArrBotones[8] = (ImageButton) findViewById(R.id.imgBtn8);
            for (int x=0;x<9;x++){
                ArrBotones[x].setVisibility(View.INVISIBLE);
            }
            Jugar=findViewById(R.id.btnJugar);
            Nombre=findViewById(R.id.edtNombre);
            TextJugadas=findViewById(R.id.txtJugadas);
        }

        public int Random() {
            Random generadorDeAzar;
            generadorDeAzar = new Random();
            Integer NroRandom;
            NroRandom = generadorDeAzar.nextInt(2);
            Log.d("random","" + NroRandom);
            return NroRandom;

        }

        public void AplicarRandom(View vista) {
            CantJugadas=0;
            if(Nombre.length()==0){
                Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_LONG).show();
            }else {
                int SumaNro = 0;
                do {
                    SumaNro = 0;
                    for (int x = 0; x < 9; x++) {
                        ArrBotones[x].setVisibility(View.VISIBLE);
                        int Nro = Random();
                        Log.d("random", " " + Nro);
                        SumaNro += Nro;
                        if (Nro == 0) {
                            ArrBotones[x].setImageResource(R.drawable.img1);
                        } else {
                            ArrBotones[x].setImageResource(R.drawable.img2);
                        }
                    }
                } while (SumaNro == 0 || SumaNro == 9);
            }

        }
        public void InvertirImagen(int Nro){
            Drawable.ConstantState codImagen1;
            codImagen1 = getDrawable(R.drawable.img1).getConstantState();
            Log.d("foto","codImagen1 es " +codImagen1);
            Drawable.ConstantState codImagen;
            codImagen = ArrBotones[Nro-1].getDrawable().getConstantState();
            if (codImagen == codImagen1){
                ArrBotones[Nro-1].setImageResource(R.drawable.img2);

            }
            else{
                ArrBotones[Nro-1].setImageResource(R.drawable.img1);

            }
            Log.d("foto","codImagen1 es "+ ArrBotones[Nro-1]);
        }
        public void InvertirImagenBoton(int Nro){
            InvertirImagen(5);
            switch (Nro){
                case 0:
                    InvertirImagen(1);
                    InvertirImagen(2);
                    InvertirImagen(4);
                    break;
                case 1:
                    InvertirImagen(1);
                    InvertirImagen(2);
                    InvertirImagen(3);
                    break;
                case 2:
                    InvertirImagen(2);
                    InvertirImagen(6);
                    InvertirImagen(3);
                    break;
                case 3:
                    InvertirImagen(1);
                    InvertirImagen(4);
                    InvertirImagen(7);
                    break;
                case 4:
                    InvertirImagen(2);
                    InvertirImagen(4);
                    InvertirImagen(6);
                    InvertirImagen(8);
                    break;
                case 5:
                    InvertirImagen(3);
                    InvertirImagen(6);
                    InvertirImagen(9);
                    break;
                case 6:
                    InvertirImagen(8);
                    InvertirImagen(4);
                    InvertirImagen(7);
                    break;
                case 7:
                    InvertirImagen(8);
                    InvertirImagen(9);
                    InvertirImagen(7);
                    break;
                case 8:
                    InvertirImagen(9);
                    InvertirImagen(6);
                    InvertirImagen(8);
                    break;
                default:break;

            }
        }
        public void ApretarBtn(View VistaRecibida){
            CantJugadas+=1;
            boolean gano;
            ImageButton BotonPresionado;
            BotonPresionado= (ImageButton)VistaRecibida;
            String TagBotonPresionado;
            TagBotonPresionado=BotonPresionado.getTag().toString();
            int NroBotonPresionado;
            NroBotonPresionado=Integer.parseInt(TagBotonPresionado);
            InvertirImagenBoton(NroBotonPresionado);
            TextJugadas.setText("Jugadas: " + CantJugadas);
            gano=VerificarJuego();
            if(gano==true){
                JugadaMax=CantJugadas;
                Toast.makeText(this, "Felicidades " + Nombre.getText() + " ganaste 2 entradas para Broadway!!!!!!", Toast.LENGTH_LONG).show();
            }

        }
        public boolean VerificarJuego(){
            boolean gano;
            Drawable.ConstantState codImagen1;
            codImagen1 = getDrawable(R.drawable.img1).getConstantState();
            Drawable.ConstantState codImagen;
            int SumaNro = 0;
            for (int x = 0; x < 9; x++) {
                codImagen = ArrBotones[x].getDrawable().getConstantState();
                if ( codImagen==codImagen1) {
                    SumaNro+=0;
                } else {
                    SumaNro+=1;
                }
            }
            if ((SumaNro == 0 || SumaNro == 9)&&(CantJugadas<=JugadaMax || JugadaMax==0 )){
                gano=true;


            }else{
                gano=false;
            }
            return gano;
        }

    }









