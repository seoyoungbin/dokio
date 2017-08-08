package com.example.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.pedokio.R;

/**
 * Created by Wim on 11/30/16.
 */
public class NotificationFragment extends Fragment {

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        return view;
    }

}
