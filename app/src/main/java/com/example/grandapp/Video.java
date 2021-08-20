package com.example.grandapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {
    String Titulo;
    String IdVideo;
    String Descripcion;
    String NombreCanal;
    String UrlMiniatura;

    public Video(String titulo,String idVideo,String descripcion,  String nombreCanal, String urlMiniatura) {
        setTitulo( titulo);
        setIdVideo(idVideo);
        setDescripcion( descripcion);
        setNombreCanal(nombreCanal);
        setUrlMiniatura(urlMiniatura);


    }

    protected Video(Parcel in) {
        Titulo = in.readString();
        IdVideo = in.readString();
        Descripcion = in.readString();
        NombreCanal = in.readString();
        UrlMiniatura = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getTitulo(){return Titulo;}
    public void setTitulo(String titulo) {Titulo = titulo;}
    public String getIdVideo(){return IdVideo;}
    public void setIdVideo(String idVideo) { IdVideo = idVideo;}
    public String getDescripcion(){return Descripcion;}
    public void setDescripcion(String descripcion) {Descripcion = descripcion;}
    public String getNombreCanal(){return NombreCanal;}
    public void setNombreCanal(String nombreCanal){NombreCanal=nombreCanal;}
    public String getUrlMiniatura(){return UrlMiniatura;}
    public void setUrlMiniatura(String urlMiniatura){UrlMiniatura=urlMiniatura;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Titulo);
        dest.writeString(IdVideo);
        dest.writeString(Descripcion);
        dest.writeString(NombreCanal);
        dest.writeString(UrlMiniatura);
    }
}

