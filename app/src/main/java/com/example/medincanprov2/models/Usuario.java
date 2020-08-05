package com.example.medincanprov2.models;

import java.io.Serializable;

public class Usuario {
    private String nomnbre;
    private String estado;
    private String id;
    private String dni;

    public Usuario() {}

    public Usuario(String nomnbre, String estado, String id, String dni) {
        this.nomnbre = nomnbre;
        this.estado = estado;
        this.id = id;
        this.dni = dni;
    }

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

}
