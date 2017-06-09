package com.example.hiroki.androidkotlinplayground

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById(R.id.list) as ListView
        listView.adapter = CustomAdapter()
    }

    private inner class CustomAdapter : BaseAdapter() {

        internal val list: MutableList<RowData> = ArrayList()

        override fun getCount(): Int {
            return 50
        }

        override fun getItem(position: Int): RowData {
            if (list.size > position) {
                return list[position]
            } else {
                val newRowData = RowData(position)
                list.add(newRowData)
                return newRowData
            }
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater
                        .inflate(android.R.layout.simple_list_item_2, null)
            }

            // get row data
            val rowData = getItem(position)
            rowData.increment()

            // set title
            val titleView = convertView!!.findViewById(android.R.id.text1) as TextView
            titleView.text = rowData.getTitle()

            // set sub-title
            val summaryView = convertView.findViewById(android.R.id.text2) as TextView
            val summary = rowData.summary
            if (summary == null) {
                summaryView.visibility = View.GONE
            } else {
                summaryView.visibility = View.VISIBLE
                summaryView.text = rowData.summary
            }

            // set background color
            convertView.setBackgroundColor(
                    if (position % 2 == 0)
                        ContextCompat.getColor(applicationContext, android.R.color.white)
                    else
                        ContextCompat.getColor(applicationContext, android.R.color.holo_green_light))

            return convertView
        }
    }
}
