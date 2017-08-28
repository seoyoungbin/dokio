package com.dokio.tacademy.pedokio.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dokio.tacademy.pedokio.FilterActivity;
import com.dokio.tacademy.pedokio.R;

/**
 * Created by Adib on 13-Apr-17.
 */

public class SearchFragment extends Fragment {

    private AppCompatActivity activity;
    ImageButton filterbtn;

    private OnFragmentInteractionListener listener;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        activity = ((AppCompatActivity) getActivity());
        filterbtn = (ImageButton) view.findViewById(R.id.filterbtn);



        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, FilterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {

        void onClicked();

    }

}
