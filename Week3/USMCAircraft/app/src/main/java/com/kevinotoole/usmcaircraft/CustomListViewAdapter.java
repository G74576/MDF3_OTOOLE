package com.kevinotoole.usmcaircraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    public CustomListViewAdapter(Context context, int resourceId, ArrayList<AircraftInfo> items){
        super(context, resourceId, items);

    }

    //Create View Holder:
    private class ViewHolder{
        ImageView imageView;
        TextView titleText;
        TextView descText;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        AircraftInfo aircraftInfo = getItem(position);
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.aircraftImage);
            holder.titleText = (TextView) view.findViewById(R.id.aircraftTitle);
            holder.descText = (TextView) view.findViewById(R.id.aircraftDescription);

            holder.imageView.setImageResource(aircraftInfo.getImageId());
            holder.titleText.setText(aircraftInfo.getTitle());
            holder.descText.setText(aircraftInfo.getDescription());

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();

            holder.imageView.setImageResource(aircraftInfo.getImageId());
            holder.titleText.setText(aircraftInfo.getTitle());
            holder.descText.setText(aircraftInfo.getDescription());
        }
        return view;
    }
}
