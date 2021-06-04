package com.example.grandapp;

import java.util.ArrayList;

public class Tema {
        private int IdTema;
        private String Nombre;

        public int getId() {
            return IdTema;
        }

        public void setId(int idtema) {
            IdTema = idtema;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String nombre) {
            Nombre = nombre;
        }
    static ArrayList<Tema> ListaTemas = new ArrayList();

  
    }

