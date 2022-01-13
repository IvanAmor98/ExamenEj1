package com.example.examenej1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AirportListAdatper extends ArrayAdapter<AirportDTO> {

    private final int resourceLayout;
    private final Context mContext;
    private final List<AirportDTO> items;

    public AirportListAdatper(Context context, int resource, List<AirportDTO> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.items = items;
    }

    @Override
    public View getDropDownView(int position, View convertedView, ViewGroup parent) {
        return createCustomLine(position, convertedView, parent);
    }

    public View createCustomLine(int position, View convertedView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View line = inflater.inflate(R.layout.spinner_row, parent, false);

        ((TextView) line.findViewById(R.id.labelListName)).setText(items.get(position).getName());
        ((TextView) line.findViewById(R.id.labelListId)).setText("" + items.get(position).getId());

        return line;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        AirportDTO airportDTO = getItem(position);

        if (airportDTO != null) {
            TextView name = v.findViewById(R.id.labelListName);
            TextView id = v.findViewById(R.id.labelListId);

            if (name != null) {
                name.setText(airportDTO.getName());
            }

            if (id != null) {
                id.setText("" + airportDTO.getId());
            }
        }

        return v;
    }
}