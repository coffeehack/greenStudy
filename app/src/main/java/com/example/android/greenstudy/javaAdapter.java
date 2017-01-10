package com.example.android.greenstudy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aniket sharma on 10-01-2017.
 */

public class javaAdapter extends ArrayAdapter<Cases> {

    public javaAdapter(Activity context, ArrayList<Cases> case1) {

        super(context, 0, case1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.caselist, parent, false);
        }


        Cases currenttask= getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.caselist);

        nameTextView.setText(currenttask.gettitle());

        return listItemView;
    }
}
