package com.example.tacademy.pedokio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginDefaultActivity extends Fragment {
    private AppCompatActivity activity;
    Button loginbtn;

    public static LoginDefaultActivity newInstance() {
        return new LoginDefaultActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_default, container, false);
        activity = ((AppCompatActivity) getActivity());
        loginbtn = (Button)view.findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
