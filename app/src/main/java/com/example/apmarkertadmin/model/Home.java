package com.example.apmarkertadmin.model;

public class Home {
    private String name;
    private int Img;
    private int id;

    public Home(int id, String name, int img) {
        this.name = name;
        this.id = id;
        Img = img;
    }

    public Home(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }
}
