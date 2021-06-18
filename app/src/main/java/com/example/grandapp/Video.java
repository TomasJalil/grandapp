package com.example.grandapp;

import java.util.ArrayList;

public class Video {
    String Foto;
    String Descripcion;
    String Titulo;
  String UrlVideo;
    int CantVistas;
   public Video (String foto,String tema,String descripcion,String titulo,String UrlVideo,int cantVistas){
       this.Foto=foto;
       this.Descripcion=descripcion;
       this.Titulo=titulo;
       this.UrlVideo=UrlVideo;
       this.CantVistas=cantVistas;
   }
   public String getUrl(String urlVideo){
       this.UrlVideo=urlVideo;
    return UrlVideo;
   }
   public String getTitulo(String titulo){
       this.Titulo=titulo;
       return Titulo;
   }
    public String getFoto(String foto){
        this.Foto=foto;
        return Foto;
    }
    public String getDescripcion(String descripcion){
        this.Descripcion=descripcion;
        return Descripcion;
    }
    public Integer getCantVisitas(Integer cantVistas){
        this.CantVistas=cantVistas;
        return CantVistas;
    }
    public void setFoto(String foto) {
        Foto = foto;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public void setUrlVideo(String urlVideo) {
        UrlVideo = urlVideo;
    }
    public void setCantVistas(Integer cantVisitas) {
        CantVistas = cantVisitas;
    }
    public Video( String foto,String descripcion, String titulo, String urlVideo) {
        setFoto( foto);
        setDescripcion( descripcion);
        setTitulo( titulo);
        setUrlVideo(urlVideo);



    }
    }

