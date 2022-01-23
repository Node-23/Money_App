package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.rubick.moneyapp.R;

public class Settings extends AppCompatActivity {

    private ImageView backBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        backBt = findViewById(R.id.settingsBackBt);

        backBt.setOnClickListener(v ->{
            Intent profile = new Intent(this, Profile.class);
            startActivity(profile);

            //TODO: Add Tooltip to limit notification text
        });
    }
}