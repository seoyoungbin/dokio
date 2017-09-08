package com.dokio.tacademy.pedokio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dokio.tacademy.pedokio.DiaryActivity;
import com.dokio.tacademy.pedokio.LoginActivity;
import com.dokio.tacademy.pedokio.MyMenuBtnOneActivity;
import com.dokio.tacademy.pedokio.MyMenuBtnThreeActivity;
import com.dokio.tacademy.pedokio.MyMenuBtnTwoActivity;
import com.dokio.tacademy.pedokio.PetListActivity;
import com.dokio.tacademy.pedokio.PetUploadActivity;
import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.model.ResRootModel;
import com.dokio.tacademy.pedokio.model.SearchListResRootModel;
import com.dokio.tacademy.pedokio.net.NetProcess;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Adib on 13-Apr-17.
 */

public class MyMenuFragment extends Fragment {

    private AppCompatActivity activity;
    LinearLayout petupload,starbtn,memobtn,btn1,btn2,btn3;
    Button logoutbtn;
    int pos;
    int size=0;
    TextView nametv;
    CircleImageView profile_image;


    public static MyMenuFragment newInstance() {
        return new MyMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = ((AppCompatActivity) getActivity());

        NetProcess.getInstance().getNetBus().register(this);
        NetProcess.getInstance().mypage_search(LoginActivity.token);

        logoutbtn = (Button) view.findViewById(R.id.logoutbtn);
        petupload = (LinearLayout) view.findViewById(R.id.petupload);
//        starbtn = (LinearLayout) view.findViewById(R.id.starbtn);
        memobtn = (LinearLayout) view.findViewById(R.id.memobtn);
        btn1 = (LinearLayout)view.findViewById(R.id.btn1);
        btn2 = (LinearLayout)view.findViewById(R.id.btn2);
        btn3 = (LinearLayout)view.findViewById(R.id.btn3);
        nametv = (TextView)view.findViewById(R.id.nametv);
        profile_image = (CircleImageView)view.findViewById(R.id.profile_image);


//
        petupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetProcess.getInstance().netpetlistsize(LoginActivity.token);


            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyMenuBtnOneActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyMenuBtnTwoActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyMenuBtnThreeActivity.class);
                startActivity(intent);
            }
        });
//
//        starbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), PetListActivity.class);
//                startActivity(intent);
//            }
//        });
//
        memobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DiaryActivity.class);
                startActivity(intent);
            }
        });
//
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("logout_pos",1);
                startActivity(intent);
                activity.finish();

            }
        });

        return view;

    }

    @Override
    public void onDestroy() {
        // 버스 해지
        NetProcess.getInstance().getNetBus().unregister(this);
        super.onDestroy();
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
            case "mobile_petlistsize": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                size = res.getResult().size();
                Log.i("T","통신결과:"+size);

                if(size==0)
                {
                    Intent intent = new Intent(getActivity(), PetUploadActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), PetListActivity.class);
                    startActivity(intent);
                }
            }


        }
    }

    @Subscribe
    public void onNetResponse(ResRootModel res) {

        // 오류 처리
        if (res.getSuccess_code() <= 0) {
            // 팝업 처리
            Log.i("T", "통신실패");
            return;
        }
        // 성공 처리
        switch (res.getTr()) {
            case "mobile_mypage_search": // 회원 가입후 처리!!
            {

                Log.i("T","통신결과:"+res.getResult().getEmail()+res.getResult().getMyprof_img());
                // 상세 정보는 각자 해보시기 바랍니다.!!
                if(res.getResult().getMyprof_img()==null) {
                    profile_image.setImageResource(R.drawable.mymenu_user);
                    profile_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                }
                else{
                    Picasso.with(getActivity())
                            .load(res.getResult().getMyprof_img())
                            .into(profile_image);
                }
                nametv.setText(res.getResult().getEmail());


            }


        }
    }
}
