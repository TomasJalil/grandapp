package com.example.grandapp;

import java.util.ArrayList;

public class Video {
    String Foto;
    String Descripcion;
    String Titulo;
    String IdVideo;

    public String getIdVideo(){return IdVideo;}
    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }

    public String getTitulo(){return Titulo;}
    public void setTitulo(String titulo) {Titulo = titulo;}

    public String getFoto(){return Foto;}
    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getDescripcion(){return Descripcion;}
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


    public Video( String foto,String descripcion, String titulo, String idVideo) {
        setIdVideo(idVideo);
        setFoto( foto);
        setDescripcion( descripcion);
        setTitulo( titulo);




    }
    }

