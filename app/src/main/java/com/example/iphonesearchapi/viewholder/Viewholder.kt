package com.example.iphonesearchapi.viewholder

import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.iphonesearchapi.databinding.PartialItunesRowBinding

class ViewHolder(view: PartialItunesRowBinding): RecyclerView.ViewHolder(view.root){

    val textView: TextView = view.textViewtrackName
    val isfav:ImageView=view.imageViewFavourite

}