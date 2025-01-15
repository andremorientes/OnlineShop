package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.ViewholderItem1Binding
import com.example.onlineshop.databinding.ViewholderItem2Binding
import com.example.onlineshop.model.ItemModels

class ListItemAdapter(val items: MutableList<ItemModels>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_ITEM1 = 0
        const val TYPE_ITEM2 = 1

    }

    private var context: Context? = null

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_ITEM1 else TYPE_ITEM2
    }

    inner class ViewHolderItem1(val binding: ViewholderItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    inner class ViewHolderItem2(val binding: ViewholderItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            TYPE_ITEM1 -> {
                val binding = ViewholderItem1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewHolderItem1(binding)

            }

            TYPE_ITEM2 -> {
                val binding = ViewholderItem2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewHolderItem2(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = items[position]

        fun bindCommonData(
            titleTxt: String,
            priceTxT: String,
            rating: Float,
            picUrl: String,
            logo: String
        ) {
            when (holder) {
                is ViewHolderItem1 -> {
                    holder.binding.texTitleItem.text = titleTxt
                    holder.binding.texPrice.text = priceTxT
                    holder.binding.ratingBar.rating = rating

                    Glide.with(holder.itemView)
                        .load(picUrl)
                        .into(holder.binding.imageItem)

                    Glide.with(holder.itemView)
                        .load(logo)
                        .into(holder.binding.imageMarca)
                }

                is ViewHolderItem2 -> {
                    holder.binding.texTitleItem.text = titleTxt
                    holder.binding.texPrice.text = priceTxT
                    holder.binding.ratingBar.rating = rating

                    Glide.with(holder.itemView)
                        .load(picUrl)
                        .into(holder.binding.imageItem)

                    Glide.with(holder.itemView)
                        .load(logo)
                        .into(holder.binding.imageMarca)
                }
            }

        }
        bindCommonData(
            item.title,
            "${item.price} USD",
            item.rating.toFloat(),
            item.picUrl[0],
            item.logo
        )


    }
}
