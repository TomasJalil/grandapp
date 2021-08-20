package com.example.grandapp;

import android.graphics.Bitmap;

public class Libro {
    String Titulo; //Listo
    String Descripcion; //Listo
    String fecha; //Listo
    String idioma; //Listo

    public Libro(String titulo, String descripcion, String fecha, String idioma) {
        Titulo = titulo;
        Descripcion = descripcion;
        this.fecha = fecha;
        this.idioma = idioma;

    }


    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }



    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
