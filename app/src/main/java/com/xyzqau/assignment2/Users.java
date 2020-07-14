package com.xyzqau.assignment2;

import android.graphics.Bitmap;

public class Users  {
    public String User_Name;
    public String User_UserName;
    public String User_password;
    public String User_age;
    public String User_hobbies;
    public String User_DOB;
    public String User_gender;
    public Bitmap User_photo;

    public Users(String user_Name,String user_UserName, String user_password, String user_age, String user_hobbies,
                 String user_DOB, String user_gender, Bitmap user_photo) {
        User_Name = user_Name;
        User_UserName=user_UserName;
        User_password = user_password;
        User_age = user_age;
        User_hobbies = user_hobbies;
        User_DOB = user_DOB;
        User_gender = user_gender;
        User_photo = user_photo;
    }
    public String getUser_UserName() {
        return User_UserName;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public String getUser_password() {
        return User_password;
    }

    public String getUser_age() {
        return User_age;
    }

    public String getUser_hobbies() {
        return User_hobbies;
    }

    public String getUser_DOB() {
        return User_DOB;
    }

    public String getUser_gender() {
        return User_gender;
    }

    public Bitmap getUser_photo() {
        return User_photo;
    }
}
