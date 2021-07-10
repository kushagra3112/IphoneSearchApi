package com.example.iphonesearchapi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.iphonesearchapi.R

class ItunesAdapter(private var context: Context, private var itunesList: ArrayList<String>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return itunesList.size
    }

    override fun getItem(p0: Int): Any {
        return itunesList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val item = this.itunesList[p0]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = inflator.inflate(R.layout.partial_itunes_row, null)
        itemView.findViewById<TextView>(R.id.textView2).text = item
        return itemView
    }

}