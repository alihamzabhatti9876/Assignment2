package com.xyzqau.assignment2;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class list_of_User extends Fragment {
    DatabaseHelper db;
    RecyclerView RView;
    RecyclerAdaptor RAdoptor;
    List<Users> LUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View v=inflater.inflate(R.layout.fragment_list_of__user, container, false);
        db=new DatabaseHelper(getContext());
        LUser=new ArrayList<>();
        LUser=db.UsersData();
        int i=0;
        String User_Name;
        String User_UserName;
        String User_password;
        String User_age;
        String User_hobbies;
        String User_DOB;
        String User_gender;
        Bitmap User_photo;
        while(i<LUser.size())
        {
            User_Name=LUser.get(i).getUser_Name();
            User_UserName=LUser.get(i).getUser_UserName();
            User_password=LUser.get(i).getUser_password();
            User_age=LUser.get(i).getUser_age();
            User_hobbies=LUser.get(i).getUser_hobbies();
            User_DOB=LUser.get(i).getUser_DOB();
            User_gender=LUser.get(i).getUser_gender();
            User_photo=LUser.get(i).getUser_photo();
            Users users=new Users(User_Name,User_UserName,User_password,User_age,User_hobbies,
                    User_DOB,User_gender,User_photo);
            LUser.add(users);

            i++;
        }
        RView = v.findViewById(R.id.RView);
        RAdoptor=new RecyclerAdaptor(LUser);
        RView.setLayoutManager(new LinearLayoutManager(getContext()));
        RView.setAdapter(RAdoptor);
        return v;
    }


}