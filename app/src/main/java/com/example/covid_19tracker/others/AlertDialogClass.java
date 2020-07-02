package com.example.covid_19tracker.others;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.covid_19tracker.R;

public class AlertDialogClass extends Dialog implements View.OnClickListener {

    private TextView tryAgainButton ;
    private AlertDialogClickInterface clickInterface ;

    public AlertDialogClass(@NonNull Context context,AlertDialogClickInterface clickInterface) {
        super(context);
        this.clickInterface = clickInterface ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        TextView tryAgainButton = findViewById(R.id.adTryAgain);
        tryAgainButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.adTryAgain){
            clickInterface.OnAlertButtonClicked();
        }

    }

    public interface AlertDialogClickInterface {
        void OnAlertButtonClicked();
    }

}
