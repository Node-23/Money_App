package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.rubick.moneyapp.Model.Expense;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.PreferenceData;
import com.rubick.moneyapp.Service.ServerActions;

public class Details extends AppCompatActivity {

    private TextView date;
    private TextView value;
    private TextView type;
    private TextView typeOfExpense;
    private TextView expensePercentage;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_details);
        setItems();
        Expense expense = PreferenceData.getExpense(getApplicationContext());
        setValues(expense);
    }

    private void setValues(Expense expense){
        date.setText(expense.getDate());
        value.setText(expense.getValue().toString());
        type.setText(expense.getType().toString());
        typeOfExpense.setText(expense.getTypeOfExpense().toString());
        if(expense.getMonthlyPercentage() != null){
            expensePercentage.setText(expense.getMonthlyPercentage().toString());
        }else{
            expensePercentage.setText("");
        }
        if(expense.getDescription() != null){
            description.setText(expense.getDescription());
        }else{
            description.setText("");
        }
    }

    private void setItems(){
        date = findViewById(R.id.dateText);
        value = findViewById(R.id.valueText);
        type = findViewById(R.id.typeText);
        typeOfExpense = findViewById(R.id.typeOfExpenseText);
        expensePercentage = findViewById(R.id.percentageText);
        description = findViewById(R.id.descriptionText);
    }
}