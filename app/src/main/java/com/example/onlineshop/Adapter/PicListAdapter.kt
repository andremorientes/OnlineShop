package com.example.onlineshop.Adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.ViewholderPicListBinding

class PicListAdapter(val items:MutableList<String>, var picMain: ImageView):
RecyclerView.Adapter<PicListAdapter.PicListViewHolder>(){

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit  var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicListAdapter.PicListViewHolder {
        context = parent.context
        val binding = ViewholderPicListBinding.inflate(android.view.LayoutInflater.from(parent.context),parent,false)
        return PicListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicListAdapter.PicListViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position])
            .into(holder.binding.picList)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition= selectedPosition
            selectedPosition= position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)


            Glide.with(context)
                .load(items[position])
                .into(picMain)
        }
    }

    override fun getItemCount(): Int = items.size

    class PicListViewHolder(val binding:ViewholderPicListBinding) :
        RecyclerView.ViewHolder(binding.root)


}