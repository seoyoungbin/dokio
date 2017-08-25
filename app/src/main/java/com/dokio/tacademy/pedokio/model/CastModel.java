package com.dokio.tacademy.pedokio.model;

public class CastModel
{
    String title1;
    String title2;
    int img1;
    int img2;

    public CastModel() {

    }

    public CastModel(String title1, String title2, int img1, int img2) {
        this.title1 = title1;
        this.title2 = title2;
        this.img1 = img1;
        this.img2 = img2;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

}
