package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.Helper.ChangeNumberItemsListener
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.databinding.ViewholderCartBinding
import com.example.onlineshop.model.ItemModels



class CartAdapter(
    private val listItemSelected:ArrayList<ItemModels>,
    context : Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val managmentCart = ManagmentCart(context)

   inner class CartViewHolder(val binding: ViewholderCartBinding):
        RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val binding= ViewholderCartBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = listItemSelected.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       val item = listItemSelected[position]

        holder.binding.tvTitle.text= item.title
        holder.binding.feeEachItem.text= "$ ${item.price}"
        holder.binding.totalEachItem.text= "$ ${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItem.text= item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.imagePic)

        holder.binding.btnPlus.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position,object : ChangeNumberItemsListener{

                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

        holder.binding.btnMinus.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position,object : ChangeNumberItemsListener{

                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }
}