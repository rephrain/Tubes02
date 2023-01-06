package com.example.tubes2;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pertemuan> pertemuans;
    protected InterfacePertemuan iPertemuan;
    protected InterfaceFRS iFRS;

    public MainPresenter(InterfacePertemuan iPertemuan, InterfaceFRS iFRS){
        this.iPertemuan = iPertemuan;
        this.iFRS = iFRS;
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
