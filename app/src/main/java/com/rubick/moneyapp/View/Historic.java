package com.rubick.moneyapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rubick.moneyapp.Model.Expense;
import com.rubick.moneyapp.Model.User;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.PreferenceData;

import java.util.ArrayList;

public class Historic extends AppCompatActivity {

    private ImageView backBt;
    private RecyclerView historicRecyclerview;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_historic);

        user = PreferenceData.getUser(getApplicationContext());

        historicRecyclerview = findViewById(R.id.historicRecyclerview);
        backBt = findViewById(R.id.historicBackBt);

        backBt.setOnClickListener(v -> {
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });
        HistoricAdapter adapter = new HistoricAdapter(user.getExpenses());
        historicRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        historicRecyclerview.setAdapter(adapter);
    }

    private class HistoricAdapter extends RecyclerView.Adapter<historicViewHolder> {

        private ArrayList<Expense> expenses;

        public HistoricAdapter(ArrayList<Expense> expenses) {
            this.expenses = expenses;
        }

        @NonNull
        @Override
        public historicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new historicViewHolder(getLayoutInflater().inflate(R.layout.historic_items, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull historicViewHolder holder, int position) {
            Expense currentExpense = expenses.get(position);
            holder.bind(currentExpense);
        }

        @Override
        public int getItemCount() {
            return expenses.size();
        }
    }

    private class historicViewHolder extends RecyclerView.ViewHolder{

        public historicViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Expense currentExpense){
            TextView date = itemView.findViewById(R.id.historicDateText);
            TextView typeOfExpense = itemView.findViewById(R.id.typeOfExpenseText);
            TextView valueView = itemView.findViewById(R.id.valueText);

            date.setText(currentExpense.getDate());
            typeOfExpense.setText(currentExpense.getTypeOfExpense().toString());
            String value = "R$ "+ currentExpense.getValue();
            valueView.setText(value);
        }
    }
}



//TODO: Put tooltip in buttons