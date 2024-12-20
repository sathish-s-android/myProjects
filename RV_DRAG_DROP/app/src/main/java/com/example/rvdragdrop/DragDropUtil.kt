package com.example.rvdragdrop

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView

fun AppCompatActivity.getTouchHelper(block:(Int,Int)->Unit):ItemTouchHelper.SimpleCallback{
   return object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
       override fun onMove(recyclerView: RecyclerView,
                           viewHolder: RecyclerView.ViewHolder,
                           target: RecyclerView.ViewHolder): Boolean {
           //on move lets you check if an item has been moved from its position either up or down

           Log.d("Sathish_SSS", "onMoven from: ${viewHolder.adapterPosition}")
           Log.d("Sathish_SSS", "onMove    to: ${target.adapterPosition}")
           recyclerView.adapter?.notifyItemMoved(viewHolder.adapterPosition,target.adapterPosition)
           block(viewHolder.adapterPosition,target.adapterPosition)
           return true
       }

       override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

           //on swipe tells you when an item is swiped left or right from its position ( swipe to delete)
       }

       override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
           super.onSelectedChanged(viewHolder, actionState)

           //when an item changes its location that is currently selected this funtion is called

       }

       override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
           super.clearView(recyclerView, viewHolder)

           //when we stop dragging , swiping or moving an item this function is called
       }
    }
}