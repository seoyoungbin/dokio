package com.example.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.pedokio.BannerLayout;
import com.example.tacademy.pedokio.R;
import com.example.tacademy.pedokio.viewpager.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wim on 11/30/16.
 */
public class HomeFragment extends Fragment{

    private AppCompatActivity activity;

    public HomeFragment() {
    }



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BannerLayout bannerLayout = (BannerLayout)view.findViewById(R.id.banner);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        activity = ((AppCompatActivity) getActivity());
        activity.setSupportActionBar(toolbar);



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


}
