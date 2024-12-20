package com.myapp.recyclerviewtest


import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomTV @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        isEnabled = false
//        isEnabled = true
//    }

}