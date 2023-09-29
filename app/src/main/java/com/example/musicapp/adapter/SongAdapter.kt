package com.example.musicapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.baseproject.base.base_view.BaseAdapterRecyclerView
import com.example.musicapp.databinding.LayoutItemSongBinding
import com.example.musicapp.entities.Song

class SongAdapter:BaseAdapterRecyclerView<Song,LayoutItemSongBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LayoutItemSongBinding {
        return LayoutItemSongBinding.inflate(inflater,parent,false)
    }

    override fun bindData(binding: LayoutItemSongBinding, item: Song, position: Int) {
            binding.nameSong.text=item.title
            Glide.with(binding.root.context).load(item.image).into(binding.imgBackground)
    }
}