package com.enterz.intuneplayapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat.startActivityForResult
import com.enterz.intuneplayapp.ui.theme.IntunePlayAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntunePlayAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        ::openChooser
                    )
                }
            }
        }
    }
    fun openChooser(){
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFile.setType("*/*")
        startActivityForResult(
            this,
            Intent.createChooser(chooseFile, "Choose a file"),
            2001,null
        )
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,block:()->Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = block)
        { Text("GET FILE") }
    }
}

