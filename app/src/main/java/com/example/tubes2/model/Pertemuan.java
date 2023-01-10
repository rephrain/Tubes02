package com.example.tubes2.model;

public class Pertemuan {
    String id;
    String judul;
    String datetimeStart;
    String datetimeEnd;
    String partisipan;
    String deskripsi;

    public Pertemuan(String id, String judul, String datetimeStart,String datetimeEnd, String deskripsi){
        this.id = id;
        this.judul = judul;
        this.datetimeStart = datetimeStart;
        this.datetimeEnd = datetimeEnd;
//        this.partisipan = partisipan;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getDatetimeStart() {
        return datetimeStart;
    }

    public String getDatetimeEnd() {
        return datetimeEnd;
    }

    public String getPartisipan() {
        return partisipan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}

