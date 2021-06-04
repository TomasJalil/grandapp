package com.example.grandapp;

import java.util.ArrayList;

public class TipoMed {
        private int IdTipo;
        private String Tipo;

        public int getId() {
            return IdTipo;
        }

        public void setId(int idTipo) {
            IdTipo = idTipo;
        }

        public String getTipo() {
            return Tipo;
        }

        public void setTipo(String tipo) {
           Tipo = tipo;
        }
    static ArrayList<TipoMed> ListaTipoMed = new ArrayList();

    public ArrayList ListarTipoMed() {
        TipoMed aux = new TipoMed(); //Se crea un objeto Coche y se asigna su referencia a aux

        //se asignan valores a los atributos del nuevo objeto
        aux.setId(IdTipo);
        aux.setTipo(Tipo);

        //se añade el objeto al final del array
        ListaTipoMed.add(aux);
        return ListaTipoMed;
    }
    }
