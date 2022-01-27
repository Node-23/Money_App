package com.rubick.moneyapp.Service;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.rubick.moneyapp.Model.ServerResponse;
import com.rubick.moneyapp.View.LoadPopup;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerActions {

    public static void GetRequest(String url, Context context) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("RESPONSE_DATA", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data = response.body().string();
                Log.d("RESPONSE_DATA", data);
                PreferenceData.saveExpense(context, data);
            }
        });
    }

    public static void PostRequest(String url, String data, Activity view, Context context, String successMessage, String appErrorMessage, boolean saveUser) {
        LoadPopup loadPopup = new LoadPopup(view);
        loadPopup.StartLoadScreen();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, data);
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("TESTE", "ERRO AO CHAMAR THREAD: " + e.toString());
                loadPopup.DismissLoadScreen();
                AppError(appErrorMessage, context);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String serverResponse = Objects.requireNonNull(response.body()).string();
                    Log.d("TESTE", "SUCESSO NO SERVIDOR: " + serverResponse);
                    if (saveUser) {
                        PreferenceData.saveUser(context, serverResponse);
                    }
                    loadPopup.DismissLoadScreen();
                    ServerSuccess(successMessage, context);
                    //TODO: Sucess message must come from server
                } else {
                    Gson gson = new Gson();
                    ServerResponse serverResponse = gson.fromJson(response.body().string(), ServerResponse.class);
                    Log.d("TESTE", "ERRO NO SERVIDOR: " + serverResponse.getMessage());

                    ServerError(serverResponse.getMessage(), context);
                    loadPopup.DismissLoadScreen();
                }
            }
        });
    }


    private static void ServerError(String serverResponse, Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, serverResponse, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void AppError(String message, Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void ServerSuccess(String message, Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
