package com.example.tubes2;

import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.ArrayList;

public interface IMainActivity {
    void changePage(String page);
    void loginUser(String email, String password, String role) throws JSONException;
    void AddAnnouncement(String judul, String[] tags, String content) throws JSONException;
    void updateListPertemuan(ArrayList<Pertemuan> pertemuans);
    void notifyLoginFailed();
    void getAcademicYears() throws JSONException;
}
