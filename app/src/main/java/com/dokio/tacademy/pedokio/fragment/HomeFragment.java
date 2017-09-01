package com.dokio.tacademy.pedokio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dokio.tacademy.pedokio.BannerLayout;
import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.model.MainAdvertiseModel;
import com.dokio.tacademy.pedokio.model.MainCastModel;
import com.dokio.tacademy.pedokio.model.SearchListResRootModel;
import com.dokio.tacademy.pedokio.net.NetProcess;
import com.dokio.tacademy.pedokio.viewpager.GlideImageLoader;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adib on 13-Apr-17.
 */

public class HomeFragment extends Fragment {

    MainAdvertiseAdapter MainAdvertiseAdapter;
    private AppCompatActivity activity;
    RecyclerView mAdvRecycler;
    RecyclerView mRecycler;
    ArrayList<MainCastModel> data;
    ArrayList<MainAdvertiseModel> data2;
    MainCastAdapter MainCastAdapter;


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
        mRecycler = (RecyclerView)view.findViewById(R.id.mRecycler);
        mAdvRecycler = (RecyclerView)view.findViewById(R.id.mAdvRecycler);
        activity = ((AppCompatActivity) getActivity());
        data = new ArrayList<MainCastModel>();
        data2 = new ArrayList<MainAdvertiseModel>();



        NetProcess.getInstance().getNetBus().register(this);
        NetProcess.getInstance().netCast();
        NetProcess.getInstance().netAdvertise();




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

    
    // MainCastRecyclerView =================================================================================================

    public class MainCastAdapter extends RecyclerView.Adapter {
        ArrayList<MainCastModel> data;

        public MainCastAdapter(ArrayList<MainCastModel> data) {
            this.data = data;
        }

        @Override
        public MainCastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_mainadvertise_layout, parent, false);
            return new MainCastViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MainCastModel MainCastModel = data.get(position);
            MainCastViewHolder MainCastViewHolder = (MainCastViewHolder) holder;

            MainCastViewHolder.adtitle.setText(MainCastModel.getAdtitle());
            Picasso.with(activity)
                    .load(MainCastModel.getAdimg())
                    .into(MainCastViewHolder.adimg);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    public class MainCastViewHolder extends RecyclerView.ViewHolder {

        TextView adtitle;
        ImageView adimg;

        public MainCastViewHolder(View itemView) {
            super(itemView);
            adtitle = (TextView) itemView.findViewById(R.id.adtitle);
            adimg = (ImageView) itemView.findViewById(R.id.adimg);
        }


    }
    // MainAdvertiseRecyclerView ========================================================================

    public class MainAdvertiseAdapter extends RecyclerView.Adapter {
        ArrayList<MainAdvertiseModel> data2;

        public MainAdvertiseAdapter(ArrayList<MainAdvertiseModel> data2) {
            this.data2 = data2;
        }

        @Override
        public MainAdvertiseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_advertise_layout, parent, false);
            return new MainAdvertiseViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MainAdvertiseModel MainAdvertiseModel = data2.get(position);
            MainAdvertiseViewHolder MainAdvertiseViewHolder = (MainAdvertiseViewHolder) holder;

            MainAdvertiseViewHolder.advtitle.setText(MainAdvertiseModel.getAdvtitle());
            MainAdvertiseViewHolder.advaddress.setText(MainAdvertiseModel.getAdvaddress());
            Picasso.with(activity)
                    .load(MainAdvertiseModel.getAdvimg())
                    .into(MainAdvertiseViewHolder.advimg);
        }


        @Override
        public int getItemCount() {
            return data2.size();
        }
    }


    public class MainAdvertiseViewHolder extends RecyclerView.ViewHolder {

        TextView advtitle,advaddress;
        ImageView advimg;

        public MainAdvertiseViewHolder(View itemView) {
            super(itemView);
            advtitle = (TextView) itemView.findViewById(R.id.advtitle);
            advaddress = (TextView) itemView.findViewById(R.id.advaddress);
            advimg = (ImageView) itemView.findViewById(R.id.advimg);
        }


    }


    // 통신 결과 응답 (통신이 끝나면 자동으로 호출된다)
    @Subscribe
    public void onNetResponse(SearchListResRootModel res) {

        // 오류 처리
        if (res.getSuccess_code() <= 0) {
            // 팝업 처리
            Log.i("T", "통신실패");
            return;
        }
        // 성공 처리
        switch (res.getTr()) {
            case "mobile_cast": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                Log.i("T", "통신결과: " + res.getResult().size());
                for (int i=0; i<res.getResult().size();i++) {
                    data.add(new MainCastModel(res.getResult().get(i).getTitle(),res.getResult().get(i).getCastimg()));

                }

                // 방향
                mRecycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
                MainCastAdapter = new MainCastAdapter(data);
                // 연결
                mRecycler.setAdapter(MainCastAdapter);



            }
            break;

            case "mobile_advertise": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                Log.i("T", "통신결과: " + res.getResult().size());
                for (int i=0; i<res.getResult().size();i++) {
                    data2.add(new MainAdvertiseModel(res.getResult().get(i).getImg_url().get(0),res.getResult().get(i).getName(),res.getResult().get(i).getAddress()));

                }

                // 방향
                mAdvRecycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
                MainAdvertiseAdapter = new MainAdvertiseAdapter(data2);
                // 연결
                mAdvRecycler.setAdapter(MainAdvertiseAdapter);



            }
            break;


        }


    }

    @Override
    public void onDestroy() {
        // 버스 해지
        NetProcess.getInstance().getNetBus().unregister(this);
        super.onDestroy();
    }

}
