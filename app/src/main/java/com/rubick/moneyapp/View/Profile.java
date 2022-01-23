package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.rubick.moneyapp.R;

public class Profile extends AppCompatActivity {
    private ImageView backBt;
    private ImageView settingsBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        backBt = findViewById(R.id.profileBackBt);
        settingsBt = findViewById(R.id.settingBt);

        backBt.setOnClickListener(v -> {
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });

        settingsBt.setOnClickListener(v ->{
            Intent settings = new Intent(this, Settings.class);
            startActivity(settings);
        });
    }
}