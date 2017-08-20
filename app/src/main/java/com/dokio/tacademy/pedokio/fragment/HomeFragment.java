package com.dokio.tacademy.pedokio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.dokio.tacademy.pedokio.BannerLayout;
import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.viewpager.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adib on 13-Apr-17.
 */

public class HomeFragment extends Fragment {

    private AppCompatActivity activity;
    HorizontalScrollView hscroll;

    private OnFragmentInteractionListener listener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BannerLayout bannerLayout = (BannerLayout)view.findViewById(R.id.banner);
        hscroll = (HorizontalScrollView)view.findViewById(R.id.hscroll);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        activity = ((AppCompatActivity) getActivity());
        activity.setSupportActionBar(toolbar);
        hscroll.setHorizontalScrollBarEnabled(false);



        final List<String> urls = new ArrayList<>();
        urls.add("https://dokio2.s3.ap-northeast-2.amazonaws.com/glory_animal.PNG");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        bannerLayout.setImageLoader(new GlideImageLoader());
        bannerLayout.setViewUrls(urls);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
    }

}
