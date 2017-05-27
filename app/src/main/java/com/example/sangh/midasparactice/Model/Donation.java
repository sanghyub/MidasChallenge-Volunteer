package com.example.sangh.midasparactice.Model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by sangh on 2017-05-27.
 */

public class Donation {
    private long num;
    private  String title;
    private  int point;
    private int totalPoint;
    private String contents;
    private String donationHistory;

    public Donation(){}

    public long getNumber() { return num; };

    public void setNumber(long num) { this.num = num;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getDonationHistory() {
        return donationHistory;
    }

    public void setDonationHistory(String donationHistory) {
        this.donationHistory = donationHistory;
    }
}
