package com.example.mapygy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayAdapter<CharSequence> adapter;
    NaverMap myMap;
    Context context;
    Spinner spinner;
    Button btn0, btn1,btn2,btn3,btn4,btn5,btn6;

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



//        spinner = (Spinner)findViewById(R.id.spinner);
//        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_do,
//                android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(adapter.getItem(i).equals("BASIC"))
//                    myMap.setMapType(NaverMap.MapType.Basic);
//                else if(adapter.getItem(i).equals("NAVI"))
//                    myMap.setMapType(NaverMap.MapType.Navi);
//                else if(adapter.getItem(i).equals("SATELLITE"))
//                    myMap.setMapType(NaverMap.MapType.Satellite);
//                else if(adapter.getItem(i).equals("HYBRID"))
//                    myMap.setMapType(NaverMap.MapType.Hybrid);
//                else if(adapter.getItem(i).equals("TERRAIN"))
//                    myMap.setMapType(NaverMap.MapType.Terrain);
//                else if(adapter.getItem(i).equals("NONE"))
//                    myMap.setMapType(NaverMap.MapType.None);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
          btn0 = findViewById(R.id.BasicMap);
          btn1 = findViewById(R.id.NaviMap);
          btn2 = findViewById(R.id.SatelliteMap);
          btn3 = findViewById(R.id.HybridMap);
          btn4 = findViewById(R.id.TerrainMap);
          btn5 = findViewById(R.id.NoneMap);
          btn6 = findViewById(R.id.choice);

          btn6.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(btn0.getVisibility() == View.VISIBLE)
                      btn0.setVisibility(View.INVISIBLE);
                  else
                      btn0.setVisibility(View.VISIBLE);
                  if(btn1.getVisibility() == View.VISIBLE)
                      btn1.setVisibility(View.INVISIBLE);
                  else
                      btn1.setVisibility(View.VISIBLE);
                  if(btn2.getVisibility() == View.VISIBLE)
                      btn2.setVisibility(View.INVISIBLE);
                  else
                      btn2.setVisibility(View.VISIBLE);
                  if(btn3.getVisibility() == View.VISIBLE)
                      btn3.setVisibility(View.INVISIBLE);
                  else
                      btn3.setVisibility(View.VISIBLE);
                  if(btn4.getVisibility() == View.VISIBLE)
                      btn4.setVisibility(View.INVISIBLE);
                  else
                      btn4.setVisibility(View.VISIBLE);
                  if(btn5.getVisibility() == View.VISIBLE)
                      btn5.setVisibility(View.INVISIBLE);
                  else
                      btn5.setVisibility(View.VISIBLE);
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
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
      this.myMap = naverMap;
       myMap.setMapType(NaverMap.MapType.Basic);
      // naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, false);
        LatLng coord = new LatLng(35.945239, 126.682121);
        Toast.makeText(this.getApplicationContext(),
                "군산대학교  :  위도: " + coord.latitude + ", 경도: " + coord.longitude,
                Toast.LENGTH_SHORT).show();
}
}