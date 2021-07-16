package com.example.grandapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Libro implements Parcelable {
    String Titulo;
    String Autor;
    String Descripcion;
    Bitmap UrlMiniatura;

    protected Libro(Parcel in) {
        Titulo = in.readString();
        Autor = in.readString();
        Descripcion = in.readString();
        UrlMiniatura = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Libro> CREATOR = new Creator<Libro>() {
        @Override
        public Libro createFromParcel(Parcel in) {
            return new Libro(in);
        }

        @Override
        public Libro[] newArray(int size) {
            return new Libro[size];
        }
    };

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Bitmap getUrlMiniatura() {
        return UrlMiniatura;
    }

    public void setUrlMiniatura(Bitmap urlMiniatura) {
        UrlMiniatura = urlMiniatura;
    }

    public Libro(String titulo, String autor, String descripcion, Bitmap urlMiniatura) {
        Titulo = titulo;
        Autor = autor;
        Descripcion = descripcion;
        UrlMiniatura = urlMiniatura;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Titulo);
        dest.writeString(Autor);
        dest.writeString(Descripcion);
        dest.writeParcelable(UrlMiniatura, flags);
    }
}
