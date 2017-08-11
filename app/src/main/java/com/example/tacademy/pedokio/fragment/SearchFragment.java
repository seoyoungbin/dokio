package com.example.tacademy.pedokio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tacademy.pedokio.FilterActivity;
import com.example.tacademy.pedokio.R;
import com.example.tacademy.pedokio.SearchViewActivity;

/**
 * Created by Wim on 11/30/16.
 */
public class SearchFragment extends Fragment {

    private AppCompatActivity activity;
    ImageButton search;
    Button filterbtn;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        activity = ((AppCompatActivity) getActivity());
        search = view.findViewById(R.id.searchimg);
        filterbtn = view.findViewById(R.id.filterbtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SearchViewActivity.class);
                startActivity(intent);
            }
        });

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, FilterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }





}
