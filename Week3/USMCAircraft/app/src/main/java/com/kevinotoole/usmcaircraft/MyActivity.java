package com.kevinotoole.usmcaircraft;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: MyActivity.java
 * Purpose: 
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MyActivity extends Activity implements AdapterView.OnItemClickListener{

    //Arrays to hold aircraft information:
    public static final Integer[] images = {R.drawable.hornet, R.drawable.harrier, R.drawable.prowler, R.drawable.f5, R.drawable.hercules,
                                    R.drawable.skytrain, R.drawable.huron, R.drawable.gulfstream, R.drawable.citation, R.drawable.cobra,
                                    R.drawable.huey, R.drawable.seaknight, R.drawable.superstallion, R.drawable.osprey};

    public static final String[] titles = new String[]{"F/A-18C/D", "AV-8B", "EA-6B", "F-5", "KC-130J/T", "C-9B", "C-12B/F",
                                                        "C-20G", "UC-35C/D", "AH-1W", "UH-1N", "CH-46E", "CH-53E", "MV-22B"};

    public static final String[] descriptions = new String[] {"Hornet", "Harrier", "Prowler", "F5", "Hercules", "Skytrain II", "Huron",
                                                        "Gulfstream IV", "Citation/Encore", "Super Cobra", "Twin Huey", "Sea Knight",
                                                        "Super Stallion", "Osprey"};

    public static ListView listView;
    public static CustomListViewAdapter adapter;
    ArrayList<AircraftInfo> aircraftInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Set Arraylist with aircraft information
        aircraftInfo = new ArrayList<AircraftInfo>();
        for (int i=0; i<images.length; i++){
            AircraftInfo info = new AircraftInfo(images[i], titles[i], descriptions[i]);
            aircraftInfo.add(info);
        }

        //Set listview and adapter:
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListViewAdapter(this, R.layout.list_item, aircraftInfo);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    //Onclick Listener for listview:
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String title, descr;
            int imgid;
            imgid = aircraftInfo.get(position).getImageId();
            title = aircraftInfo.get(position).getTitle();
            descr = aircraftInfo.get(position).getDescription();

        Log.i("AIRCRAFT_DETAIL", imgid + " " + title + " " + descr);

            //Intent to pass information to detailView:
            Intent detailView = new Intent(MyActivity.this, DetailView.class);
            detailView.putExtra("IMGID", imgid);
            detailView.putExtra("TITLE", title);
            detailView.putExtra("DESCR", descr);
            startActivityForResult(detailView, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
