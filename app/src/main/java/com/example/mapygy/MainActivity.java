package com.example.mapygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayAdapter<CharSequence> adapter;
    NaverMap myMap;
    Context context = this;
    boolean boo;
    Button btn0, btn1,btn2,btn3,btn4,btn5,btn6;
    InfoWindow infoWindow;
    TableLayout visBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        btn0 = findViewById(R.id.BasicMap);
        btn1 = findViewById(R.id.NaviMap);
        btn2 = findViewById(R.id.SatelliteMap);
        btn3 = findViewById(R.id.HybridMap);
        btn4 = findViewById(R.id.TerrainMap);
        btn5 = findViewById(R.id.NoneMap);
        btn6 = findViewById(R.id.choice);
        visBtn = findViewById(R.id.buttonLayout);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visBtn.getVisibility()==View.VISIBLE){
                    visBtn.setVisibility(View.GONE);
                }
                else {
                    visBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.Basic);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.Navi);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.Satellite);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.Hybrid);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.Terrain);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMap.setMapType(NaverMap.MapType.None);
            }
        });
//        boo = true;
//        btn1 = findViewById(R.id.button);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(boo){
//                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_CADASTRAL, true);
//                    boo = false;
//                }
//                else{
//                    myMap.setMapType(NaverMap.MapType.Basic);
//                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_CADASTRAL, false);
//                    boo = true;
//                }
//            }
//        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
      this.myMap = naverMap;
      myMap.setMapType(NaverMap.MapType.Basic);
      CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.945239, 126.682121));
      naverMap.moveCamera(cameraUpdate);
//      Marker marker1 = new Marker();
//      marker1.setPosition(new LatLng(35.945239, 126.682121));
//      marker1.setMap(naverMap);
//      marker1.setIconTintColor(Color.RED);
//
//      infoWindow = new InfoWindow();
//      marker1.setTag("우리학교 아카데미홀옹ㄹㄹ");
//      marker1.setOnClickListener(overlay -> {
//
//          Marker marker = (Marker)overlay;
//          if (marker.getInfoWindow() == null) {
//              infoWindow.open(marker);
//          } else {
//              infoWindow.close();
//          }
//          return true;
//      });
//
//  //    marker2.setTag("군산 시처어엉");
//
//     infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
//          @NonNull
//          @Override
//          public CharSequence getText(@NonNull InfoWindow infoWindow) {
//              return ((CharSequence)infoWindow.getMarker().getTag());
//          }
//      });


        myMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                boolean mark = true;
                String text = "latitude = "+latLng.latitude+" longitude = "+latLng.longitude;
                Marker marker1 = new Marker();
                marker1.setPosition(new LatLng(latLng.latitude, latLng.longitude));
                marker1.setMap(naverMap);
                marker1.setIconTintColor(Color.RED);



//                    infoWindow = new InfoWindow();
//                    marker1.setOnClickListener(overlay -> {
//                        Marker marker = (Marker)overlay;
//                        if (marker.getInfoWindow() == null) {
//                            infoWindow.open(marker);
//                        } else {
//                            infoWindow.close();
//                        }
//                        return true;
//                    });

//                    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
//                        @NonNull
//                        @Override
//                        public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                            return text;
//                        }
//                    });

                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();



            }
        });
    }

}