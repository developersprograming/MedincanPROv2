package com.example.medincanprov2;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    static String nomnbre="";
    static String estado="";
    static     String id="";
    static    String dni="";

    public User(String nomnbre, String estado, String id, String dni) {
        this.nomnbre = nomnbre;
        this.estado = estado;
        this.id = id;
        this.dni = dni;
    }

    public User() {

    }

    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
