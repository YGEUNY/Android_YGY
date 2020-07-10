package com.example.mapygy;

import android.os.AsyncTask;

import com.naver.maps.geometry.LatLng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<LatLng, Void, String> {

    @Override
    protected String doInBackground(LatLng... latLngs) {
        String strCoord = String.valueOf(latLngs[0].latitude) + "," + String.valueOf(latLngs[0].longitude);
        StringBuilder sb = new StringBuilder();
        StringBuilder urlBuilder = new StringBuilder("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=" + strCoord + "&sourcecrs=epsg:4326&output=json&orders=addr"); /* URL */

        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "ub53n5xi8s");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", "az1puWG2euKjPJY4XmlU9gv67VOmb9vZ4KQN0KGw");

            BufferedReader reader;
            InputStreamReader tmp;
            if (conn.getResponseCode() >= 100 && conn.getResponseCode() <= 100) {
                tmp = new InputStreamReader(conn.getInputStream());
                reader = new BufferedReader(tmp);
            } else {
                tmp = new InputStreamReader(conn.getErrorStream());
                reader = new BufferedReader(tmp);
            }
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
