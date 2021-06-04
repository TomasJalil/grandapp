package com.example.grandapp;

import java.util.ArrayList;

public class Publicacion {
    private int IdPublicacion;
    private int CantMegusta;
    private int IdTema;

    public int getidPublicacion() {
        return IdPublicacion;
    }

    public void setidPublicacion(int idPublicacion) {
        IdPublicacion = idPublicacion;
    }

    public int getCantMegusta() {
        return CantMegusta;
    }

    public void setcantMegusta(int   cantMegusta) {
        CantMegusta = cantMegusta;
    }

    public int getidTema() {
        return IdTema;
    }

    public void setidTema(int    idTema) {
        IdTema = idTema;
    }
    static ArrayList<Publicacion> ListaPublicaciones = new ArrayList();

    public ArrayList ListarPublicaciones() {
        Publicacion aux = new Publicacion(); //Se crea un objeto Coche y se asigna su referencia a aux

        //se asignan valores a los atributos del nuevo objeto
        aux.setidPublicacion(IdPublicacion);
        aux.setcantMegusta(CantMegusta);
        aux.setidTema(IdTema);

        //se añade el objeto al final del array
        ListaPublicaciones.add(aux);
        return ListaPublicaciones;
    }
}