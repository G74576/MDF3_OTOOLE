package com.kevinotoole.usmcaircraft;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: AircraftWidgetProvider.java
 * Purpose:
 */

public class AircraftWidgetProvider extends AppWidgetProvider {


    //Arrays to hold aircraft information:
    public static final Integer[] images = {R.drawable.hornet, R.drawable.harrier, R.drawable.prowler, R.drawable.f5, R.drawable.hercules,
            R.drawable.skytrain, R.drawable.huron, R.drawable.gulfstream, R.drawable.citation, R.drawable.cobra,
            R.drawable.huey, R.drawable.seaknight, R.drawable.superstallion, R.drawable.osprey};

    public static final String[] titles = new String[]{"F/A-18C/D", "AV-8B", "EA-6B", "F-5", "KC-130J/T", "C-9B", "C-12B/F",
            "C-20G", "UC-35C/D", "AH-1W", "UH-1N", "CH-46E", "CH-53E", "MV-22B"};

    public static final String[] descriptions = new String[] {"Hornet", "Harrier", "Prowler", "F5", "Hercules", "Skytrain II", "Huron",
            "Gulfstream IV", "Citation/Encore", "Super Cobra", "Twin Huey", "Sea Knight",
            "Super Stallion", "Osprey"};

    public String tit;
    public String des;
    public int imgid;
    Random imgRandom;

    ArrayList<AircraftInfo> arrayList;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        arrayList = new ArrayList<AircraftInfo>();
        for (int i=0; i<images.length; i++) {
            AircraftInfo info = new AircraftInfo(images[i], titles[i], descriptions[i]);
            arrayList.add(info);
        }

        imgRandom = new Random();
        int index = imgRandom.nextInt(arrayList.size());
        AircraftInfo item = arrayList.get(index);
        imgid = item.getImageId();
        tit = item.getTitle();
        des = item.getDescription();

        Intent wiIntent = new Intent(context, WidgetDetailView.class);
        String titl, descp;
        int img;
        img = imgid;
        titl = tit;
        descp = des;
        wiIntent.putExtra("IMG", img);
        wiIntent.putExtra("TIT", titl);
        wiIntent.putExtra("DES", descp);

        Log.i("WIDGET_PUT_INT", img + " " + titl + " " + descp);

        for (int appWidgetId : appWidgetIds) {


            Intent intent = new Intent(context, WidgetDetailView.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            remoteViews.setImageViewResource(R.id.widgetImage, imgid);
            remoteViews.setOnClickPendingIntent(R.id.widgetImage, pendingIntent);
            remoteViews.setTextViewText(R.id.widgetTitle, tit);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {

    }
}
