package com.myapp.lifecyclecheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_h).setOnClickListener {
            Intent(this,MainActivity2::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onPause() {
        Log.d("Sathish_SSS", "onPause: MainActivity")
        super.onPause()
    }
}