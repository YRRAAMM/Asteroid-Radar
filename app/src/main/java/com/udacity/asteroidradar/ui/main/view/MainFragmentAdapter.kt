package com.udacity.asteroidradar.ui.main.view

import android.annotation.SuppressLint
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.udacity.asteroidradar.models.Asteroid

class MainFragmentAdapter(private val onClickListener: OnClickListener):
    RecyclerView.Adapter<MainFragmentAdapter.AsteroidItemViewHolder>() {

    var data = listOf<Asteroid>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    // recycler the view
    override fun onBindViewHolder(holder: AsteroidItemViewHolder, position: Int) {
        val item = data[position]

    }

    // how to draw // where you inflate the view = (layout view) and return the viewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidItemViewHolder {
        TODO("Not yet implemented")
    }

    class AsteroidItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // data binding stuff.
    }

}


