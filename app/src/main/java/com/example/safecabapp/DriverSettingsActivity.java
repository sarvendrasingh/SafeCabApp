package com.example.safecabapp;



import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;



import android.app.Activity;

import android.content.Intent;

import android.graphics.Bitmap;

import android.net.Uri;

import android.os.Bundle;

import android.provider.MediaStore;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.RadioButton;

import android.widget.RadioGroup;

public class DriverSettingsActivity extends AppCompatActivity {



    private EditText mNameField, mPhoneField, mCarField;



    private Button mBack, mConfirm;



    private ImageView mProfileImage;



    private FirebaseAuth mAuth;

    private DatabaseReference mDriverDatabase;



    private String userID;

    private String mName;

    private String mPhone;

    private String mCar;

    private String mService;

    private String mProfileImageUrl;



    private Uri resultUri;



    private RadioGroup mRadioGroup;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_driver_settings);





        mNameField = (EditText) findViewById(R.id.name);

        mPhoneField = (EditText) findViewById(R.id.phone);

        mCarField = (EditText) findViewById(R.id.car);



        mProfileImage = (ImageView) findViewById(R.id.profileImage);



        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);



        mBack = (Button) findViewById(R.id.back);

        mConfirm = (Button) findViewById(R.id.confirm);



        mAuth = FirebaseAuth.getInstance();

        userID = mAuth.getCurrentUser().getUid();



        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);



        getUserInfo();



        mProfileImage.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                startActivityForResult(intent, 1);

            }

        });



        mConfirm.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                saveUserInformation();

            }

        });



        mBack.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                finish();

                return;

            }

        });

    }