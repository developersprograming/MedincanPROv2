package com.example.medincanprov2.models;

import java.io.Serializable;

public class Paciente implements Serializable {
    private String nomnbre;
    private String estado;
    private String id;
    private String dni;
    private String idUSer;

    public Paciente() {}

    public Paciente(String nomnbre, String estado, String id, String dni, String idUSer) {
        this.nomnbre = nomnbre;
        this.estado = estado;
        this.id = id;
        this.dni = dni;
        this.idUSer = idUSer;
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

    public String getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(String idUSer) {
        this.idUSer = idUSer;
    }

}
