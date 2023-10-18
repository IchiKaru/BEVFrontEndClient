package com.example.mymap;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity; //will deal with this later

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap googleMap;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Replace with your server's API endpoint for vehicle location data
        @SuppressLint("AuthLeak") String apiUrl = "mongodb+srv://ClientSide:SDpJGwDBlEJLTtKZ@rt-location.ahaubf7.mongodb.net/?retryWrites=true&w=majority";

        new FetchVehicleLocationsTask().execute(apiUrl);
    }

    private class FetchVehicleLocationsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                Scanner scanner = new Scanner(stream, "UTF-8").useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray locations = new JSONArray(result);
                    for (int i = 0; i < locations.length(); i++) {
                        JSONObject location = locations.getJSONObject(i);
                        double latitude = location.getDouble("latitude");
                        double longitude = location.getDouble("longitude");
                        LatLng vehiclePosition = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(vehiclePosition));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MapsActivity.this, "Failed to fetch vehicle locations.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
