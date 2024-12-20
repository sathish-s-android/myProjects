package com.myapp.composeshadow

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapp.composeshadow.ui.theme.ComposeShadowTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state: MutableState<Boolean> = remember {
                mutableStateOf(false)
            }
            val shState: MutableState<Int> = remember {
                mutableIntStateOf(0)
            }
            val statef: MutableState<Float> = remember {
                mutableFloatStateOf(1f)
            }
            ComposeShadowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        state,
                        statef
                    )
                }
            }

//            CoroutineScope(Dispatchers.IO).launch {
//                delay(2000)
//                withContext(Dispatchers.Main) {
//                    state.value = true
//                    for (i in 0..20) {
//                        delay(20)
//                        shState.value = i
//                    }
//                }
//            }
//
            CoroutineScope(Dispatchers.IO).launch {
                delay(2000)
                withContext(Dispatchers.Main) {
                    for (i in 0..100) {
                        delay(50)
                        statef.value = i.toFloat()/100f
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    isNeedToClip: State<Boolean>,
    state: State<Float>
) {
    Box(modifier = modifier
        .background(Color.White)
        .fillMaxWidth()
        .fillMaxHeight()
        .layout { measurable, constraints ->

            val placable = measurable.measure(constraints)

            layout(placable.width, placable.height) {
                placable.placeRelative(70.dp.roundToPx(), 100.dp.roundToPx())
            }
        }) {

//        ClippedShadowButton(modifier,"sathish",R.drawable.ic_launcher_foreground,{})
//        ShadowBox(name, modifier, isNeedToClip, state)

//        ElevatedBackgroundWithCutout(modifier)

        ElC(state)

    }

}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ElC(state: State<Float>){
    val shadow = remember {
        GenericShape { size, _ ->
            lineTo(Float.MAX_VALUE, 0f)
            lineTo(Float.MAX_VALUE, size.height)
            lineTo(0f, size.height)
        }
    }
    Column {

        BottomShadow(state)
        BottomShadow(state)
        BottomShadow(state)

////        for(i in 0..2){
//            Surface (
//                modifier = Modifier
////                    .padding(16.dp)
//                    .height(50.dp)
//                    .width(150.dp)
////                    .clip(
//////                    if (isNeedToClip.value) {
////                        shadow
//////                    } else {
//////                        shape
//////                    }
////                    ),
//                        ,
////                shape = RectangleShape,
////                tonalElevation = 20.dp,
//                shadowElevation = 20.dp,
//            ) {
//                Text(text = "Elevated Surface")
//            }
////        }
    }


}

@Composable
fun BottomShadow(alpha: State<Float>) {
    Box(modifier = Modifier
//        .alpha(alpha.value)
        .height(50.dp)
        .width(50.dp)
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = alpha.value),
                    Color.Transparent,
                )
            )
        )
    )
}


@Composable
fun ShadowBox(
    name: String,
    modifier: Modifier = Modifier,
    isNeedToClip: State<Boolean>,
    state: State<Int>
) {
    val shape = remember {
        RoundedCornerShape(size = 0.dp)
    }
    val shadow = remember {
        GenericShape { size, _ ->
            lineTo(Float.MAX_VALUE, 0f)
            lineTo(Float.MAX_VALUE, size.height)
            lineTo(0f, size.height)
        }
    }

    Column {
        for(i in 0..2){
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(
                        if (isNeedToClip.value) {
                            shadow
                        } else {
                            shape
                        }
                    )
                    .shadow(state.value.dp, ambientColor = Color.Black, spotColor = Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp)
                        .background(Color.Green)
                ) {
                    Text(
                        text = "Allow only bottom shadow",
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}



