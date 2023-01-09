package com.example.tubes2.model;

public class Pengumuman {
    Integer id;
    String judul;
    String tema;
    String tanggal;
    private boolean isFav;

    public Pengumuman(Integer id, String judul, String tema, String tanggal){
        this.id = id;
        this.judul = judul;
        this.tema = tema;
        this.tanggal = tanggal;
        this.isFav = false;
    }
    public Integer getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getTema() { return tema; }

    public String getTanggal() { return tanggal; }

    public boolean getIsFav(){
        return this.isFav;
    }
}
