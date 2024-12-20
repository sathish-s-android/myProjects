package com.example.rvdragdrop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RvAdapter():RecyclerView.Adapter<DragViewHolder>() {

    private val differCallBack  = object : DiffUtil.ItemCallback<String>()
    {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem.contentEquals(newItem)
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem.contentEquals(newItem)
        }


    }

    private val differ = AsyncListDiffer(this, differCallBack)

    fun swap(list:List<String>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragViewHolder {
        return DragViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DragViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}

class DragViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val tv = view.findViewById<TextView>(R.id.item_name)

    fun bindItem(string: String){
        tv.text = string
    }
}