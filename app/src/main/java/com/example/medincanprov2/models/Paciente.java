package com.example.medincanprov2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Paciente implements Parcelable {
    private String nomnbre="";
    private String estado="";
    private     String id="";
    private    String dni="";
    private  String idUSer="";

    public Paciente() {
    }

    public Paciente(String nomnbre, String estado, String id, String dni, String idUSer) {
        this.nomnbre = nomnbre;
        this.estado = estado;
        this.id = id;
        this.dni = dni;
        this.idUSer = idUSer;
    }

    protected Paciente(Parcel in) {
        nomnbre = in.readString();
        estado = in.readString();        id = in.readString();
        dni = in.readString();
        idUSer = in.readString();
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(String idUSer) {
        this.idUSer = idUSer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomnbre);
        dest.writeString(estado);
        dest.writeString(id);
        dest.writeString(dni);
        dest.writeString(idUSer);
    }
}
