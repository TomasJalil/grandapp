package com.example.grandapp;

import java.util.ArrayList;

public class Video {
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









    }

