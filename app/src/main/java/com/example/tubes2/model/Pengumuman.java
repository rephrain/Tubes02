package com.example.tubes2.model;

import android.nfc.Tag;

import java.util.ArrayList;

public class Pengumuman {
    String title;
    ArrayList<String> tags;
    String content;

    public Pengumuman(String title, ArrayList<String> tags, String content){
        this.title = title;
        this.tags = tags;
        this.content = content;
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
