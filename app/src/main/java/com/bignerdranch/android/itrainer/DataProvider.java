package com.bignerdranch.android.itrainer;

/**
 * Created by mperez5 on 9/8/2016.
 */
public class DataProvider {

    public DataProvider(int img_res, String c_name, String userName)
    {
        this.setImg_res(img_res);
        this.setC_name(c_name);
        this.setUserName(userName);

    }

    private int img_res;
    private String c_name, userName;

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImg_res() {
        return img_res;
    }

    public String getC_name() {
        return c_name;
    }

    public String getUserName() {
        return userName;
    }
}
