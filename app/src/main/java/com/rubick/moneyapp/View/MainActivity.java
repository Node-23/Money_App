package com.rubick.moneyapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rubick.moneyapp.Model.Login;
import com.rubick.moneyapp.Model.ServerResponse;
import com.rubick.moneyapp.Model.User;
import com.rubick.moneyapp.R;
import com.rubick.moneyapp.Service.PreferenceData;
import com.rubick.moneyapp.Service.ServerActions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

public class MainActivity extends AppCompatActivity {

    private Button loginBt;
    private TextView registerBt;
    private EditText usernameText;
    private EditText passwordText;

    private final LoadPopup loadPopup = new LoadPopup(MainActivity.this);
    private final String successMessage = "Login was successful";
    private final String errorMessage = "Something went wrong, please try again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //TODO: Verificar se há algum usuário salvo no preferenceData, se sim, e a opção de manter concetado estiver marcada, então fazer login direto
        setItems();
        setButtonFunctions();
    }

    private boolean Validation(){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Some fields are blank", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String ConvertToJson(Login login) {
        Gson gson = new Gson();
        return gson.toJson(login);
    }

    private void Login(){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        Login login = new Login(username, password);
        String data = ConvertToJson(login);
        String url = "http://10.0.2.2:8080/api/user/login";
        Context context = getApplicationContext();
        ServerActions.PostRequest(url, data, MainActivity.this, context, successMessage, errorMessage);
    }

    private void setItems(){
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginBt = findViewById(R.id.loginBt);
        registerBt = findViewById(R.id.registerLink);
    }

    private void setButtonFunctions(){
        registerBt.setOnClickListener(v -> {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        });

        loginBt.setOnClickListener(v -> {
            if(!Validation()){
                return;
            }
            Login();
            Intent switchActivityIntent = new Intent(this, Home.class);
            startActivity(switchActivityIntent);
        });

    }
    //TODO: Make a class just to error/success/other messages
    //TODO: Wait load popup finish to allow click
}