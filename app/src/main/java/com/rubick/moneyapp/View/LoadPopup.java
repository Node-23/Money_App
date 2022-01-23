package com.rubick.moneyapp.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.rubick.moneyapp.R;

public class LoadPopup {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadPopup(Activity activity){
        this.activity = activity;
    }

    public void StartLoadScreen(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.load_popup, null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void DismissLoadScreen(){
        alertDialog.dismiss();
    }
}
