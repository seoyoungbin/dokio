package com.dokio.tacademy.pedokio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.model.CastModel;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adib on 13-Apr-17.
 */

public class CastFragment extends Fragment {
    public AppCompatActivity activity;
    FlyBanner mBannerLocal;
    CastAdapter CastAdapter;
    ArrayList<CastModel> data;
    RecyclerView recyclerView;


    public static CastFragment newInstance() {

        return new CastFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        activity = ((AppCompatActivity) getActivity());
        mBannerLocal = (FlyBanner)view.findViewById(R.id.banner2);

        initLocalBanner();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCast);
        data = new ArrayList<>();
        // 방향
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
        CastAdapter = new CastAdapter(data);
        // 연결
        recyclerView.setAdapter(CastAdapter);

        // 데이터 설정
        // String title1, String title2, int img, int img2
        data.add(new CastModel("반려견 잘 키우는 방법 3가지","수제간식 만드는 방법",R.drawable.dogc, R.drawable.dog));
        data.add(new CastModel("반려견 잘 키우는 방법 3가지","수제간식 만드는 방법",R.drawable.dogc, R.drawable.dog));
        data.add(new CastModel("반려견 잘 키우는 방법 3가지","수제간식 만드는 방법",R.drawable.dogc, R.drawable.dog));
        data.add(new CastModel("반려견 잘 키우는 방법 3가지","수제간식 만드는 방법",R.drawable.dogc, R.drawable.dog));
        return view;
    }

    private void initLocalBanner() {


        List<String> images = new ArrayList<>();
        images.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EC%A7%B1%EB%8F%85_%EC%83%81%EC%A3%BC%EA%B2%AC_%EA%B0%95%EB%83%89%EC%9D%B4.jpg");
        images.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EC%9E%AC%EB%B0%A9%EC%86%A1%EC%A7%A4.PNG");
        images.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EA%B1%B0%EB%85%B812.PNG");
        images.add("https://s3.ap-northeast-2.amazonaws.com/dokio2/%EA%B1%B0%EB%85%B813.PNG");
        mBannerLocal.setImagesUrl(images);

    }

    public class CastAdapter extends RecyclerView.Adapter {
        ArrayList<CastModel> data;

        public CastAdapter(ArrayList<CastModel> data) {
            this.data = data;
        }

        @Override
        public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_cast_layout, parent, false);
            return new CastViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CastModel castModel = data.get(position);
            CastViewHolder CastViewHolder = (CastViewHolder) holder;

            CastViewHolder.title1.setText("" + castModel.getTitle1());
            CastViewHolder.title2.setText("" + castModel.getTitle2());
            CastViewHolder.img1.setImageResource(castModel.getImg1());
            CastViewHolder.img2.setImageResource(castModel.getImg2());
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    public class CastViewHolder extends RecyclerView.ViewHolder {

        TextView title1, title2;
        ImageView img1, img2;

        public CastViewHolder(View itemView) {
            super(itemView);
            title1 = (TextView) itemView.findViewById(R.id.title1);
            title2 = (TextView) itemView.findViewById(R.id.title2);
            img1 = (ImageView) itemView.findViewById(R.id.img1);
            img2 = (ImageView) itemView.findViewById(R.id.img2);
        }


    }

}
