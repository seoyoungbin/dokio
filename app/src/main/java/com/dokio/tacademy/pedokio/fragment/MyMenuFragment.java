package com.dokio.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dokio.tacademy.pedokio.R;

/**
 * Created by Adib on 13-Apr-17.
 */

public class MyMenuFragment extends Fragment {

    private AppCompatActivity activity;
    LinearLayout petupload,starbtn,memobtn;
    Button logoutbtn;
    int pos;


    public static MyMenuFragment newInstance() {
        return new MyMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = ((AppCompatActivity) getActivity());

//        logoutbtn = (Button) view.findViewById(R.id.logoutbtn);
//        petupload = (LinearLayout) view.findViewById(R.id.petupload);
//        starbtn = (LinearLayout) view.findViewById(R.id.starbtn);
//        memobtn = (LinearLayout) view.findViewById(R.id.memobtn);
//
//        petupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), PetUploadActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        starbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), PetListActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        memobtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), DiaryActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        logoutbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                intent.putExtra("logout_pos", pos);
//                startActivity(intent);
//                getActivity().finish();
//
//            }
//        });

        return view;

    }
}
