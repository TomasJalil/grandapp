package com.example.grandapp;


import java.util.ArrayList;

public class Usuarios {
    private int Id;
    private String Nombre;
    private String Apellido;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    static ArrayList<Usuarios> ListaUsuarios = new ArrayList();

    public ArrayList ListarUsuarios() {
        Usuarios aux = new Usuarios(); //Se crea un objeto Coche y se asigna su referencia a aux

        //se asignan valores a los atributos del nuevo objeto
        aux.setId(Id);
        aux.setNombre(Nombre);
        aux.setApellido(Apellido);

        //se añade el objeto al final del array
        ListaUsuarios.add(aux);
        return ListaUsuarios;
    }
}
