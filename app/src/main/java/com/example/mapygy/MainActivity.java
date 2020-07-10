package com.example.mapygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.Symbol;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PolygonOverlay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayAdapter<CharSequence> adapter;
    NaverMap myMap;
    Context context = this;
    boolean boo;
    Button btn0, btn1,btn2,btn3,btn4,btn5,btn6, mapViewBtn;
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
        mapViewBtn = findViewById(R.id.mapViewBtn);

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
        mapViewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.myMap = naverMap;
     //   MyAsyncTask.execute();
        myMap.setMapType(NaverMap.MapType.Basic);
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.945239, 126.682121));
        naverMap.moveCamera(cameraUpdate);

        Marker[] marker = new Marker[100];

        myMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                Marker mark = new Marker();
//                MyAsyncTask async = new MyAsyncTask();
//
//                async.execute(latLng);
//
//                String json = null;
//                //   new MyAsyncTask().execute();
//                try {
//                    json = async.execute(latLng).get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//                String text = json;
                String text = "latitude = " + latLng.latitude + " longitude = " + latLng.longitude;

                mark.setPosition(new LatLng(latLng.latitude, latLng.longitude));
                mark.setMap(naverMap);
                mark.setIconTintColor(Color.RED);
                infoWindow = new InfoWindow();
                mark.setOnClickListener(overlay -> {
                    Marker markerInfo = (Marker) overlay;
                    if (markerInfo.getInfoWindow() == null) {
                        infoWindow.open(markerInfo);
                    } else {
                        infoWindow.close();
                    }
                    return true;
                });

                infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
                    @NonNull
                    @Override
                    public CharSequence getText(@NonNull InfoWindow infoWindow) {
                        return text;
                    }
                });

                PolygonOverlay polygon = new PolygonOverlay();
                polygon.setCoords(Arrays.asList(
                        new LatLng(latLng.latitude, latLng.longitude)
                ));
                polygon.setMap(naverMap);
            }
        });
    }
}

