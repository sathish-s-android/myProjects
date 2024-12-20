package com.myapp.sheetviewsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.sheetviewsample.ui.theme.SheetViewSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SheetViewSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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

//val sharedScrollPosition = remember { mutableStateOf(0f) }
//
//val lazyListState1 = rememberLazyListState()
//val lazyListState2 = rememberLazyListState()
//
//LazyColumn(state = lazyListState1) {
//    // Your items
//}
//
//LazyColumn(state = lazyListState2) {
//    // Your items
//}
//
//lazyListState1.snapshotFlow {
//    sharedScrollPosition.value = it.firstVisibleItemIndex.toFloat() // Adjust as needed
//}
//
//lazyListState2.snapshotFlow {
//    sharedScrollPosition.value = it.firstVisibleItemIndex.toFloat() // Adjust as needed
//}

@Composable
fun MyLazyRow(list:List<String>){
    val lazyListState = rememberLazyListState()
    LazyRow(state = lazyListState) {
        items(items = list){
            Text(text = it,
                modifier = Modifier.width(150.dp).height(150.dp))
        }
    }
}
