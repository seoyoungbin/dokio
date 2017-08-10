package com.example.tacademy.pedokio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    FlyBanner mBannerLocal;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        initLocalBanner();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1)
                .setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.tab2)
                .setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);
    }

    private void initLocalBanner() {
        mBannerLocal = (FlyBanner) findViewById(R.id.banner_1);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.dog);
        images.add(R.drawable.dogb);
        images.add(R.drawable.dogc);
        images.add(R.drawable.hoteli);
        mBannerLocal.setImages(images);

        mBannerLocal.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }


}
