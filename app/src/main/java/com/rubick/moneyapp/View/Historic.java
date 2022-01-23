package com.rubick.moneyapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rubick.moneyapp.R;

public class Historic extends AppCompatActivity {

    private ImageView backBt;
    private RecyclerView historicRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_historic);

        historicRecyclerview = findViewById(R.id.historicRecyclerview);
        backBt = findViewById(R.id.historicBackBt);

        backBt.setOnClickListener(v -> {
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });
        HistoricAdapter adapter = new HistoricAdapter();
        historicRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        historicRecyclerview.setAdapter(adapter);
    }

    private class HistoricAdapter extends RecyclerView.Adapter<historicViewHolder> {

        @NonNull
        @Override
        public historicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new historicViewHolder(getLayoutInflater().inflate(R.layout.historic_items, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull historicViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }

    private class historicViewHolder extends RecyclerView.ViewHolder{

        public historicViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}



//TODO: Put tooltip in buttons