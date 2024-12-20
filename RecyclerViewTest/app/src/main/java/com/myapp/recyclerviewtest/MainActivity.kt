package com.myapp.recyclerviewtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mBinding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(getData())
        mBinding.rvList.adapter = adapter


    }
}

fun getData():List<String>{
    val list = mutableListOf<String>()
    for(i in 0..50){
        list.add(" $i   kjuytresxcvbnm,loiuytredsxcvbnjkloiuytrdsjhgbnmjuyhgvbnjhgsdhflksdhfklhdsfhdsfhjkldshflsdkjsdfljsdflkjdskl")
    }
    return list
}