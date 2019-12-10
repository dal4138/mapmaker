package com.example.jisu.mapmaker;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    static final String TAG="MainActivity";
    static RecyclerView recyclerView;
    static ArrayList<Data> list = new ArrayList<>();
    static ArrayList<String> check = new ArrayList<>();
    static GoogleMap googleMaps;
    static LinearLayoutManager manager;
    static MyCustom custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        custom=new MyCustom(R.layout.layout_item,list);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(custom);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        listSetting();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int i) {
                boolean tag=true;
                if(MainActivity.check.size()==0){
                    MainActivity.check.add(list.get(i).getName());
                }else{
                    for( int x=0;x<MainActivity.check.size();x++){
                        if(MainActivity.check.get(x).equals(list.get(i).getName())){
                            MainActivity.check.remove(x);
                            tag=false;
                            break;
                        }
                    }
                    if(tag){
                        MainActivity.check.add(list.get(i).getName());
                    }
                }
                ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(MainActivity.this);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG,"근손실온다.");
        googleMaps=googleMap;
        googleMaps.clear();
        if(check.size()>=1){
            for(String x :check){
                for(Data y:MainActivity.list){
                    if(x.equals(y.getName())){
                        Log.d(TAG,"들어왔나");
                        //위치값
                        LatLng latLng=new LatLng(y.getX(), y.getY());
                        //지도에 표시 마킹
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.title(y.getName());
                        markerOptions.position(latLng);
                        googleMaps.addMarker(markerOptions);
                        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                    }
                }
            }
        }
        Log.d(TAG,"62번째 줄일것이다.");
    }

    private void listSetting() {
        list.add(new Data("용산", 37.532558, 126.964315));
        list.add(new Data("강남", 37.498665, 127.027637));
        list.add(new Data("홍대", 37.557799, 126.924510));
        list.add(new Data("의정부", 37.739018, 127.045869));
        list.add(new Data("잠실", 37.513542, 127.100134));
        list.add(new Data("디지털미디어시티", 37.578261, 126.900420));
    }
}
