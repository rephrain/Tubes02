package com.example.tubes2.model;

import android.nfc.Tag;

import java.util.ArrayList;

public class Pengumuman {
    String title;
    String content;
    ArrayList<String> tags;

    public Pengumuman(String title, String content, ArrayList<String> tags){
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
class Tags{
    String announcement_id;
    String tag_id;

    public Tags(String announcement_id, String tag_id){
        this.announcement_id = announcement_id;
        this.tag_id = tag_id;
    }
}
