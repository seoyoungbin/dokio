package com.dokio.tacademy.pedokio.model;

public class ListModel
{
    String img;
    String title;
    String address;

    public ListModel() {
    }

    public ListModel(String img, String title, String address) {
        this.img = img;
        this.title = title;
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
