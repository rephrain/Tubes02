package com.example.tubes2.model;

public class JadwalDosen {
    Integer id;
    String namaDosen;
    String jadwalSenin;
    String jadwalSelasa;
    String jadwalRabu;
    String jadwalKamis;
    String jadwalJumat;
    private boolean isFav;

    public JadwalDosen(Integer id, String namaDosen, String jadwalSenin,String jadwalSelasa, String jadwalRabu, String jadwalKamis, String jadwalJumat){
        this.id = id;
        this.namaDosen = namaDosen;
        this.jadwalSenin = jadwalSenin;
        this.jadwalSelasa = jadwalSelasa;
        this.jadwalRabu = jadwalRabu;
        this.jadwalKamis = jadwalKamis;
        this.jadwalJumat = jadwalJumat;
        this.isFav = false;
    }

    public Integer getId() {
        return id;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public String getJadwalSenin() {
        return jadwalSenin;
    }

    public String getJadwalSelasa() {
        return jadwalSelasa;
    }

    public String getJadwalRabu() {
        return jadwalRabu;
    }

    public String getJadwalKamis() {
        return jadwalKamis;
    }

    public String getJadwalJumat() {
        return jadwalJumat;
    }

    public void toggleFavorite(){
        this.isFav = !isFav;
    }

    public boolean getIsFav(){
        return this.isFav;
    }
}
