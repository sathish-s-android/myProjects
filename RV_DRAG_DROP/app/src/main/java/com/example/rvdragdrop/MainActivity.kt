package com.example.rvdragdrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvdragdrop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding
    private val mAdapter:RvAdapter = RvAdapter()

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = getTouchHelper(::swapItems)
        ItemTouchHelper(simpleItemTouchCallback)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpRv()
        setUpInitialData()
    }


    private fun setUpRv(){
        mBinding.rvList.adapter = mAdapter
        mBinding.rvList.layoutManager = LinearLayoutManager(this)
        itemTouchHelper.attachToRecyclerView(mBinding.rvList)
    }

    private fun setUpInitialData(){
        mAdapter.swap(DataUtil.DataList)
    }

    private fun swapItems(from:Int,to:Int){

    }

}