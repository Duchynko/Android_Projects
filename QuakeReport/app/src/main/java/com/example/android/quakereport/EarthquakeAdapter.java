package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jakub on 10/13/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView; // Check if the existing view is being reused, otherwise inflate the view
        Earthquake currentEarthquake = getItem(position); //Current Earthquake object, located at this position

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Update TextView
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText((int) currentEarthquake.getMagnitude());

        //Update TextView
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquake.getLocation());

        //Update TextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
