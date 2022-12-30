package com.example.tubes2;

public class Pertemuan {
    Integer id;
    String judul;
    String tanggalPertemuan;
    String waktuPertemuan;
    String partisipan;
    String deskripsi;
    private boolean isFav;

    public Pertemuan(Integer id, String judul, String tanggalPertemuan,String waktuPertemuan, String partisipan, String deskripsi){
        this.id = id;
        this.judul = judul;
        this.tanggalPertemuan = tanggalPertemuan;
        this.waktuPertemuan = waktuPertemuan;
        this.partisipan = partisipan;
        this.deskripsi = deskripsi;
        this.isFav = false;
    }

    public Integer getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getWaktuPertemuan() {
        return waktuPertemuan;
    }

    public String getTanggalPertemuan() {
        return tanggalPertemuan;
    }

    public String getPartisipan() {
        return partisipan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void toggleFavorite(){
        this.isFav = !isFav;
    }

    public boolean getIsFav(){
        return this.isFav;
    }
}

