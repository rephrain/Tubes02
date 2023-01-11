package com.example.tubes2.model;

public class MataKuliah {
    private String id;
    private String code;
    private String name;
    private int semester;
    private String score;

    public MataKuliah(String id, String code, String name, int semester, String score) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }
}
