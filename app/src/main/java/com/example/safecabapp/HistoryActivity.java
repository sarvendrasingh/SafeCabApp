package com.example.safecabapp;



import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;



import android.annotation.SuppressLint;

import android.app.ProgressDialog;

import android.os.Bundle;

import android.telecom.Call;

import android.text.format.DateFormat;

import android.util.Log;

import android.view.View;

import android.view.textclassifier.ConversationActions;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;



import com.bumptech.glide.request.Request;

import com.example.safecabapp.historyRecyclerView.HistoryAdapter;

import com.example.safecabapp.historyRecyclerView.HistoryObject;

import com.google.android.gms.common.api.Response;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;



import org.json.JSONException;

import org.json.JSONObject;



import java.io.IOException;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Locale;



import javax.security.auth.callback.Callback;



import okhttp3.MediaType;

import okhttp3.OkHttpClient;

import okhttp3.RequestBody;



public class HistoryActivity extends AppCompatActivity {

    private String customerOrDriver, userId;



    private RecyclerView mHistoryRecyclerView;

    private RecyclerView.Adapter mHistoryAdapter;

    private RecyclerView.LayoutManager mHistoryLayoutManager;



    private TextView mBalance;



    private Double Balance = 0.0;



    private Button mPayout;



    private EditText mPayoutEmail;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);



        mBalance = findViewById(R.id.balance);

        mPayout = findViewById(R.id.payout);

        mPayoutEmail = findViewById(R.id.payoutEmail);



        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.historyRecyclerView);

        mHistoryRecyclerView.setNestedScrollingEnabled(false);

        mHistoryRecyclerView.setHasFixedSize(true);

        mHistoryLayoutManager = new LinearLayoutManager(HistoryActivity.this);

        mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);

        mHistoryAdapter = new HistoryAdapter(getDataSetHistory(), HistoryActivity.this);

        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        @Override

        public void onResponse(Call call, Response response)

                    throws IOException {



            int responseCode = response.code();





            if (response.isSuccessful())

                switch (responseCode) {

                    case 200:

                        Snackbar.make(findViewById(R.id.layout), "Payout Successful!", Snackbar.LENGTH_LONG).show();

                        break;

                    case 501:

                        Snackbar.make(findViewById(R.id.layout), "Error: no payout available", Snackbar.LENGTH_LONG).show();

                        break;

                    default:

                        Snackbar.make(findViewById(R.id.layout), "Error: couldn't complete the transaction", Snackbar.LENGTH_LONG).show();

                        break;

                }

            else

                Snackbar.make(findViewById(R.id.layout), "Error: couldn't complete the transaction", Snackbar.LENGTH_LONG).show();



            progress.dismiss();

        }

    });

    */

}

}