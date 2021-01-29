package com.example.venteproductapp;

public class ClientData {
    public String namec;
    public  String displayc;
    public String imagec;

    public void setNamec(String namec) {
        this.namec = namec;
    }

    public void setImagec(String imagec) {
        this.imagec = imagec;
    }

    public void setDisplayc(String displayc) {
        this.displayc = displayc;
    }

    public String getNamec() {
        return namec;
    }

    public String getImagec() {
        return imagec;
    }

    public String getDisplayc() {
        return displayc;
    }

    public ClientData(String namec, String displayc, String imagec) {
        this.namec = namec;
        this.displayc = displayc;
        this.imagec = imagec;

    }
}
