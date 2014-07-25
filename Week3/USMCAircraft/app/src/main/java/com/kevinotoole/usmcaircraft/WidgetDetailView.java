package com.kevinotoole.usmcaircraft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: WidgetDetailView.java
 * Purpose: purpose is to hold the detail from an image clicked from the widget
 */


public class WidgetDetailView extends Activity {

    //Global Variables:
    public String detTitle;
    public String detDesc;
    public int detImgId;
    ImageView detailImage;
    TextView detailTitle, detailDescription;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        //set findView:
        detailImage = (ImageView)findViewById(R.id.detailImage);
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailDescription = (TextView)findViewById(R.id.detailDescription);
        doneButton = (Button)findViewById(R.id.detailClose);

        //Receive intent from widget:
        Intent widInt = this.getIntent();
        detImgId = widInt.getIntExtra("IMG", 0);
        detTitle = widInt.getStringExtra("TIT");
        detDesc = widInt.getStringExtra("DES");

        Log.i("WIDGET_DETAIL", detImgId + " " + detTitle  + " " + detDesc);

        //Set Views
        detailImage.setImageResource(detImgId);
        detailTitle.setText(detTitle);
        detailDescription.setText(detDesc);

        //Set onClickListener for done button:
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
