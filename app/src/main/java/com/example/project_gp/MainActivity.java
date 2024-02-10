package com.example.project_gp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.Manifest;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private TextView address, longitude, latitude, latitudeLongitude;
    private Button getLocation;
    private final static int REQUEST_CODE = 100;
    private DatabaseReference databaseReference;
    private String username; // New variable to store the username

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("user_locations");

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarhomepage);
        setSupportActionBar(toolbar);

        // Retrieve data passed from LoginActivity
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            username = intent.getStringExtra("username"); // Retrieve the username

            // Display the user information on the UI
            displayUserInfo(name, email);
        }

        // Set up the button click listener
        Button clinicLocationButton = findViewById(R.id.buttonclinicloc);
        clinicLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity_map_locator activity when the button is clicked
                Intent mapLocatorIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapLocatorIntent);
            }
        });

        address = findViewById(R.id.address);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        latitudeLongitude = findViewById(R.id.latitudeLongitude);
        getLocation = findViewById(R.id.buttonGetCurrentLocation); // Initialize the getLocation button

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
    }

    private void getLastLocation() {
        // Check if location services are enabled
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Location services are not enabled, prompt the user to enable them
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    assert addresses != null;
                                    latitude.setText("Latitude: " + addresses.get(0).getLatitude());
                                    longitude.setText("Longitude: " + addresses.get(0).getLongitude());
                                    latitudeLongitude.setText("Coordinate: " + addresses.get(0).getLatitude() + ", " + addresses.get(0).getLongitude());
                                    address.setText("Address:" + addresses.get(0).getAddressLine(0));

                                    // Save the data to Firebase
                                    saveLocationToFirebase(address.getText().toString(),
                                            addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                // Location is null, show a message or handle accordingly
                                Toast.makeText(MainActivity.this, "Location not available", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void displayUserInfo(String name, String email) {
        TextView welcomeMessageTextView = findViewById(R.id.welcomeMessage);
        TextView userInfoTextView = findViewById(R.id.userInfo);

        if (welcomeMessageTextView != null && userInfoTextView != null) {
            welcomeMessageTextView.setText("Welcome to Medi Finder, " + name + "!");
            userInfoTextView.setText("Name: " + name + "\nEmail: " + email);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_about) {
            // Handle about menu item
            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (itemId == R.id.item_logout) {
            // Handle logout menu item
            // You may want to add a confirmation dialog before logging out
            // For now, let's assume the user is logged out and navigate to LoginActivity
            Intent logoutIntent = new Intent(this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current activity to prevent going back to MainActivity
            return true;
        } else if (itemId == R.id.item_share) {
            // Handle share menu item
            // You can implement the share functionality here
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Visit Our Github Link: ");
            startActivity(Intent.createChooser(shareIntent, "Share using"));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void saveLocationToFirebase(String address, double latitude, double longitude) {
        // Create a unique key for the user location entry
        String locationKey = databaseReference.push().getKey();

        // Create a map to store location details
        Map<String, Object> locationData = new HashMap<>();
        locationData.put("address", address);
        locationData.put("latitude", latitude);
        locationData.put("longitude", longitude);
        locationData.put("coordinate", latitude + ", " + longitude);
        locationData.put("userAgent", getUserAgent()); // Get user agent
        locationData.put("dateTime", getCurrentDateTime()); // Get current date/time

        // Save the data to Firebase using the username as a reference
        databaseReference.child(username).setValue(locationData);

        // Display a success message
        Toast.makeText(this, "Location saved", Toast.LENGTH_SHORT).show();
    }

    // Method to get user agent
    private String getUserAgent() {
        return System.getProperty("http.agent");
    }

    // Method to get current date/time
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
