package com.myapp.imagezoom

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TransformableSample() {

    val interactionSource = remember { MutableInteractionSource() }
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var componentHeight = 0
    var componentWidth = 0
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
       val newScale = scale*zoomChange

        if (newScale in 1.0..4.0){
            scale = newScale
        }
        else if(newScale<1.0){
            scale = 1.0f
            offset = Offset.Zero
        }

        val actualOffset = offset +(offsetChange*scale)
        val maxX = ((componentWidth * newScale)-componentWidth)/2
        val maxY = ((componentHeight * newScale)-componentHeight)/2

        val actX = if(actualOffset.x>=0){
            actualOffset.x
        }else{
            actualOffset.x *-1
        }
        val actY = if(actualOffset.y>=0){
            actualOffset.y
        }else{
            actualOffset.y *-1
        }

        val x = if (maxX > 0 && maxX >= actX) {
            actualOffset.x
        }else{
            offset.x
        }
        val y = if (maxY > 0 && maxY >= actY) {
            actualOffset.y
        }else{
            offset.y
        }

        if(scale > 1.0f){
            offset = Offset(x,y)
        }
    }
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .pointerInput(interactionSource) {
                    detectTapGestures(
                        onDoubleTap = {
                            scale = if (scale != 1f) {
                                offset = Offset.Zero
                                1f
                            } else {
                                4f
                            }
                        },
                    )
                }
                .onGloballyPositioned {
                    componentHeight = it.size.height
                    componentWidth = it.size.width

                }
                .transformable(state = state)
                .background(Color.Blue)
                .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "test",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Yellow)

        )
    }


}

@Composable
fun ComposeWebView(){


    Box(modifier = Modifier
        // apply other transformations like rotation and zoom
        // on the pizza slice emoji
        // add transformable to listen to multitouch transformation events
        // after offset
        .background(Color.Blue)
        .fillMaxSize()){
        val mUrl = "https://www.google.com"

        // Adding a WebView inside AndroidView
        // with layout as full screen
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                getSettings().setSupportZoom(true)
                getSettings().builtInZoomControls = true
                getSettings().displayZoomControls = false
            }
        }, update = {
            it.loadUrl(mUrl)
        })
    }
    // Declare a string that contains a url

}

































interface AtMentionProvider {
    data class Params(
        val portalId: String,
        val projectId: String,
        val searchString: String,
        val isClientUserNeeded: Boolean,
        val requestType:RequestType = RequestType.BOTH
    )

    sealed class Request private constructor() {
        data class Get(val params: Params): Request()
    }
}

enum class RequestType{
    LOCAL,NETWORK,BOTH;
}


fun getConvertedParam(request:AtMentionProvider.Request.Get,requestType:RequestType):AtMentionProvider.Request{
   return request.copy(params = AtMentionProvider.Params(request.params.portalId,request.params.projectId,request.params.searchString,request.params.isClientUserNeeded,RequestType.LOCAL))
}