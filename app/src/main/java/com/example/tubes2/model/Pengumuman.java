package com.example.tubes2.model;

import android.nfc.Tag;

import java.util.ArrayList;

public class Pengumuman {
    String id;
    String title;
    String content;
    String[] tags;
    String updated_at;

    public Pengumuman(String title, String content, String[] tags){
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getTitle(){
        return title;
    }

    public String getTags(){
        StringBuilder tag = new StringBuilder();
        for (String s : tags) {
            tag.append(s).append(", ");
        }
        return tag.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.id = content;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }

    public String getContent() {
        return content;
    }
}