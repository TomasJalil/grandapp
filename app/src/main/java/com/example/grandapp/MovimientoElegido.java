package com.example.grandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MovimientoElegido extends AppCompatActivity {
    ArrayList<Video> ListaVideos;
    ArrayList<String> ListaYoga;
    ArrayList<String> ListaMeditacion;
    ArrayList<String> ListaZumba;
    ArrayList<String> ListaFuncional;
    ArrayList<String> ListaStretching;
    ArrayList<String> ListaBaile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_elegido);
        Bundle PaqueteRecibido;
        PaqueteRecibido = this.getIntent().getExtras();
        String mensaje = PaqueteRecibido.getString("Tema");
        Log.d("Bundle", "Me llego " + mensaje);
        ListaVideos = new ArrayList<>();
        ListaYoga = new ArrayList();
        ListaMeditacion = new ArrayList();
        ListaZumba = new ArrayList();
        ListaFuncional = new ArrayList();
        ListaStretching = new ArrayList();
        ListaBaile = new ArrayList();
        ListaYoga.add("Hjr41tU7PoI");
        ListaYoga.add("LFwRVET62e8");
        ListaYoga.add("6W7bI9kU2so");
        ListaYoga.add("SrltH0sg0bQ");
        ListaYoga.add("8VkoGpAxSss");
        ListaYoga.add("20Xwv2zSIhc");
        ListaMeditacion.add("4AKVbUO85t0");
        ListaMeditacion.add("p_x_wPOkM2o");
        ListaMeditacion.add("zkbssc2d2lA");
        ListaMeditacion.add("dme_KfguNPU");
        ListaMeditacion.add("2x3Ryt1oXIs");
        ListaMeditacion.add("m0y9Z3Bnu0k");
        ListaZumba.add("dQNxnvOxDeY");
        ListaZumba.add("v97HDBtqpEg");
        ListaZumba.add("iQqbeq19uSg");
        ListaZumba.add("bS2mngPhwGE");
        ListaZumba.add("Tsozmjr3xoE");
        ListaFuncional.add("qNklnG3Qc9Q");
        ListaFuncional.add("v58WUdT4Jik");
        ListaFuncional.add("Ljab75tJ5R4");
        ListaFuncional.add("PrlNFrnO4fA");
        ListaFuncional.add("AD1YG9b88bk");
        ListaStretching.add("l7NgOIN0rLw");
        ListaStretching.add("v7jKget1E_g");
        ListaStretching.add("apj3GQoXAYw");
        ListaStretching.add("l7NgOIN0rLw");
        ListaStretching.add("repkCHdC1L8");
        ListaBaile.add("-uLTmQ4Kn_c");
        ListaBaile.add("OreDQTfMeBk");
        ListaBaile.add("I6RYz9TK3ag");
        ListaBaile.add("ZQsLB8i2ENM");
        ListaBaile.add("3ogunT_nt3k");
        ListaBaile.add("TMXA9szWklA");
        Video vidYoga=new Video("Yoga",ListaYoga);
        Video vidMeditacion=new Video("Meditacion",ListaMeditacion);
        Video vidZumba=new Video("Zumba",ListaZumba);
        Video vidStretching=new Video("Stretching",ListaStretching);
        Video vidFuncional=new Video("Funcional",ListaFuncional);
        Video vidBaile=new Video("Baile",ListaBaile);

        ListaVideos.add(vidYoga);
        ListaVideos.add(vidMeditacion);
        ListaVideos.add(vidZumba);
        ListaVideos.add(vidStretching);
        ListaVideos.add(vidFuncional);
        ListaVideos.add(vidBaile);

    }


    }

