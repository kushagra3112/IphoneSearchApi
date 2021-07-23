package com.example.iphonesearchapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.iphonesearchapi.database.SongDatabaseDao
import com.example.iphonesearchapi.databinding.PartialItunesRowBinding
import com.example.iphonesearchapi.fragments.SearchFragmentDirections
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.viewholder.ViewHolder

class ItunesAdapter(var itunesList: MutableList<Result>) :
    RecyclerView.Adapter<ViewHolder>() {
    var favlist: List<Long> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PartialItunesRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = itunesList[position]
        val isFavorite = favlist.contains(value.trackId.toLong())

        holder.textView.text = value.trackName
        holder.isfav.visibility = if (isFavorite) View.VISIBLE else View.INVISIBLE
        holder.textView.setOnClickListener { view ->


            view.findNavController()?.navigate(
                SearchFragmentDirections.actionSearchFragment2ToDetailFragment(
                    value.trackId,
                    value.trackName
                )
            )
        }
    }

    override fun getItemCount(): Int = itunesList.size
    fun resetDataSource(it: MutableList<Result>) {
        itunesList.clear()
        itunesList = it
        notifyDataSetChanged()
    }

    fun setfavlist(it: List<Long>) {
        favlist = it

    }


}