package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rubick.moneyapp.R;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_details);
    }
}