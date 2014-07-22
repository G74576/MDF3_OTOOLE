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
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity implements AdapterView.OnItemClickListener{

    public static Resources res;

    //Arrays to hold aircraft information:
    public static final Integer[] images = {R.drawable.hornet, R.drawable.harrier, R.drawable.prowler, R.drawable.f5, R.drawable.hercules,
                                    R.drawable.skytrain, R.drawable.huron, R.drawable.gulfstream, R.drawable.citation, R.drawable.cobra,
                                    R.drawable.huey, R.drawable.seaknight, R.drawable.superstallion, R.drawable.osprey};

    public static final String[] titles = new String[]{"F/A-18C/D", "AV-8B", "EA-6B", "F-5", "KC-130J/T", "C-9B", "C-12B/F",
                                                        "C-20G", "UC-35C/D", "AH-1W", "UH-1N", "CH-46E", "CH-53E", "MV-22B"};

    public static final String[] descriptions = new String[] {"Hornet", "Harrier", "Prowler", "F5", "Hercules", "Skytrain II", "Huron",
                                                        "Gulfstream IV", "Citation/Encore", "Super Cobra", "Twin Huey", "Sea Knight",
                                                        "Super Stallion", "Osprey"};
//    public static final String[] titles = new String[] {res.getString(R.string.hornet), res.getString(R.string.harrier), res.getString(R.string.prowler),
//                                    res.getString(R.string.f5), res.getString(R.string.hercules), res.getString(R.string.sky), res.getString(R.string.huron),
//                                    res.getString(R.string.gulf), res.getString(R.string.citation), res.getString(R.string.cobra), res.getString(R.string.huey),
//                                    res.getString(R.string.sea), res.getString(R.string.stallion), res.getString(R.string.osprey)};
//
//    public static final String[] descriptions = new String[] {res.getString(R.string.hornet1), res.getString(R.string.harrier1), res.getString(R.string.prowler1),
//                                    res.getString(R.string.f51), res.getString(R.string.hercules1), res.getString(R.string.sky1), res.getString(R.string.huron1),
//                                    res.getString(R.string.gulf1), res.getString(R.string.citation1), res.getString(R.string.cobra1), res.getString(R.string.huey1),
//                                    res.getString(R.string.sea1), res.getString(R.string.stallion1), res.getString(R.string.osprey1)};

    ListView listView;
    ArrayList<AircraftInfo> aircraftInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        aircraftInfo = new ArrayList<AircraftInfo>();
        for (int i=0; i<images.length; i++){
            AircraftInfo info = new AircraftInfo(images[i], titles[i], descriptions[i]);
            aircraftInfo.add(info);
        }

        listView = (ListView) findViewById(R.id.listView);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.list_item, aircraftInfo);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(), "AC" + (position + 1) + ":" + aircraftInfo.get(position), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
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
