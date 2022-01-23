package com.rubick.moneyapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rubick.moneyapp.Model.User;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.ServerActions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    private ImageView backBt;
    private ImageView confirmBt;
    private EditText completeNameText;
    private EditText emailText;
    private EditText descriptionText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;

    private LoadPopup loadPopup = new LoadPopup(Register.this);


    private final String successMessage = "User created successfully";
    private final String errorMessage = "Something went wrong, please try again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        setItems();
        setButtonsFunctions();

    }

    private void RegisterUser() {
        String name = completeNameText.getText().toString().trim();
        String username = usernameText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        User user;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        Date today = new Date();

        if (!descriptionText.getText().toString().isEmpty()) {
            String reason = descriptionText.getText().toString().trim();
            user = new User(name, username, email, reason, password, dateFormat.format(today));
        } else {
            user = new User(name, username, email, password, dateFormat.format(today));
        }

        String data = ConvertToJson(user);
        String url = "http://10.0.2.2:8080/api/user/register";
        Context context = getApplicationContext();
        ServerActions.PostRequest(url, data, Register.this, context, successMessage, errorMessage);
        //PostUSer(data);
    }

    private String ConvertToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    private void setItems() {
        confirmPasswordText = findViewById(R.id.confirmPasswordText);
        passwordText = findViewById(R.id.passwordText);
        usernameText = findViewById(R.id.userNameText);
        descriptionText = findViewById(R.id.descriptionInput);
        emailText = findViewById(R.id.emailText);
        completeNameText = findViewById(R.id.completeNameText);
        backBt = findViewById(R.id.registerBackBt);
        confirmBt = findViewById(R.id.confirmBt);
    }

    private void setButtonsFunctions() {
        backBt.setOnClickListener(v -> {
            Intent login = new Intent(this, MainActivity.class);
            startActivity(login);
        });
        confirmBt.setOnClickListener(v -> {
            if (!Validation()) {
                return;
            }
            RegisterUser();
        });
    }

    private boolean Validation() {
        if (!isEmpty()) {
            Toast.makeText(getApplicationContext(), "All mandatory fields must be completed", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!MatchPasswords()) {
            Toast.makeText(getApplicationContext(), "Password fields must match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (PasswordLength()) {
            Toast.makeText(getApplicationContext(), "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isEmpty() {
        if (confirmPasswordText.getText().toString().isEmpty()) return false;
        if (passwordText.getText().toString().isEmpty()) return false;
        if (usernameText.getText().toString().isEmpty()) return false;
        if (emailText.getText().toString().isEmpty()) return false;
        if (completeNameText.getText().toString().isEmpty()) return false;
        return true;
    }

    private boolean MatchPasswords() {
        return passwordText.getText().toString().equals(confirmPasswordText.getText().toString());
    }

    private boolean PasswordLength() {
        return passwordText.getText().toString().length() < 6;
    }


}