package com.example.iphonesearchapi.viewholder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iphonesearchapi.databinding.PartialItunesRowBinding

class ViewHolder(view: PartialItunesRowBinding): RecyclerView.ViewHolder(view.root){

    val textView: TextView = view.textViewtrackName

}