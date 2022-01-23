package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.rubick.moneyapp.Model.User;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.PreferenceData;

public class Home extends AppCompatActivity {

    private ImageView historicButton;
    private ImageView profileButton;
    private ImageView addButton;
    private ImageView helpBt;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        user = PreferenceData.getUser(getApplicationContext());
        setItems();
        setButtons();

    }
    //TODO: Recive data from server

    private void setItems(){
        profileButton = findViewById(R.id.profileHomeImage);
        addButton = findViewById(R.id.addButton);
        helpBt = findViewById(R.id.helpBt);
        historicButton = findViewById(R.id.historicBt);
    }

    private void setButtons(){
        addButton.setOnClickListener(v ->{
            Intent addExpense = new Intent(this, AddExpense.class);
            startActivity(addExpense);
        });

        profileButton.setOnClickListener(v->{
            Intent profile = new Intent(this, Profile.class);
            startActivity(profile);
        });

        helpBt.setOnClickListener(v->{
            Intent help = new Intent(this, Help.class);
            startActivity(help);
        });

        historicButton.setOnClickListener(v->{
            Intent historic = new Intent(this, Historic.class);
            startActivity(historic);
        });
    }

}