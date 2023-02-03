package com.udacity.asteroidradar.ui.main.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.asteroidRepository.database.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class MainFragmentAdapter(private val onClickListener: OnClickListener):
    ListAdapter<Asteroid, MainFragmentAdapter.AsteroidItemViewHolder>(DiffCallback) {

//    override fun getItemCount() = TODO("Not yet implemented")

    // recycler the view
    override fun onBindViewHolder(holder: AsteroidItemViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    // how to draw // where you inflate the view = (layout view) and return the viewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidItemViewHolder {
        return AsteroidItemViewHolder(AsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class AsteroidItemViewHolder(private val itmView: AsteroidItemBinding):
        RecyclerView.ViewHolder(itmView.root) {
        // data binding stuff.
        fun bind(asteroid: Asteroid) {
            itmView.asteroid = asteroid
            itmView.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (asteroid:Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

    }
}


