package com.example.grandapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Tema implements Parcelable {
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

    public Tema(Integer idTema, String nombre) {
        setId(idTema);
        setNombre(nombre);
    }
    protected Tema(Parcel in) {
        IdTema = in.readInt();
        Nombre = in.readString();
    }

    public static final Creator<Tema> CREATOR = new Creator<Tema>() {
        @Override
        public Tema createFromParcel(Parcel in) {
            return new Tema(in);
        }

        @Override
        public Tema[] newArray(int size) {
            return new Tema[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdTema);
        dest.writeString(Nombre);
    }
}

