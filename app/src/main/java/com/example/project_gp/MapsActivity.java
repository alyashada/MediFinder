package com.example.project_gp;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_gp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.Manifest;
import android.util.Log;
import android.widget.Toast;


import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;
    RequestQueue requestQueue;
    Gson gson;
    Information[] information;
    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gson = new GsonBuilder().create();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(6.5170,100.2152);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Kangar Health Clinic")
                .position(new LatLng(6.43980000,100.19050000))
                .snippet("Jalan Tun Abdul Razak, Pusat Bandar Kangar, 01000 Kangar, Perlis")


        );

        markerOptions.add(new MarkerOptions().title("Arau Health Clinic")
                .position(new LatLng(6.43241800,100.27043600))
                .snippet("Arau, 02600 Arau, Perlis")


        );

        markerOptions.add(new MarkerOptions().title("Klinik Anda kangar 24 Jam")
                .position(new LatLng(6.41281000,100.18239000))
                .snippet("N0,86 & No.88, Taman Pertiwi Indah, Jalan Kangar,01000 Kangar,Perlis.")


        );

        markerOptions.add(new MarkerOptions().title("Klinik Haji Adnan")
                .position(new LatLng(6.44548000,100.23794000))
                .snippet("13,Taman Jejawi,02600 Arau,Kangar.Open 8:30am-9:00pm")


        );

        markerOptions.add(new MarkerOptions().title("Klinik & Surgeri Sedhu Ram")
                .position(new LatLng(6.43579000,100.30066000))
                .snippet("Pauh,02600 Arau, Perlis")


        );



        markerOptions.add(new MarkerOptions().title("Klinik Kamil Ariff")
                .position(new LatLng(6.42642000,100.27315000))
                .snippet("15, Jalan Syed Hussain,Arau, 02600 Arau Perlis")


        );

        markerOptions.add(new MarkerOptions().title("Kampung Gial Health Clinic")
                .position(new LatLng(6.46496000,100.27347000))
                .snippet("kampung gial,02500,Kangar, Perlis")


        );

        markerOptions.add(new MarkerOptions().title("NAURAH Clinic")
                .position(new LatLng(6.43431000,100.29714000))
                .snippet("20,R6,Kampung Kubang,02600 Arau,Perlis")


        );

        markerOptions.add(new MarkerOptions().title("Klinik REMEDIC Pauh")
                .position(new LatLng(6.43731000,100.30351000))
                .snippet("No.6,Taman Bersatu Pauh,02600 Arau, perlis")


        );

        markerOptions.add(new MarkerOptions().title("Klinik 1 Malaysia Arau")
                .position(new LatLng(6.43770000,100.23235000))
                .snippet("Lot.8, Jalan Jejawi Sematang,Kawasan Perindustrian...")


        );


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,8));
        sendRequest();
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        }else{
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms,200);
        }
    }
    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestQueue.add(stringRequest);


    }

    public Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            information = gson.fromJson(response, Information[].class);

            Log.d("Information", "Number of Information : " + information.length);

            if  (information.length <1) {
                Toast.makeText(getApplicationContext(), "Problem retrieving JSON data", Toast.LENGTH_SHORT).show();
                return;
            }

            for (Information info: information) {
                double lat = Double.parseDouble(info.lat);
                double lng = Double.parseDouble(info.lng);
                String title = info.name;
                String snippet = info.description;



                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat,lng))
                        .title(title)
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                mMap.addMarker(marker);

            }

        }
    };

    public Response.ErrorListener onError = new Response.ErrorListener(){

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();

        }
    };
}