package com.kevinotoole.usmcaircraft;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: WidgetConfigurationActivity.java
 * Purpose: the purpose of this activity is to run a configuration of the widget giving the user the
 *          option to choose the background color of the widget.
 */

public class WidgetConfigureActivity extends Activity implements View.OnClickListener {

    //Arrays to hold aircraft information:
    public static final Integer[] images = {R.drawable.hornet, R.drawable.harrier, R.drawable.prowler, R.drawable.f5, R.drawable.hercules,
            R.drawable.skytrain, R.drawable.huron, R.drawable.gulfstream, R.drawable.citation, R.drawable.cobra,
            R.drawable.huey, R.drawable.seaknight, R.drawable.superstallion, R.drawable.osprey};

    public static final String[] titles = new String[]{"F/A-18C/D", "AV-8B", "EA-6B", "F-5", "KC-130J/T", "C-9B", "C-12B/F",
            "C-20G", "UC-35C/D", "AH-1W", "UH-1N", "CH-46E", "CH-53E", "MV-22B"};

    public static final String[] descriptions = new String[] {"Hornet", "Harrier", "Prowler", "F5", "Hercules", "Skytrain II", "Huron",
            "Gulfstream IV", "Citation/Encore", "Super Cobra", "Twin Huey", "Sea Knight",
            "Super Stallion", "Osprey"};

    //Global Variables:
    public String tit;
    public String des;
    public  int imgid;
    Random imgRandom;

    ArrayList<AircraftInfo> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_config);

        Button scarletButton = (Button) this.findViewById(R.id.scarletBtn);
        scarletButton.setOnClickListener(this);

        Button goldButton = (Button) this.findViewById(R.id.goldBtn);
        goldButton.setOnClickListener(this);

        arrayList = new ArrayList<AircraftInfo>();
        for (int i=0; i<images.length; i++) {
            AircraftInfo info = new AircraftInfo(images[i], titles[i], descriptions[i]);
            arrayList.add(info);
        }

        //Set random image for widget when created:
        imgRandom = new Random();
        int index = imgRandom.nextInt(arrayList.size());
        AircraftInfo item = arrayList.get(index);
        imgid = item.getImageId();
        tit = item.getTitle();
        des = item.getDescription();

        Intent intent = new Intent(this, WidgetDetailView.class);
        intent.putExtra("IMG", imgid);
        intent.putExtra("TIT", tit);
        intent.putExtra("DES", des);

        Log.i("PUT_INT", imgid + " " + tit + " " + des);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //To change background color to scarlet:
            case R.id.scarletBtn:

                Bundle extras = getIntent().getExtras();

                if (extras != null) {

                    int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

                    if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {

                        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
                        rv.setInt(R.id.widget, "setBackgroundColor", Color.parseColor("#BA0000"));
                        rv.setInt(R.id.widgetPrevious, "setBackgroundColor", Color.parseColor("#BA0000"));
                        rv.setInt(R.id.widgetNext, "setBackgroundColor", Color.parseColor("#BA0000"));

                        Intent buttonIntent = new Intent(view.getContext(), WidgetDetailView.class);
//                        String titl, descp;
//                        int img;
//                        img = imgid;
//                        titl = tit;
//                        descp = des;
//                        buttonIntent.putExtra("IMG", img);
//                        buttonIntent.putExtra("TIT", titl);
//                        buttonIntent.putExtra("DES", descp);

                        PendingIntent pi = PendingIntent.getActivity(this, 0, buttonIntent, 0);

                        rv.setOnClickPendingIntent(R.id.widgetImage, pi);
                        rv.setImageViewResource(R.id.widgetImage, imgid);
                        rv.setTextViewText(R.id.widgetTitle, tit);

                        AppWidgetManager.getInstance(this).updateAppWidget(widgetId, rv);

                        Intent resultValue = new Intent();
                        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        setResult(RESULT_OK, resultValue);
                        finish();

                    }
                }

                break;

            //To change background color to gold:
            case R.id.goldBtn:
                Bundle extras1 = getIntent().getExtras();

                if (extras1 != null) {

                    int widgetId = extras1.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

                    if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {

                        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
                        rv.setInt(R.id.widget, "setBackgroundColor", Color.parseColor("#FFFF33"));
                        rv.setInt(R.id.widgetPrevious, "setBackgroundColor", Color.parseColor("#FFFF33"));
                        rv.setInt(R.id.widgetNext, "setBackgroundColor", Color.parseColor("#FFFF33"));
                        rv.setTextColor(R.id.widgetTitle, Color.parseColor("#BA0000"));

                        Intent buttonIntent = new Intent(view.getContext(), WidgetDetailView.class);
//                        String titl, descp;
//                        int img;
//                        img = imgid;
//                        titl = tit;
//                        descp = des;
//                        buttonIntent.putExtra("IMG", img);
//                        buttonIntent.putExtra("TIT", titl);
//                        buttonIntent.putExtra("DES", descp);

                        PendingIntent pi = PendingIntent.getActivity(this, 0, buttonIntent, 0);

                        rv.setOnClickPendingIntent(R.id.widgetImage, pi);
                        rv.setImageViewResource(R.id.widgetImage, imgid);
                        rv.setTextViewText(R.id.widgetTitle, tit);

                        AppWidgetManager.getInstance(this).updateAppWidget(widgetId, rv);

                        Intent resultValue = new Intent();
                        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        setResult(RESULT_OK, resultValue);
                        finish();

                    }
                }

                break;
        }
    }
}
