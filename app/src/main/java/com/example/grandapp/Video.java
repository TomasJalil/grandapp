package com.example.grandapp;

import java.util.ArrayList;

public class Video {
    String Foto;
    String Tema;
    String Descripcion;
    String Titulo;
  ArrayList urlVideo;
    int CantVistas;
   public Video (String foto,String tema,String descripcion,String titulo,ArrayList UrlVideo,int cantVistas){
       this.Foto=foto;
       this.Tema=tema;
       this.Descripcion=descripcion;
       this.Titulo=titulo;
       this.urlVideo=UrlVideo;
       this.CantVistas=cantVistas;
   }
   public Video(String tema,ArrayList urlVideo){
       this.Tema=tema;
       this.urlVideo=urlVideo;

   }
}
