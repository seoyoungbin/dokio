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
import android.widget.LinearLayout;

import com.dokio.tacademy.pedokio.DiaryActivity;
import com.dokio.tacademy.pedokio.LoginActivity;
import com.dokio.tacademy.pedokio.PetListActivity;
import com.dokio.tacademy.pedokio.PetUploadActivity;
import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.model.SearchListResRootModel;
import com.dokio.tacademy.pedokio.net.NetProcess;
import com.squareup.otto.Subscribe;

/**
 * Created by Adib on 13-Apr-17.
 */

public class MyMenuFragment extends Fragment {

    private AppCompatActivity activity;
    LinearLayout petupload,starbtn,memobtn;
    Button logoutbtn;
    int pos;
    int size=0;


    public static MyMenuFragment newInstance() {
        return new MyMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = ((AppCompatActivity) getActivity());

        NetProcess.getInstance().getNetBus().register(this);

//        logoutbtn = (Button) view.findViewById(R.id.logoutbtn);
        petupload = (LinearLayout) view.findViewById(R.id.petupload);
//        starbtn = (LinearLayout) view.findViewById(R.id.starbtn);
        memobtn = (LinearLayout) view.findViewById(R.id.memobtn);
//
        petupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetProcess.getInstance().netpetlist(LoginActivity.token);
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
//        logoutbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                intent.putExtra("logout_pos", pos);
//                startActivity(intent);
//                getActivity().finish();
//
//            }
//        });

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
            case "mobile_petlist": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                size = res.getResult().size();
            }
            break;

        }
    }
}
