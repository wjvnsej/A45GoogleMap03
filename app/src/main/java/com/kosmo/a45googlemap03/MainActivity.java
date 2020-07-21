package com.kosmo.a45googlemap03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO61";

    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                Log.d(TAG, "GoogleMap is ready...");
                map = googleMap;

                //대표위치(서울시청)
                LatLng SEOUL = new LatLng(37.56, 126.97);
                //가산디지털단지역
                LatLng GASAN = new LatLng(37.480379, 126.882744);

                //지역명
                String[] titles = {"가산집", "맥도날드", "잔치집", "계림", "깜닭"};

                //위경도
                ArrayList<LatLng> loc = new ArrayList<LatLng>();
                loc.add(new LatLng(37.477297, 126.895067));
                loc.add(new LatLng(37.480095, 126.881072));
                loc.add(new LatLng(37.478407, 126.880976));
                loc.add(new LatLng(37.478393, 126.883319));
                loc.add(new LatLng(37.481904, 126.883039));

                for(int idx = 0; idx < titles.length; idx++) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(loc.get(idx));
                    markerOptions.title(titles[idx]);
                    markerOptions.snippet("여기는 " + titles[idx]);

                    map.addMarker(markerOptions);
                }
                //카메라 이동 및 최초레벨 지정
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(GASAN, 13));
                //지도유형
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                //마커클릭에 대한 리스너
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(),
                                "Marker클릭 : " + marker.getTitle() + "\n" + marker.getPosition(),
                                Toast.LENGTH_SHORT).show();
                        //true로 하면 InfoWindow가 안나온다.
                        return false;
                    }
                });

                //정보창 클릭에 대한 리스너
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Toast.makeText(getApplicationContext(),
                                "InfoWin클릭 : " + marker.getTitle() + "\n" + marker.getPosition(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        try {
            MapsInitializer.initialize(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}




























