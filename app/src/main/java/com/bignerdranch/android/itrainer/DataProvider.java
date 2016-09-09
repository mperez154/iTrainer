package com.bignerdranch.android.itrainer;

/**
 * Created by mperez5 on 9/8/2016.
 */
public class DataProvider {

    //Create Variables for image, customer name and userName
    private int img_res;
    private String c_name, userName;

    //constructor which has 3 arguments
    public DataProvider(int img_res, String c_name, String userName)
    {
        //Use setter methods to set all variables, passed in via args
        this.setImg_res(img_res);
        this.setC_name(c_name);
        this.setUserName(userName);
    }

    //Setters and Getters for all variables
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
