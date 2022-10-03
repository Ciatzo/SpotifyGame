package com.cianjansen.spotifygame.ui.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cianjansen.spotifygame.data.model.Artist
import com.cianjansen.spotifygame.databinding.ItemLayoutBinding


class MainAdapter(
    private val artists: ArrayList<Artist>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(artist: Artist) {
            itemBinding.artistName.text = artist.name
            itemBinding.artistHref.text = artist.href
            Glide.with(itemBinding.root)
                .load(artist.image)
                .into(itemBinding.artistImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(artists[position])

    }


    fun addData(list: List<Artist>) {
        artists.addAll(list)
    }

}