package com.easyapps.example.testlistc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyapps.example.testlistc.databinding.ItemViewBinding
import com.easyapps.example.testlistc.db.JokeEntity
import javax.inject.Inject

class JokeAdapter @Inject constructor() : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    private val jokeList = mutableListOf<JokeEntity>()

    fun submitList(list: List<JokeEntity>) {
        jokeList.clear()
        jokeList.addAll(list)
        notifyDataSetChanged() // List Adapter can be used
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .let { return JokeViewHolder(it) }
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokeList[position])
    }

    class JokeViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(jokeEntity: JokeEntity) {
            binding.jokeString.text = jokeEntity.joke
            binding.jokeFav.text = "fav:" + jokeEntity.fav.toString()
        }


    }


}
