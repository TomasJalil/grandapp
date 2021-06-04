package com.example.grandapp;

import java.util.ArrayList;

public class MedicamentoXusuario {
        private int IdUsuario;
    private int IdMeds;
        private String fechaInicio;
    private String horaInicio;
    private int repeticion;
    private  int repeticionAlerta;
    public int getIdUsuario() {
            return IdUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            IdUsuario = idUsuario;
        }
    public int getIdMeds() {
        return IdMeds;
    }

    public void setIdMeds(int idMeds) {
        IdMeds = idMeds;
    }

        public String getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(String FechaInicio) {
            fechaInicio= FechaInicio;
        }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String HoraInicio) {
        horaInicio= HoraInicio;
    }

        public int getRepeticion() {
            return repeticion;
        }

        public void setRepeticion(int Repeticion) {
            repeticion = Repeticion;
        }
    public int getRepeticionAlerta() {
        return repeticionAlerta;
    }

    public void setRepeticionAlerta(int RepeticionAl) {
        repeticionAlerta = RepeticionAl;
    }
    static ArrayList<MedicamentoXusuario> ListaMedicamentosXUsuario = new ArrayList();

    public ArrayList ListarMedsXUsuario() {
        MedicamentoXusuario aux = new MedicamentoXusuario(); //Se crea un objeto Coche y se asigna su referencia a aux

        //se asignan valores a los atributos del nuevo objeto
        aux.setIdUsuario(IdUsuario);
        aux.setIdMeds(IdMeds);
        aux.setHoraInicio(horaInicio);
        aux.setFechaInicio(fechaInicio);
        aux.setRepeticion(repeticion);
        aux.setRepeticionAlerta(repeticionAlerta);
        //se añade el objeto al final del array
        ListaMedicamentosXUsuario.add(aux);
        return ListaMedicamentosXUsuario;
    }
    }

