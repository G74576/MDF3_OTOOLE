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
 * File: DetailView.java
 * Purpose: purpose of this activiy is to hold the detail view of an item clicked from the listView
 *          on MyActiviy.java
 */

public class DetailView extends Activity {

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

        //Find views:
        detailImage = (ImageView)findViewById(R.id.detailImage);
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailDescription = (TextView)findViewById(R.id.detailDescription);
        doneButton = (Button)findViewById(R.id.detailClose);

        //Receive intent from MyActivity:
        Intent getIntent = this.getIntent();
        detImgId = getIntent.getIntExtra("IMGID", 0);
        detTitle = getIntent.getStringExtra("TITLE");
        detDesc = getIntent.getStringExtra("DESCR");

        Log.i("DETAIL_VIEW", detImgId + " " + detTitle + " " + detDesc);

        //Set Views
        detailImage.setImageResource(detImgId);
        detailTitle.setText(detTitle);
        detailDescription.setText(detDesc);

        //Set done button onClickListener:
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
