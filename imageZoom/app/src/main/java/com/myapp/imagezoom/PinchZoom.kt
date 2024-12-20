package com.myapp.imagezoom

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun PinchToZoomView(
    modifier: Modifier,
    imageContentDescription: String = ""
) {
    // Mutable state variables to hold scale and offset values
    var scale = remember { mutableStateOf(1f) }
    var offsetX = remember { mutableStateOf(0f) }
    var offsetY = remember { mutableStateOf(0f) }

    val minScale = 1f
    val maxScale = 4f

    // Remember the initial offset
    var initialOffset = remember { mutableStateOf(Offset(0f, 0f)) }

    // Coefficient for slowing down movement
    val slowMovement = 0.5f

    // Box composable containing the image
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    // Update scale with the zoom
                    val newScale = scale.value * zoom
                    scale.value = newScale.coerceIn(minScale, maxScale)

                    // Calculate new offsets based on zoom and pan
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val offsetXChange = (centerX - offsetX.value) * (newScale / scale.value - 1)
                    val offsetYChange = (centerY - offsetY.value) * (newScale / scale.value - 1)

                    // Calculate min and max offsets
                    val maxOffsetX = (size.width / 2) * (scale.value - 1)
                    val minOffsetX = -maxOffsetX
                    val maxOffsetY = (size.height / 2) * (scale.value - 1)
                    val minOffsetY = -maxOffsetY

                    // Update offsets while ensuring they stay within bounds
                    if (scale.value * zoom <= maxScale) {
                        offsetX.value = (offsetX.value + pan.x * scale.value * slowMovement + offsetXChange)
                            .coerceIn(minOffsetX, maxOffsetX)
                        offsetY.value = (offsetY.value + pan.y * scale.value * slowMovement + offsetYChange)
                            .coerceIn(minOffsetY, maxOffsetY)
                    }

                    // Store initial offset on pan
                    if (pan != Offset(0f, 0f) && initialOffset.value == Offset(0f, 0f)) {
                        initialOffset.value = Offset(offsetX.value, offsetY.value)
                    }
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        // Reset scale and offset on double tap
                        if (scale.value != 1f) {
                            scale.value = 1f
                            offsetX.value = initialOffset.value.x
                            offsetY.value = initialOffset.value.y
                        } else {
                            scale.value = 2f
                        }
                    }
                )
            }
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
                translationX = offsetX.value
                translationY = offsetY.value
            }
    ) {
        // Image to be displayed with pinch-to-zoom functionality
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = imageContentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}