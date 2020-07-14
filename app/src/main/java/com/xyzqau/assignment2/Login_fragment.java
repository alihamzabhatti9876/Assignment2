package com.xyzqau.assignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_fragment extends Fragment {
    DatabaseHelper db;
    Button lg_btn;
    EditText lg_Username;
    EditText lg_Password;
    String lg_name;
    String lg_password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_login_fragment, container, false);
        db=new DatabaseHelper(getContext());
        lg_Username=v.findViewById(R.id.login_userName);
        lg_Password=v.findViewById(R.id.login_password);
        TextView tview=v.findViewById(R.id.texthint);
        lg_btn=v.findViewById(R.id.SignIn_btn);
        lg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            lg_name=lg_Username.getText().toString();
            lg_password=lg_Password.getText().toString();

            try{
                boolean flag=db.CheckLogin(lg_name,lg_password);
                if(!flag){
                    FragmentManager fm=getActivity().getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.MainFrame,new list_of_User());
                    ft.commit();
                }
                else{
                    Toast.makeText(getContext(),"Faild",Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
            }
        });
        tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp signup=new SignUp();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.MainFrame,signup);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}