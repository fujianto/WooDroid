package com.septianfujianto.woodroid.Model;

/**
 * Created by Septian A. Fujianto on 11/29/2016.
 */

public class Item {
    private String title;
    private String detail;

    public Item(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
