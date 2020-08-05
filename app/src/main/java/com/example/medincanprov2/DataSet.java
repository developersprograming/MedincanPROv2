package com.example.medincanprov2;

import com.example.medincanprov2.models.Usuario;

public class DataSet {

    private static DataSet instance;

    private Usuario currentUser;

    private DataSet() {
    }

    public static DataSet getInstance() {
        if (instance == null) {
            instance = new DataSet();
        }
        return instance;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }
}
