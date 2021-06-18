package com.example.grandapp;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Tema {
    private int IdTema;
    private String Nombre;

    public int getId() {
        return IdTema;
    }

    public void setId(int idtema) {
        IdTema = idtema;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Tema(Integer idTema, String nombre) {
        setId(idTema);
        setNombre(nombre);
    }


}

