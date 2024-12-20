package com.example.rvdragdrop

object DataUtil {

    val DataList = getList()

    private fun getList():List<String>{
        val list = mutableListOf<String>()
        for (i in 0..30){
            list.add("Sathish $i")
        }
        return list
    }

}