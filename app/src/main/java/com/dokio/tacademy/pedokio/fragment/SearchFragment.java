package com.dokio.tacademy.pedokio.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dokio.tacademy.pedokio.DetailActivity;
import com.dokio.tacademy.pedokio.FilterActivity;
import com.dokio.tacademy.pedokio.GpsDetectService;
import com.dokio.tacademy.pedokio.R;
import com.dokio.tacademy.pedokio.SearchViewActivity;
import com.dokio.tacademy.pedokio.U;
import com.dokio.tacademy.pedokio.model.ListModel;
import com.dokio.tacademy.pedokio.model.SearchListResRootModel;
import com.dokio.tacademy.pedokio.net.NetProcess;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.M;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by Adib on 13-Apr-17.
 */

public class SearchFragment extends Fragment implements OnMapReadyCallback {

    public static final int REQEUST_CODE = 1004;
    public static final int RESULT_CODE = 2004;

    MapListAdapter MapListAdapter;
    ArrayList<ListModel> data;
    private GoogleMap mMap;
    MapView mapView;
    private AppCompatActivity activity;
    ImageButton filterbtn, searchbtn, gpsbtn;
    int flagGpsStatus;              // 0:그냥 액티비티 구동되면 1:gps를 설정을 타고 돌아오면
    TextView addr, markercount, buildingtv, tv1, tv2, tv3, searchx;
    boolean isFirstGpsLoad;         // gps가 최초로 로딩되어 좌표값을 획득했는가?
    LatLng myLoc;
    List<Address> addresses;
    double lat,lng;
    Button hotelbtn, cafebtn, hospitalbtn;
    RecyclerView MapRecyclerView;
    String[] title,address,tel;
    int[] _id;

    boolean rvalid = false;
    double[] Alat,Alng;
    double rlat,rlng;
    String name;


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
        searchbtn = (ImageButton) view.findViewById(R.id.searchbtn);
        addr = (TextView) view.findViewById(R.id.addr);
        gpsbtn = (ImageButton) view.findViewById(R.id.gpsbtn);
        hotelbtn = (Button) view.findViewById(R.id.hotelbtn);
        cafebtn = (Button) view.findViewById(R.id.cafebtn);
        hospitalbtn = (Button) view.findViewById(R.id.hospitalbtn);
        markercount = (TextView) view.findViewById(R.id.markercount);
        buildingtv = (TextView) view.findViewById(R.id.buildingtv);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        searchx = (TextView) view.findViewById(R.id.searchx);
        MapRecyclerView = (RecyclerView) view.findViewById(R.id.MapRecyclerView);
        data = new ArrayList<>();



        // 지도를 소유하고 있는 플레그먼트 획득
        mapView = (MapView) view.findViewById(R.id.imap);


        // 버스 등록
        NetProcess.getInstance().getNetBus().register(this);
        U.getInstance().getGpsBus().register(this);

        checkGpsOn();

        hotelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetProcess.getInstance().netCategoryMap("호텔",lat,lng);
                buildingtv.setText("호텔");
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv3.setText("이 검색 되었습니다.");
                buildingtv.setVisibility(View.VISIBLE);
                markercount.setVisibility(View.VISIBLE);
                searchx.setVisibility(View.GONE);
                hotelbtn.setBackgroundColor(Color.parseColor("#ff5252"));
                cafebtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                hospitalbtn.setBackgroundColor(Color.parseColor("#eeeeee"));

            }
        });
        cafebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetProcess.getInstance().netCategoryMap("카페",lat,lng);
                buildingtv.setText("카페");
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setText("가 검색 되었습니다.");
                tv3.setVisibility(View.VISIBLE);
                buildingtv.setVisibility(View.VISIBLE);
                markercount.setVisibility(View.VISIBLE);
                searchx.setVisibility(View.GONE);
                hotelbtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                cafebtn.setBackgroundColor(Color.parseColor("#fdb72f"));
                hospitalbtn.setBackgroundColor(Color.parseColor("#eeeeee"));
            }
        });

        hospitalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetProcess.getInstance().netCategoryMap("병원",lat,lng);
                buildingtv.setText("병원");
                tv3.setText("이 검색 되었습니다.");
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                buildingtv.setVisibility(View.VISIBLE);
                markercount.setVisibility(View.VISIBLE);
                searchx.setVisibility(View.GONE);
                hotelbtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                cafebtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                hospitalbtn.setBackgroundColor(Color.parseColor("#4ab5f9"));
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SearchViewActivity.class);
                startActivityForResult(intent, REQEUST_CODE);
            }
        });


        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, FilterActivity.class);
                startActivity(intent);
            }
        });

        gpsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr.setText(addresses.get(0).getAddressLine(0).toString() );
                mMap.clear();
                MapMarker(lat,lng,"내 위치",0);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                buildingtv.setVisibility(View.GONE);
                markercount.setVisibility(View.GONE);
                searchx.setText("내 위치가 검색되었습니다.");
                searchx.setVisibility(View.VISIBLE);
                hotelbtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                cafebtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                hospitalbtn.setBackgroundColor(Color.parseColor("#eeeeee"));
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQEUST_CODE && resultCode == RESULT_CODE){
            rlat = data.getDoubleExtra("latitude", 0.0);
            rlng = data.getDoubleExtra("longitude", 0.0);
            name = data.getStringExtra("name");
            MapMarker(rlat,rlng,name,4);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 버스 해지
        U.getInstance().getGpsBus().unregister(this);
                NetProcess.getInstance().getNetBus().unregister(this);
        rvalid = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mapView != null) mapView.onCreate(savedInstanceState);
        mapView.onResume();
        // 지도 비동기화진행
        mapView.getMapAsync(this);
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

    // =============================================================================================
    // GPS 세팅

    @Override
    public void onResume() {
        super.onResume();
        // GPS 설정을 처리하고 돌아왓을대 다음 단계로 갈수 있겠금 처리해야한다
        if (flagGpsStatus == 1) {
            flagGpsStatus = 2;
            checkGpsUseOn();
        }
    }

    // 3. OS version 6.0 checking => 동의 여부 확인
    public void checkGpsUseOn() {
        // OS version 6.0 checking => 동의 여부 확인
        if (Build.VERSION.SDK_INT >= M) {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // 동의 되었다
                    gpsDetect(1);
                } else {
                    // 요청 : 1000: 요청코드 (임의로 설정)
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1000);
                }
            } else {
                // 퍼미션을 이미 동의 했다
                gpsDetect(2);
            }
        } else {
            // 6.0 이하
            gpsDetect(3);
        }
    }

    // 코드별 처리
    public void gpsDetect(int code) {
        if (code == 4) {    // 거부했음 -> 종료!!
            Toast.makeText(activity, "GPS를 거부했습니다.", Toast.LENGTH_LONG).show();
        } else if (code == 5 || code == 2 || code == 1) { // 동의한것 => 6.0이상 사용자의미
            startService();
        } else if (code == 3) { // 6.0 이하 단말기이다 그냥 pass

        }
    }

    // GPS를 획득하는 서비스 가동
    public void startService() {
        Intent intent = new Intent(activity, GpsDetectService.class);
        getActivity().startService(intent);
    }

    public void checkGpsOn() {
        // 단말기에서 gps 사용을 on 했는지 체크한다.
        // 정확도를 높이고, gps값을 획득하기 위한 조치
        String gps = android.provider.Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        //Log.i("[gps]="+gps);
        if (!(gps.matches(".*gps*.") || gps.matches(".*network*."))) {
            // GPS 사용 막았다 사용 설정 on 시켜라

            // 다이얼로그 바디
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(activity);
            // 메세지
            alert_confirm.setMessage("GSP를 사용할수 없습니다. 설정 화면으로 이동하시겠습니까?");
            // 확인 버튼 리스너
            alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (flagGpsStatus == 0)
                        flagGpsStatus = 1;
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    checkGpsUseOn();
                }
            });
            // 다이얼로그 생성
            AlertDialog alert = alert_confirm.create();

            // 아이콘
            alert.setIcon(R.drawable.launcher);
            // 다이얼로그 타이틀
            alert.setTitle("알림");
            // 다이얼로그 보기
            alert.show();

        } else {
            // GPS 켜져있다 => 3단계로 이동
            checkGpsUseOn();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            // 동의했냐 거절했냐
            if (grantResults.length > 0) {
                if (grantResults[0] < 0) { // 거부
                    gpsDetect(4);
                } else { // 동의
                    gpsDetect(5);
                }
            }
            for (int i = 0; i < permissions.length; i++) {
                Log.i("T", permissions[i]);
            }
            for (String s : permissions) {
                Log.i("T", s);
            }

        }
    }

    // 서비스로 부터 gps값을 받는다 (버스를 통해)
    @Subscribe
    public void receiveGPS(Location location) {
        // TODO: React to the event somehow!
        if (location != null) {
            //addr.setText(location.getLatitude() + "," + location.getLongitude());
            getAddress(location);
            if (!isFirstGpsLoad) {
                //myLocationShow(location);
                isFirstGpsLoad = true;
                //startAllCoffeeStore();
            }
        }
    }

    // 현재 위치를 지도상에 표시한다(댕겨서)
    public void myLocationShow(Location location) {
        // 화면에 표시
        mMap.clear(); // 마킹은 한개만 둔다 그래서 초기화
        myLoc = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(myLoc).title("내위치"));
        // 지도 줌 처리
        CameraPosition ani = new CameraPosition.Builder()
                .target(myLoc)
                .zoom(13)
                .bearing(60)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ani));

    }

    // lat, lng => address 획득\
    public void getAddress(Location location) {
        if (location == null) return;
        // 기본 재료 준비
        Geocoder geocoder = new Geocoder(activity, Locale.KOREA);
        lat = location.getLatitude();
        lng = location.getLongitude();
        // 변환
        try {
            addresses = geocoder.getFromLocation(lat, lng, 2);
            if (addresses != null && addresses.size() > 0) {
                for (Address address : addresses) {
                    U.getInstance().log(address.toString());
                    U.getInstance().log(address.getThoroughfare());
                }
                U.getInstance().log(addresses.toString());

                U.getInstance().log(U.getInstance().getTransferAddr(addresses.get(0)));
                /*
                  Address[
                    addressLines=[0:"대한민국 서울특별시 관악구 낙성대동"],
                    feature=낙성대동,
                    admin=서울특별시,
                    sub-admin=null,
                    locality=관악구,
                    thoroughfare=낙성대동,
                    postalCode=null,
                    countryCode=KR,
                    countryName=대한민국,
                    hasLatitude=true,
                    latitude=37.4762397,
                    hasLongitude=true,
                    longitude=126.9583907,
                    phone=null,
                    url=null,
                    extras=null]
                 */
            } else {
                Log.i("T", "주소 변환한 결과가 없다");
                addr.setText("주소 변환한 결과가 없다");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("T", "주소 변환 실패");
            addr.setText("주소 변환 실패");
        }

    }

    public void aniMoveCamera(LatLng myLoc)
    {
        CameraPosition ani = new CameraPosition.Builder()
                .target(myLoc)
                .zoom(13)
                .bearing(60)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ani));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng markerloc = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                aniMoveCamera(markerloc);
                marker.showInfoWindow();
                for(int i = 0;i<data.size();i++)
                {

                    if(marker.getPosition().longitude==Alng[i]&&marker.getPosition().latitude==Alat[i]) {
                        MapRecyclerView.smoothScrollToPosition(i);
                    }

                }



                return true;
            }
        });


    }

    public interface OnFragmentInteractionListener {

        void onClicked();

    }

    public void MapMarker(double lat, double lng, String name, int pos)
    {
        if (pos == 0)
        {
            LatLng myloc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myloc).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        }
        else if (pos==1) {
            LatLng myloc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myloc).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.hotel_marker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        }
        else if(pos==2){
            LatLng myloc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myloc).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe_marker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        }
        else if(pos==3){
            LatLng myloc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myloc).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital_marker)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        }
        else if(pos==4){
            LatLng myloc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myloc).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        }

    }

    public class MapListAdapter extends RecyclerView.Adapter {
        ArrayList<ListModel> data;

        public MapListAdapter(ArrayList<ListModel> data) {
            this.data = data;
        }

        @Override
        public MapListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_search_layout, parent, false);
            return new MapListViewHolder(item);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ListModel ListModel = data.get(position);
            MapListViewHolder MapListViewHolder = (MapListViewHolder) holder;

            MapListViewHolder.title.setText("" + ListModel.getTitle());
            MapListViewHolder.address.setText("" + ListModel.getAddress());
            MapListViewHolder.searchlist.setTag(position);
            MapListViewHolder.callbtn.setTag(position);
            //MapListViewHolder.image.setImageDrawable(getResources().getDrawable(R.drawable.dog));
            Picasso.with(activity)
                    .load(ListModel.getImg())
                    .into(MapListViewHolder.image);
            MapListViewHolder.callbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel[pos]));
                    startActivity(intent);

                }
            });

            MapListViewHolder.searchlist.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    Log.i("T","통신결과3: "+pos);
                    intent.putExtra("_id",_id[pos]);
                    startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    public class MapListViewHolder extends RecyclerView.ViewHolder {

        TextView title, address;
        ImageView image;
        LinearLayout searchlist;
        ImageButton callbtn;

        public MapListViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.searchListTitle);
            address = (TextView) itemView.findViewById(R.id.searchListAddress);
            image = (ImageView) itemView.findViewById(R.id.advi);
            callbtn = (ImageButton) itemView.findViewById(R.id.callbtn);
            searchlist = (LinearLayout) itemView.findViewById(R.id.searchlist);

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
            case "mobile_category_hotel": // 회원 가입후 처리!!
            {
                Log.i("T","통신결과: "+res.getResult().size());
                // 상세 정보는 각자 해보시기 바랍니다.!!
                //Log.i("T", "통신결과" + res.getResult().get(0).getWedo().getLat());
                mMap.clear();
                markercount.setText(res.getResult().size() + "개");

                Alat = new double[res.getResult().size()];
                Alng = new double[res.getResult().size()];

                for (int i = 0; i < res.getResult().size(); i++) {
                    MapMarker(res.getResult().get(i).getWedo().getLat(), res.getResult().get(i).getWedo().getLon(), res.getResult().get(i).getName(), 1);
                    Alat[i] = res.getResult().get(i).getWedo().getLat();
                    Alng[i] = res.getResult().get(i).getWedo().getLon();
                }



                if(rvalid == false) {
                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    for (int i = 0; i < res.getResult().size(); i++) {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    // 방향
                    MapRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                    // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
                    MapListAdapter = new MapListAdapter(data);
                    // 연결
                    MapRecyclerView.setAdapter(MapListAdapter);
                    rvalid = true;
                }

                else if(rvalid == true) {

                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    data.clear();
                    MapListAdapter.notifyDataSetChanged();

                    for (int i = 0; i < res.getResult().size(); i++) {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    MapListAdapter.notifyDataSetChanged();

                }


            }
            break;

            case "mobile_category_cafe": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                //Log.i("T", "통신결과" + res.getResult().get(0).getWedo().getLat());
                mMap.clear();
                markercount.setText(res.getResult().size()+"개");

                Alat = new double[res.getResult().size()];
                Alng = new double[res.getResult().size()];


                for(int i = 0;i<res.getResult().size();i++){
                    MapMarker(res.getResult().get(i).getWedo().getLat(),res.getResult().get(i).getWedo().getLon(),res.getResult().get(i).getName(),2);
                    Alat[i] = res.getResult().get(i).getWedo().getLat();
                    Alng[i] = res.getResult().get(i).getWedo().getLon();
                }

                if(rvalid == false) {

                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    for (int i = 0; i < res.getResult().size(); i++) {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    // 방향
                    MapRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                    // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
                    MapListAdapter = new MapListAdapter(data);
                    // 연결
                    MapRecyclerView.setAdapter(MapListAdapter);
                    rvalid = true;
                }

                else if(rvalid == true)
                {

                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    data.clear();
                    MapListAdapter.notifyDataSetChanged();

                    for (int i = 0; i < res.getResult().size(); i++)
                    {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    MapListAdapter.notifyDataSetChanged();

                }


            }
            break;

            case "mobile_category_hospital": // 회원 가입후 처리!!
            {
                // 상세 정보는 각자 해보시기 바랍니다.!!
                //Log.i("T", "통신결과" + res.getResult().get(0).getWedo().getLat());

                mMap.clear();
                markercount.setText(res.getResult().size()+"개");

                Alat = new double[res.getResult().size()];
                Alng = new double[res.getResult().size()];


                for(int i = 0;i<res.getResult().size();i++){
                    MapMarker(res.getResult().get(i).getWedo().getLat(),res.getResult().get(i).getWedo().getLon(),res.getResult().get(i).getName(),3);
                    Alat[i] = res.getResult().get(i).getWedo().getLat();
                    Alng[i] = res.getResult().get(i).getWedo().getLon();
                }

                if(rvalid == false) {

                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    for (int i = 0; i < res.getResult().size(); i++) {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    // 방향
                    MapRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                    // 아답터 (뷰홀더, 데이터 설정연결, 이벤트등등..)
                    MapListAdapter = new MapListAdapter(data);
                    // 연결
                    MapRecyclerView.setAdapter(MapListAdapter);
                    rvalid = true;
                }

                else if(rvalid == true) {

                    title = new String[res.getResult().size()];
                    address = new String[res.getResult().size()];
                    tel = new String[res.getResult().size()];
                    _id = new int[res.getResult().size()];

                    data.clear();
                    MapListAdapter.notifyDataSetChanged();

                    for (int i = 0; i < res.getResult().size(); i++) {
                        title[i] = res.getResult().get(i).getName();
                        address[i] = res.getResult().get(i).getAddress();
                        tel[i] = res.getResult().get(i).getPhonenumber();
                        _id[i] = res.getResult().get(i).get_id();
                    }

                    for (int i = 0; i < res.getResult().size(); i++)
                        data.add(new ListModel(res.getResult().get(i).getImg_url().get(0), title[i], address[i]));

                    MapListAdapter.notifyDataSetChanged();

                }


            }
            break;
        }
    }


}
