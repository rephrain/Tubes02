package com.example.tubes2.model;

import android.nfc.Tag;

import java.util.ArrayList;

public class Pengumuman {
    String id;
    String title;
    String updated_at;
    String created_at;
    Author author;
    ArrayList<Tags> tags;

    public Pengumuman(String id, String title, String updated_at, String created_at, Author author, ArrayList<Tags> tags){
        this.id = id;
        this.title = title;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.author = author;
        this.tags = tags;
    }
    public String getTitle() {
        return title;
    }
}
class Author{
    String id;
    String author;

    public Author(String id, String author){
        this.id = id;
        this.author = author;
    }
}
class Tags{
    String tag;
    String tag_id;

    public Tags(String tag, String tag_id){
        this.tag = tag;
        this.tag_id = tag_id;
    }
}