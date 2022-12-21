package com.example.shopapp;

public class Model {
    String mPhoneName;
    String mPhoneDescription;
    String mPhonePrice;
    int mPhoneImage;

    public Model(String mPhoneName, String mPhoneDescription, String mPhonePrice, int mPhoneImage) {
        this.mPhoneName = mPhoneName;
        this.mPhoneDescription = mPhoneDescription;
        this.mPhonePrice = mPhonePrice;
        this.mPhoneImage = mPhoneImage;
    }

    public String getmPhoneName() {

        return mPhoneName;
    }

    public String getmPhoneDescription() {

        return mPhoneDescription;
    }

    public String getmPhonePrice() {
        return mPhonePrice;
    }

    public int getmPhoneImage() {

        return mPhoneImage;
    }
}
