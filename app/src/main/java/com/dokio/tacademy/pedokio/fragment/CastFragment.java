package com.dokio.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokio.tacademy.pedokio.R;

/**
 * Created by Adib on 13-Apr-17.
 */

public class CastFragment extends Fragment {

    public static CastFragment newInstance() {
        return new CastFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        return view;
    }

}
