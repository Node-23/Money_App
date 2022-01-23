package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.rubick.moneyapp.R;

public class Help extends AppCompatActivity {

    private ImageView backBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_help);

        backBt = findViewById(R.id.helpBackBt);

        backBt.setOnClickListener(v -> {
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });

    }
}