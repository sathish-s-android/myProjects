package com.myapp.behindonclicklistener

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.behindonclicklistener.ui.theme.BehindOnClickListenerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BehindOnClickListenerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        Greeting1(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(modifier= modifier
        .height(150.dp)
        .width(200.dp)
        .background(
            Color.Red.copy(alpha = 0.5f)
        )) {

        Box(modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .clickable {
                Log.d("Sathish_SSS", "Greeting: clicked")
            }) {

        }

        LazyRow(modifier= modifier
            .fillMaxSize()
            .background(
                Color.Green.copy(alpha = 0.5f)
            )) {

        }

    }
}

@Composable
fun Greeting1(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(150.dp)
            .width(200.dp)
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Green.copy(alpha = 0.5f))
        ) {
            // Handle scrolling (optional)

            items(items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 0", "Item 1", "Item 2", "Item 3", "Item 4")) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(8.dp)
                        .clickable {
                            // Handle click on individual items in the LazyRow
                            Log.d("Sathish_SSS", "LazyRow item '$item' clicked")
                        }
                )
            }
        }
        // Clickable box with white background, full size
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .clickable(onClick = {
                    // Handle click on the entire box
                    Log.d("Sathish_SSS", "Greeting box clicked")
                })
        ) {
            // Content for the clickable box (optional)
            Text(text = "Click me!", modifier = Modifier.align(Alignment.Center))
        }

        // LazyRow nested within the box, filling the remaining space

    }
}