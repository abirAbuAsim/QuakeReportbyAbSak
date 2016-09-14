package com.trynlearn.android.quakereportbyabsak;
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
* {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link Earthquake} objects.
* */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_value);
        // Get the version name from the current Earthquake object and
        // set this text on the name TextView
        DecimalFormat formatter = new DecimalFormat("0.0");
        String mag = formatter.format(currentEarthquake.getMagValue());
        magTextView.setText(mag);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView offsetLocTextView = (TextView) listItemView.findViewById(R.id.offset_location);
        TextView primaryLocTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        String fullLocation = currentEarthquake.getFullLocation();
        String offsetLocation = "";
        String primaryLocation = "";
        if(fullLocation.contains("of")){
            String[] strArray = fullLocation.split("of");
            offsetLocation = strArray[0] + " of";
            primaryLocation = strArray[1];
        } else {
            primaryLocation = fullLocation;
        }
        // Get the version number from the current Earthquake object and
        // set this text on the number TextView
        offsetLocTextView.setText(offsetLocation);
        primaryLocTextView.setText(primaryLocation);


        // formatting the time to display with style
        long dateInMilliseconds = currentEarthquake.getDateValue();
        Date dateObject = new Date(dateInMilliseconds);
        //SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = formatDate(dateObject);
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_value);
        // Get the image resource ID from the current Earthquake object and
        // set the image to dateTextView
        dateTextView.setText(dateToDisplay);

        String timeToDisplay = formatTime(dateObject);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_value);
        timeTextView.setText(timeToDisplay);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


}