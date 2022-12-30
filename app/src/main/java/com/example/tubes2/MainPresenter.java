package com.example.tubes2;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pertemuan> pertemuans;
    protected InterfacePertemuan iPertemuan;

    public MainPresenter(InterfacePertemuan iPertemuan){
        this.iPertemuan = iPertemuan;
        this.pertemuans = new ArrayList<>();
    }

    public void addToListPertemuan(Integer id, String judul, String tanggalPertemuan,String waktuPertemuan, String partisipan, String deskripsi){
        pertemuans.add(new Pertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi));
        this.iPertemuan.updateListPertemuan(pertemuans);
    }

    public void delItemListPertemuan(Pertemuan pertemuan){
        pertemuans.remove(pertemuan);
        this.iPertemuan.updateListPertemuan(pertemuans);
    }

    public void toggleFav (Pertemuan pertemuan){
        pertemuan.toggleFavorite();
    }
}
