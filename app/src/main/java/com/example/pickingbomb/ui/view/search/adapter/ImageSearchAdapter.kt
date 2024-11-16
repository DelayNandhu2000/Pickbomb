package com.example.pickingbomb.ui.view.search.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems

class ImageSearchAdapter:RecyclerView.Adapter<ImageSearchViewHolder>(){


    var item = listOf<ImageSearchItems>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        return when(viewType){

            else -> {
                throw IllegalArgumentException("Invalid Layout")

            }
        }
    }

    override fun onBindViewHolder(holder: ImageSearchViewHolder, position: Int) {
        when(holder){

            else -> {}
        }
    }

    override fun getItemCount() = item.size

    override fun getItemViewType(position: Int): Int {
        val item = item[position]
        return when(item){
            else -> {
                throw IllegalArgumentException("Invalid Class")
            }
        }
    }



}