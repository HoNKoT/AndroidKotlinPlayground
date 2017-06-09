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

        (findViewById(R.id.list) as ListView).adapter = CustomAdapter()
    }

    private inner class CustomAdapter : BaseAdapter() {

        internal val list: MutableList<RowData> = ArrayList()

        override fun getCount(): Int = 50

        override fun getItem(position: Int): RowData =
                if (list.size > position) {
                    list[position]
                } else {
                    val newRowData = RowData(position)
                    list.add(newRowData)
                    newRowData
                }

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: layoutInflater.inflate(android.R.layout.simple_list_item_2, null)

            // get row data
            val rowData = getItem(position)
            rowData.increment()

            // view set up
            view.apply {
                // set title
                (findViewById(android.R.id.text1) as TextView).text = rowData.getTitle()

                // set sub-title
                (findViewById(android.R.id.text2) as TextView).apply {
                    visibility = rowData.summary?.let { View.VISIBLE } ?: View.GONE
                    text = rowData.summary?.let { it }
                }

                // set background color
                setBackgroundColor(ContextCompat.getColor(applicationContext,
                        if (position % 2 == 0) android.R.color.white else android.R.color.holo_green_light))
            }

            return view
        }
    }
}
