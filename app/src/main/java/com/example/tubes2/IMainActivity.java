package com.example.tubes2;

import com.example.tubes2.model.Pertemuan;
import com.example.tubes2.model.User;

import org.json.JSONException;

import java.util.ArrayList;

public interface IMainActivity {
    void changePage(String page);
    void loginUser(String email, String password, String role) throws JSONException;
    void updateListPertemuan(ArrayList<Pertemuan> pertemuans);
    void notifyLoginFailed();
    void getAcademicYears() throws JSONException;
    void setUserInformationAtHome(String role, String nama);
    void runGetUserInfoTask() throws JSONException;
//    void hideAddAppointmentForAdmin();
    void getAppointments() throws JSONException;
}
