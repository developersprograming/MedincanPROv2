package com.example.medincanprov2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Medicamentos implements Parcelable {
    private   String nombreMEdicamento;
    private   String cantidad;
    private  String hora;
    private  String minutos;
  private  String id;
  private  String idUSer;

    public Medicamentos(String nombreMEdicamento, String cantidad, String hora, String minutos, String id, String idUSer) {
        this.nombreMEdicamento = nombreMEdicamento;
        this.cantidad = cantidad;
        this.hora = hora;
        this.minutos = minutos;
        this.id = id;
        this.idUSer = idUSer;
    }

    protected Medicamentos(Parcel in) {
        nombreMEdicamento = in.readString();
        cantidad = in.readString();
        hora = in.readString();
        minutos = in.readString();
        id = in.readString();
        idUSer = in.readString();
    }

    public static final Creator<Medicamentos> CREATOR = new Creator<Medicamentos>() {
        @Override
        public Medicamentos createFromParcel(Parcel in) {
            return new Medicamentos(in);
        }

        @Override
        public Medicamentos[] newArray(int size) {
            return new Medicamentos[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(String idUSer) {
        this.idUSer = idUSer;
    }

    public Medicamentos() {
    }

    public String getNombreMEdicamento() {
        return nombreMEdicamento;
    }

    public void setNombreMEdicamento(String nombreMEdicamento) {
        this.nombreMEdicamento = nombreMEdicamento;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreMEdicamento);
        dest.writeString(cantidad);
        dest.writeString(hora);
        dest.writeString(minutos);
        dest.writeString(id);
        dest.writeString(idUSer);
    }
}
