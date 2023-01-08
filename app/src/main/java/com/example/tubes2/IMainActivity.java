package com.example.tubes2;

import com.example.tubes2.model.Pertemuan;

import java.util.ArrayList;

public interface IMainActivity {
    void executeGetSemesterDilalui();
    void updateListPertemuan(ArrayList<Pertemuan> pertemuans);
    void resetAddForm();
}
