package com.example.iphonesearchapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iphonesearchapi.databinding.PartialItunesRowBinding

class ItunesAdapter( private var itunesList: ArrayList<String>) :
    RecyclerView.Adapter<ItunesAdapter.ViewHolder>() {


    class ViewHolder(view:PartialItunesRowBinding): RecyclerView.ViewHolder(view.root){

        val textView: TextView = view.textViewtrackName

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PartialItunesRowBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itunesList[position]
    }

    override fun getItemCount(): Int =  itunesList.size



}