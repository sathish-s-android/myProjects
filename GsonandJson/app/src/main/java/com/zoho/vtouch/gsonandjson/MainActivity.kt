package com.zoho.vtouch.gsonandjson

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zoho.vtouch.gsonandjson.ui.theme.GsonAndJsonTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GsonAndJsonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Log.d("Sathish_SSS 1", "onCreate: ${LocalizationUtil1.getUTCMillisFromDateTimeString()}")
                    Log.d("Sathish_SSS 2", "onCreate: ${LocalizationUtil2.getUTCMillisFromDateTimeString()}")

                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

data class Person(val name: String, val age: Int)

@Serializable
data class Person1(val name: String, val age: Int)
