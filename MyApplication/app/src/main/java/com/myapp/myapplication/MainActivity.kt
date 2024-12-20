package com.myapp.myapplication

import android.icu.util.Currency
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Time

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_hello).setOnClickListener {
            DownloadManagerHelper(this).downloadFileToInternalStorage("Sathish_${System.currentTimeMillis()}.png")
        }
    }
}