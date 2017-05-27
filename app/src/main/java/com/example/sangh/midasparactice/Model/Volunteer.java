package com.example.sangh.midasparactice.Model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by sangh on 2017-05-27.
 */

public class Volunteer {
    private long num;
    private String title;
    private Bitmap Img;
    private Date startDate;
    private Date endDate;
    private String Contents;
    private int point;
    private boolean join;


    public Volunteer() {
        join = false;
    }
    public long getNumber() { return num; };

    public void setNumber(long num) { this.num = num;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return Img;
    }

    public void setImg(Bitmap img) {
        Img = img;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }
}
