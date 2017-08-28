package com.dokio.tacademy.pedokio.model;

public class DogInfoModel
{
    String doginfo1,doginfo2;

    public DogInfoModel() {
    }

    public DogInfoModel(String doginfo1, String doginfo2) {
        this.doginfo1 = doginfo1;
        this.doginfo2 = doginfo2;
    }

    public String getDoginfo1() {
        return doginfo1;
    }

    public void setDoginfo1(String doginfo1) {
        this.doginfo1 = doginfo1;
    }

    public String getDoginfo2() {
        return doginfo2;
    }

    public void setDoginfo2(String doginfo2) {
        this.doginfo2 = doginfo2;
    }
}
