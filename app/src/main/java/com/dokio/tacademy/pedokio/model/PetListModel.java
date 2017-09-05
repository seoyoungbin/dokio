package com.dokio.tacademy.pedokio.model;

public class PetListModel
{
    String petname;
    String dog_profile_image;
    String seximg;
    int _id;
    boolean isDelete;

    public PetListModel(String petname, String dog_profile_image, String seximg) {
        this.petname = petname;
        this.dog_profile_image = dog_profile_image;
        this.seximg = seximg;
    }

    public PetListModel(String petname, String dog_profile_image, String seximg, int _id) {
        this.petname = petname;
        this.dog_profile_image = dog_profile_image;
        this.seximg = seximg;
        this._id = _id;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getDog_profile_image() {
        return dog_profile_image;
    }

    public void setDog_profile_image(String dog_profile_image) {
        this.dog_profile_image = dog_profile_image;
    }

    public String getSeximg() {
        return seximg;
    }

    public void setSeximg(String seximg) {
        this.seximg = seximg;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
