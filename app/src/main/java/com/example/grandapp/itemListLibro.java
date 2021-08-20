package com.example.grandapp;

import android.graphics.Bitmap;

public class itemListLibro {
    String Titulo;
    Bitmap imgResource;

    public itemListLibro(String titulo, Bitmap imgResource) {
        Titulo = titulo;
        this.imgResource = imgResource;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Bitmap getImgResource() {
        return imgResource;
    }

    public void setImgResource(Bitmap imgResource) {
        this.imgResource = imgResource;
    }
}
