package com.example.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.pedokio.R;

/**
 * Created by Wim on 11/30/16.
 */
public class SearchFragment extends Fragment {

    private AppCompatActivity activity;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        /*Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        activity = ((AppCompatActivity) getActivity());
        activity.setSupportActionBar(toolbar);*/
        return view;
    }




}
