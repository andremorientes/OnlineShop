package com.example.onlineshop.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderSizeBinding

class SizeListAdapter(val items: MutableList<String>):
RecyclerView.Adapter<SizeListAdapter.SizeListViewHolder>(){

    private var selectedPosition= -1
    private var lastSelectedPosition= -1
     private lateinit var context: Context
    inner class SizeListViewHolder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListViewHolder {
        context= parent.context
        val binding= ViewholderSizeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SizeListViewHolder(binding)
    }

    override fun getItemCount(): Int= items.size


    override fun onBindViewHolder(holder: SizeListAdapter.SizeListViewHolder, @SuppressLint("RecyclerView") position : Int) {
        holder.binding.tvSize.text= items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition= selectedPosition
            selectedPosition= position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)


        }
        if (selectedPosition== position){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.orange_bg_size)
            holder.binding.tvSize.setTextColor(context.resources.getColor(R.color.white))

        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.orange_circle_size)
            holder.binding.tvSize.setTextColor(context.resources.getColor(R.color.black))

        }
    }

}