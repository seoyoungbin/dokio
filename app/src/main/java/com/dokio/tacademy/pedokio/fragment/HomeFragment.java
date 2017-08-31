package com.dokio.tacademy.pedokio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
        activity = ((AppCompatActivity) getActivity());

        hscroll.setHorizontalScrollBarEnabled(false);



        final List<String> urls = new ArrayList<>();
        urls.add("https://dokio2.s3.ap-northeast-2.amazonaws.com/glory_animal.PNG");
        urls.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EB%A6%AC%EC%96%BC%ED%97%88%EB%B8%8C_%EC%8B%9C%EC%84%A47.PNG");
        urls.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EC%95%84%EB%9D%A0%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90_%EC%8B%9C%EC%84%A46.jpeg");
        urls.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EA%B9%80%EB%AF%BC%EC%84%B1%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90_%EC%8B%9C%EC%84%A46.jpg");
        urls.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/POPO%26MIRU_%ED%99%9C%EB%8F%992.jpg");
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
