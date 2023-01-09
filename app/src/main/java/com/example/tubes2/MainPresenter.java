package com.example.tubes2;

import android.content.Context;
import android.os.Bundle;

import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pertemuan> pertemuans;
    protected IMainActivity iMainActivity;
    protected Context context;
    private User user;
    private String page;

    public MainPresenter(IMainActivity iMainActivity, Context context){
        this.iMainActivity = iMainActivity;
        this.pertemuans = new ArrayList<>();
        this.context = context;
    }

    public void addToListPertemuan(Integer id, String judul, String tanggalPertemuan,String waktuPertemuan, String partisipan, String jadwalPartisipan,String deskripsi){
        pertemuans.add(new Pertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, jadwalPartisipan, deskripsi));
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void callAuthenticateTask(String email, String password, String role) throws JSONException {
        this.iMainActivity.loginUser(email, password, role);
    }

    public void loginAuthenticated(User user){
        this.user = user;
        this.iMainActivity.changePage("home");
    }

    public void delItemListPertemuan(Pertemuan pertemuan){
        pertemuans.remove(pertemuan);
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void toggleFav (Pertemuan pertemuan){
        pertemuan.toggleFavorite();
    }
}
