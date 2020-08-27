package com.example.safecabapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MapActivityReference extends AppCompatActivity {
    private Button mGetStarted;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_reference);
        mGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEdit = "sanal"
            }
        });
    }
}