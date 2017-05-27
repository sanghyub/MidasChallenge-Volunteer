package com.example.sangh.midasparactice.Model;

import android.graphics.Bitmap;

/**
 * Created by sangh on 2017-05-27.
 */

public class UserInfo {
    private long num;
    private String name;
    private int point;
    private Bitmap userImage;

    public UserInfo(){

    }

    public long getNumber() { return num; };

    public void setNumber(long num) { this.num = num;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }
}
