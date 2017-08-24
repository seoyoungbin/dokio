package com.dokio.tacademy.pedokio.model;

public class HairModel
{
    int img;
    String title;
    String address;

    public HairModel() {
    }

    public HairModel(int img, String title, String address) {
        this.img = img;
        this.title = title;
        this.address = address;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
