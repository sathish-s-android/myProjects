package com.myapp.recyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val list: List<String>):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

//    override fun onViewAttachedToWindow(holder: MyViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        holder.textView?.setEnabled(false)
//        holder.textView?.setEnabled(true)
//    }
}

class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
    val textView:TextView? = view.findViewById(R.id.my_tv)

    init {
        textView?.isLongClickable = true
    }
    fun bind(str:String){
        textView?.isEnabled = false
        textView?.isEnabled = true
        textView?.text = str
    }
}