package com.example.tubes2;

import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.ArrayList;

public interface IMainActivity {
    void loginUser(String email, String password, String role) throws JSONException;
    void loginAuthenticated();
    void executeGetSemesterDilalui();
    void updateListPertemuan(ArrayList<Pertemuan> pertemuans);
    void resetAddForm();
}
