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

    public void addToListPertemuan(String id, String judul, String tanggalPertemuan,String waktuPertemuan,String deskripsi){
        pertemuans.add(new Pertemuan(id, judul, tanggalPertemuan, waktuPertemuan, deskripsi));
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
        this.iMainActivity.runGetUserInfoTask();

//        if (this.user.getRole().equals("admin")){
//            this.iMainActivity.hideAddAppointmentForAdmin();
//        }
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

    public void setUserIdName(String id, String name){
        this.user.setId(id);
        this.user.setName(name);
    }

    public void setUserInformationAtHome(){
        this.iMainActivity.setUserInformationAtHome(this.user.getRole(), this.user.getName());
    }

    public void getAppointments() throws JSONException {
        this.iMainActivity.getAppointments();
    }
//    public void toggleFav (Pertemuan pertemuan){
//        pertemuan.toggleFavorite();
//    }
}
