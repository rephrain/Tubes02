package com.example.tubes2;

import android.content.Context;
import android.os.Bundle;

import com.example.tubes2.model.Pengumuman;
import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pertemuan> pertemuans;
    protected ArrayList<Pengumuman> pengumumans;
    protected IMainActivity iMainActivity;
    protected Context context;
    private User user;
    private Pengumuman pengumuman;
    private String page;

    public MainPresenter(IMainActivity iMainActivity, Context context){
        this.iMainActivity = iMainActivity;
        this.pertemuans = new ArrayList<>();
        this.context = context;
    }

    public void addToListPertemuan(Integer id, String judul, String tanggalPertemuan,String waktuPertemuan, String partisipan,String deskripsi){
        pertemuans.add(new Pertemuan(id, judul, tanggalPertemuan, waktuPertemuan, partisipan, deskripsi));
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void delItemListPertemuan(Pertemuan pertemuan){
        pertemuans.remove(pertemuan);
        this.iMainActivity.updateListPertemuan(pertemuans);
    }

    public void callAuthenticateTask(String email, String password, String role) throws JSONException {
        this.iMainActivity.loginUser(email, password, role);
    }

    public void loginAuthenticated(User user) throws JSONException {
        this.user = user;
        this.iMainActivity.changePage("home");
        this.getAcademicYears();
    }

    public void notifyLoginFailed(){
        this.iMainActivity.notifyLoginFailed();
    }

    public void getAcademicYears() throws JSONException {
        this.iMainActivity.getAcademicYears();
    }

    public User getUser(){
        return this.user;
    }

    public void toggleFav (Pertemuan pertemuan){
        pertemuan.toggleFavorite();
    }

    public void callAnnouncementTask(String judul, String[] tags, String content) throws JSONException{
        this.iMainActivity.AddAnnouncement(judul,tags,content);
    }

    public void addedAnnouncement(ArrayList<Pengumuman> pengumumans) {
        this.pengumumans = pengumumans;
        this.iMainActivity.changePage("pengumuman");
    }

    public ArrayList<Pengumuman> getPengumuman(){
        return this.pengumumans;
    }
}
