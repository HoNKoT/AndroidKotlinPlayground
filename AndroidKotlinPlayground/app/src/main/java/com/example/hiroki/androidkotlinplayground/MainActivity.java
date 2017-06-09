package com.example.hiroki.androidkotlinplayground;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter {

        final List<RowData> list = new ArrayList<>();

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public RowData getItem(int position) {
            if (list.size() > position) {
                return list.get(position);
            } else {
                final RowData newRowData = new RowData(position);
                list.add(newRowData);
                return newRowData;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_2, null);
            }

            // get row data
            final RowData rowData = getItem(position);
            rowData.increment();

            // set title
            final TextView titleView = (TextView) convertView.findViewById(android.R.id.text1);
            titleView.setText(rowData.getTitle());

            // set sub-title
            final TextView summaryView = (TextView) convertView.findViewById(android.R.id.text2);
            final String summary = rowData.getSummary();
            if (summary == null) {
                summaryView.setVisibility(View.GONE);
            } else {
                summaryView.setVisibility(View.VISIBLE);
                summaryView.setText(rowData.getSummary());
            }

            // set background color
            convertView.setBackgroundColor(
                    position % 2 == 0
                            ? ContextCompat.getColor(getApplicationContext(), android.R.color.white)
                            : ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));

            return convertView;
        }
    }
}
