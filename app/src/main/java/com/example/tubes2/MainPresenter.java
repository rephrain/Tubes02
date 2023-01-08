package com.example.tubes2;

import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pertemuan> pertemuans;
    protected IMainActivity iMainActivity;
    public MainPresenter(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
        this.pertemuans = new ArrayList<>();
    }

    public void addToListPertemuan(Integer id, String judul, String tanggalPertemuan,String waktuPertemuan, String partisipan, String deskripsi){
        pertemuans.add(new Pertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi));
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void delItemListPertemuan(Pertemuan pertemuan){
        pertemuans.remove(pertemuan);
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void toggleFav (Pertemuan pertemuan){
        pertemuan.toggleFavorite();
    }
}
