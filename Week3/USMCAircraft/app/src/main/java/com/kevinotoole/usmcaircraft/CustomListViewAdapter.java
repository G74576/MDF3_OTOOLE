package com.kevinotoole.usmcaircraft;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: CustomListViewAdapter.java
 * Purpose:
 */

public class CustomListViewAdapter extends ArrayAdapter<AircraftInfo> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId, List<AircraftInfo> items){
        super(context, resourceId, items);
        this.context = context;
    }

    //Create View Holder:
    private class ViewHolder{
        ImageView imageView;
        TextView titleText;
        TextView descText;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        AircraftInfo aircraftInfo = getItem(position);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.aircraftImage);
            holder.titleText = (TextView) convertView.findViewById(R.id.aircraftTitle);
            holder.descText = (TextView) convertView.findViewById(R.id.aircraftDescription);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();

            holder.imageView.setImageResource(aircraftInfo.getImageId());
            holder.titleText.setText(aircraftInfo.getTitle());
            holder.descText.setText(aircraftInfo.getDescription());


        }
        return convertView;
    }
}
