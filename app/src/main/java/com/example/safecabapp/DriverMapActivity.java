package com.example.safecabapp;



import androidx.annotation.NonNull;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;





public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback, RoutingListener {


    private GoogleMap mMap;

    Location mLastLocation;

    LocationRequest mLocationRequest;


    private FusedLocationProviderClient mFusedLocationClient;


    private Button mLogout, mSettings, mRideStatus, mHistory;


    private Switch mWorkingSwitch;


    private int status = 0;


    private String customerId = "", destination;

    private LatLng destinationLatLng, pickupLatLng;

    private float rideDistance;


    private Boolean isLoggingOut = false;


    private SupportMapFragment mapFragment;


    private LinearLayout mCustomerInfo;


    private ImageView mCustomerProfileImage;


    private TextView mCustomerName, mCustomerPhone, mCustomerDestination;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_driver_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        polylines = new ArrayList<>();


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        mCustomerInfo = (LinearLayout) findViewById(R.id.customerInfo);


        mCustomerProfileImage = (ImageView) findViewById(R.id.customerProfileImage);


        mCustomerName = (TextView) findViewById(R.id.customerName);

        mCustomerPhone = (TextView) findViewById(R.id.customerPhone);

        mCustomerDestination = (TextView) findViewById(R.id.customerDestination);


        mWorkingSwitch = (Switch) findViewById(R.id.workingSwitch);

        mWorkingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    connectDriver();

                } else {

                    disconnectDriver();

                }

            }

        });


        private void getAssignedCustomer () {

            String driverId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DatabaseReference assignedCustomerRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driverId).child("customerRequest").child("customerRideId");

            assignedCustomerRef.addValueEventListener(new ValueEventListener() {

                @Override

                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        status = 1;

                        customerId = dataSnapshot.getValue().toString();

                        getAssignedCustomerPickupLocation();

                        getAssignedCustomerDestination();

                        getAssignedCustomerInfo();

                    } else {

                        endRide();

                    }

                }


                @Override

                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }


        @Override

        public void onRoutingCancelled () {

        }

        private void erasePolylines () {

            for (Polyline line : polylines) {

                line.remove();

            }

            polylines.clear();

        }

    }
