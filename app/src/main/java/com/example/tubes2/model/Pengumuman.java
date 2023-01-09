package com.example.tubes2.model;

public class Pengumuman {
    Integer id;
    String Judul;
    String Tema;
    private boolean isFav;

    public Pengumuman(Integer id){
        this.id = id;
        this.isFav = false;
    }
}
