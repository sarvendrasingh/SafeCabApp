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

public class CustomerSettingsActivity extends AppCompatActivity {



    private EditText mNameField, mPhoneField;



    private Button mBack, mConfirm;



    private ImageView mProfileImage;



    private FirebaseAuth mAuth;

    private DatabaseReference mCustomerDatabase;



    private String userID;

    private String mName;

    private String mPhone;

    private String mProfileImageUrl;



    private Uri resultUri;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_settings);



        mNameField = (EditText) findViewById(R.id.name);

        mPhoneField = (EditText) findViewById(R.id.phone);



        mProfileImage = (ImageView) findViewById(R.id.profileImage);



        mBack = (Button) findViewById(R.id.back);

        mConfirm = (Button) findViewById(R.id.confirm);



        mAuth = FirebaseAuth.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);



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