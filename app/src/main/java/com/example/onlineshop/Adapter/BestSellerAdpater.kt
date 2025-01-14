package com.example.onlineshop.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.ViewholderBestsellersBinding
import com.example.onlineshop.model.ItemModels

class BestSellerAdpater(val items: MutableList<ItemModels>):
RecyclerView.Adapter<BestSellerAdpater.BestSellerViewHolder>(){

    inner class BestSellerViewHolder(val binding: ViewholderBestsellersBinding):
    RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val binding= ViewholderBestsellersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BestSellerViewHolder(binding)
    }

    override fun getItemCount(): Int= items.size

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.binding.texTitleItem.text = items[position].title
        holder.binding.texPrice.text = items[position].price.toString()
        holder.binding.ratingBar.rating = this.items[position].rating.toFloat()

        Glide.with(holder.itemView)
            .load(items[position].picUrl[0])
            .into(holder.binding.imageItem)

        Glide.with(holder.itemView)
            .load(items[position].logo)
            .into(holder.binding.imageMarca)

    }
}
