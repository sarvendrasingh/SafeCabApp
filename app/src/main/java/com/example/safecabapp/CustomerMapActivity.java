package com.example.safecabapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class CustomerMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_map);
    });

mSettings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CustomerMapActivity.this, CustomerSettingsActivity.class);
            startActivity(intent);
            return;
        }
    });

        mHistory.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CustomerMapActivity.this, HistoryActivity.class);
            intent.putExtra("customerOrDriver", "Customers");
            startActivity(intent);
            return;
        }
    });
    @Override
    public void onKeyExited(String key) {

    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {

    }

    @Override
    public void onGeoQueryReady() {
        if (!driverFound)
        {
            radius++;
            getClosestDriver();
        }
    }

    @Override
    public void onGeoQueryError(DatabaseError error) {

    }
});


@Override
public void onCancelled(DatabaseError databaseError) {
        }
        });

@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

        }else{
        checkLocationPermission();
        }
        }

        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        mMap.setMyLocationEnabled(true);
        }


        boolean getDriversAroundStarted = false;
        List<Marker> markers = new ArrayList<Marker>();
private void getDriversAround(){
        getDriversAroundStarted = true;
        DatabaseReference driverLocation = FirebaseDatabase.getInstance().getReference().child("driversAvailable");

        GeoFire geoFire = new GeoFire(driverLocation);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(mLastLocation.getLongitude(), mLastLocation.getLatitude()), 999999999);

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
@Override
public void onKeyEntered(String key, GeoLocation location) {

        for(Marker markerIt : markers){
        if(markerIt.getTag().equals(key))
        return;
        }

        LatLng driverLocation = new LatLng(location.latitude, location.longitude);

        Marker mDriverMarker = mMap.addMarker(new MarkerOptions().position(driverLocation).title(key).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_car)));
        mDriverMarker.setTag(key);

        markers.add(mDriverMarker);


        }

@Override
public void onKeyExited(String key) {
        for(Marker markerIt : markers){
        if(markerIt.getTag().equals(key)){
        markerIt.remove();
        }
        }
        }

@Override
public void onKeyMoved(String key, GeoLocation location) {
        for(Marker markerIt : markers){
        if(markerIt.getTag().equals(key)){
        markerIt.setPosition(new LatLng(location.latitude, location.longitude));
        }
        }
        }

@Override
public void onGeoQueryReady() {
        }

@Override
public void onGeoQueryError(DatabaseError error) {

        }
        });
        }
        }