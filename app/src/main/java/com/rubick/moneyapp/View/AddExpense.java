package com.rubick.moneyapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rubick.moneyapp.Model.Expense;
import com.rubick.moneyapp.Model.Login;
import com.rubick.moneyapp.Model.Type;
import com.rubick.moneyapp.Model.TypeOfExpense;
import com.rubick.moneyapp.Model.User;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.PreferenceData;
import com.rubick.moneyapp.Service.ServerActions;

import java.math.BigDecimal;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    private Spinner typeSpinner;
    private Spinner typeOfExpenseSpinner;
    private Button datePicker;
    private DatePickerDialog datePickerDialog;
    private ImageView backBt;
    private ImageView addBt;
    private EditText valueInput;
    private EditText descriptionInput;
    private User user;
    private String dateToParse;

    private final String successMessage = "Expense added successfully";
    private final String errorMessage = "Something went wrong, please try again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_expense);
        user = PreferenceData.getUser(getApplicationContext());
        setItems();
        setButtonFunctions();
    }

    private void PostExpense(String data){
        String url = "http://10.0.2.2:8080/api/expense/register";
        Context context = getApplicationContext();
        //TODO: Fazer uma função que ativa o loadpopup enquanto espera o PostRequest terminar
        ServerActions.PostRequest(url, data, AddExpense.this, context, successMessage, errorMessage);
    }

    private String CreateExpense(){
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(valueInput.getText().toString()));
        String description = descriptionInput.getText().toString();
        Type type = getType(typeSpinner.getSelectedItem().toString());
        TypeOfExpense expenseType = getTypeOfExpense(typeOfExpenseSpinner.getSelectedItem().toString());
        String date = dateToParse;

        Expense expense;
        if(description.isEmpty()){
            expense = new Expense(user.getId(), value, type, expenseType, date);
        }else{
            expense = new Expense(user.getId(), value, type, description, expenseType, date);
        }
        //TODO: Add this expense in app user object
        return ConvertToJson(expense);
    }

    private String ConvertToJson(Expense expense) {
        Gson gson = new Gson();
        return gson.toJson(expense);
    }

    private Type getType(String typeString){
        switch (typeString){
            case "Money":
                return Type.MONEY;
            case "Debit":
                return  Type.DEBIT;
            case "Credit":
                return Type.CREDIT;
            case "PIX":
            return Type.PIX;
        }
        return null;
    }

    private TypeOfExpense getTypeOfExpense(String typeOfExpenseString){
        switch (typeOfExpenseString){
            case "Debt":
                return TypeOfExpense.DEBT;
            case "Study":
                return  TypeOfExpense.STUDY;
            case "Work":
                return TypeOfExpense.WORK;
            case "Entertainment":
                return TypeOfExpense.ENTERTAINMENT;
            case "Other":
                return TypeOfExpense.OTHER;
        }
        return null;
    }

    private boolean Validation(){
        if(valueInput.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "All fields must be completed", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(typeSpinner.getSelectedItem().toString().equals("Select an Item") || typeOfExpenseSpinner.getSelectedItem().toString().equals("Select an Item")){
            Toast.makeText(getApplicationContext(), "All fields must be completed", Toast.LENGTH_SHORT).show();
            return false;
        }

        int year = Integer.parseInt(datePicker.getText().toString().split(" ")[2]);
        if(year < 2020){
            Toast.makeText(getApplicationContext(), "Year must be greater than 2020", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setButtonFunctions(){
        addBt.setOnClickListener(v ->{
            if(!Validation()){
                return;
            }
            String data = CreateExpense();
            PostExpense(data);
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });

        backBt.setOnClickListener(v ->{
            Intent home = new Intent(this, Home.class);
            startActivity(home);
        });
    }

    private void setItems(){
        descriptionInput = findViewById(R.id.descriptionInput);
        valueInput = findViewById(R.id.valueInput);
        typeSpinner = findViewById(R.id.typeSpinner);
        typeOfExpenseSpinner = findViewById(R.id.typeOfExpenseSpinner);
        datePicker = findViewById(R.id.datePicker);
        addBt = findViewById(R.id.addBt);
        backBt = findViewById(R.id.expenseBackBt);

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.TypeComboBox, android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter typeOfExpenseAdapter = ArrayAdapter.createFromResource(this, R.array.TypeOfExpenseComboBox, android.R.layout.simple_spinner_dropdown_item);
        typeOfExpenseSpinner.setAdapter(typeOfExpenseAdapter);
        datePicker.setText(getTodayDate());
        initDatePicker();
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        setDateToParse(year, month, day);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                setDateToParse(year, month, day);
                String date = makeDateString(day, month, year);
                datePicker.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void setDateToParse(int year, int month, int day){
        dateToParse = year + "-" + smallerThanTen(month) + "-" + smallerThanTen(day);
    }

    private String smallerThanTen(int date){
        if(date < 10){
            return "0" + date;
        }
        return String.valueOf(date);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return "JAN";
        } else if (month == 2) {
            return "FEV";
        } else if (month == 3) {
            return "MAR";
        } else if (month == 4) {
            return "APR";
        } else if (month == 5) {
            return "MAY";
        } else if (month == 6) {
            return "JUN";
        } else if (month == 7) {
            return "JUL";
        } else if (month == 8) {
            return "AUG";
        } else if (month == 9) {
            return "SEP";
        } else if (month == 10) {
            return "OCT";
        } else if (month == 11) {
            return "NOV";
        } else if (month == 12) {
            return "DEC";
        }
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}