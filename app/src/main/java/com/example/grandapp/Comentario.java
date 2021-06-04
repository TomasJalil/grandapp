package com.example.grandapp;
//COMENTARIOS

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;

public class Comentario {
    private int idUsuario;
    private int idComentario;
    private int idPublicacion;

    public int getidUsuario() {
        return idUsuario;
    }

    public void setidUsuario(int idUsuario) {
        idUsuario = idUsuario;
    }

    public int getidComentario() {
        return idComentario;
    }

    public void setidComentario(int idComentario) {
        idComentario = idComentario;
    }

    public int getidPublicacion() {
        return idPublicacion;
    }

    public void setidPublicacion(int idPublicacion) {
        idPublicacion = idPublicacion;
    }
    static ArrayList<Comentario> ListaComentarios = new ArrayList();

    public ArrayList ListarComentarios() {
        Comentario aux = new Comentario(); //Se crea un objeto Coche y se asigna su referencia a aux

        //se asignan valores a los atributos del nuevo objeto
        aux.setidUsuario(idUsuario);
        aux.setidComentario(idComentario);
        aux.setidPublicacion(idPublicacion);

        //se añade el objeto al final del array
        ListaComentarios.add(aux);
        return ListaComentarios;
    }
}
