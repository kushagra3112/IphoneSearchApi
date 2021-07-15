package com.example.iphonesearchapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iphonesearchapi.databinding.PartialItunesRowBinding
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.viewholder.ViewHolder

class ItunesAdapter(var itunesList: List<Result>) :
    RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PartialItunesRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = itunesList[position]
        holder.textView.text = value.trackName
    }

    override fun getItemCount(): Int = itunesList.size
    fun resetDataSource(it: List<Result>) {
        itunesList = it
        notifyDataSetChanged()
    }


}