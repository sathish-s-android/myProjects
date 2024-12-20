package com.myapp.composeshadow

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.material.Surface
import androidx.compose.ui.unit.dp

@Composable
fun ClippedShadowButton(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {

    //Outline paddings to make space for shadows
    val outlineHorizontalPadding = 20.dp
    val outlineVerticalPadding = 20.dp

    //Inner padding of button
    val innerVerticalPadding = 16.dp
    val innerHorizontalPadding = 24.dp

    //Spacer width will determine shadow width
    val spacerWidth = 24.dp
    //Extra height to make shadows bigger than the button
    val spacerExtraHeight = 4.dp
    val shadowElevation = 16.dp

    //Shadow offset from outlineHorizontalPadding
    val shadowOffsetX = 6.dp

    val buttonShape = RoundedCornerShape(size = 0.dp)
    val shadowColor = Color(0xFF707070)
    val ambientColor = Color(0xFFFEFEFE)
    val contentColor = Color(0xFF8391A8)


    val density = LocalDensity.current
    var spacerHeight by remember { mutableStateOf(value = 0.dp) }

    Box(
        modifier = modifier.wrapContentSize()
    ) {

        //Left shadow
        Spacer(
            modifier = Modifier
                .padding(
                    start = outlineHorizontalPadding - (spacerWidth / 2f) + shadowOffsetX,
                    bottom = shadowElevation
                )
                .size(width = spacerWidth, height = spacerHeight + spacerExtraHeight)
                .align(alignment = Alignment.CenterStart)
                .shadow(
                    elevation = shadowElevation,
                    shape = CircleShape,
                    ambientColor = ambientColor,
                    spotColor = shadowColor,
                    clip = true
                )
        )

//        Right shadow
        Spacer(
            modifier = Modifier
                .padding(
                    end = 20.dp,//outlineHorizontalPadding - (spacerWidth / 2f) + shadowOffsetX+10.dp,
                    bottom = shadowElevation
                )
                .size(width = spacerWidth, height = spacerHeight + spacerExtraHeight)
                .align(alignment = Alignment.CenterEnd)
                .shadow(
                    elevation = shadowElevation,
                    shape = CircleShape,
                    ambientColor = ambientColor,
                    spotColor = shadowColor
                )

        )

        //Button
        Row(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(horizontal = outlineHorizontalPadding, vertical = outlineVerticalPadding)
                .background(color = Color.White, shape = buttonShape)
                .clip(shape = buttonShape)
                .clickable(onClick = onClick)
                .padding(horizontal = innerHorizontalPadding, vertical = innerVerticalPadding)
                .onSizeChanged {
                    spacerHeight = with(density) { it.height.toDp() + (innerVerticalPadding * 2) }
                }
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(text = text, color = contentColor)
        }
    }
}


















@Composable
fun ElevatedBackgroundWithCutout(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 10.dp,
    shadowElevation= 10.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(200.dp) // Adjust height as needed
                .drawBehind {
                    val cutoutWidth = 50.dp // Adjust cutout width as needed
                    val cutoutPath = Path().apply {
                        moveTo(size.width, 0f)
                        lineTo(size.width - cutoutWidth.toPx(), 0f)
                        lineTo(size.width - cutoutWidth.toPx(), size.height)
                        lineTo(size.width, size.height)
                        close()
                    }
                    clipPath(cutoutPath) {
                        drawRect(color = Color.White) // Adjust color as needed
                    }
                },
//                .clip(
//                    cutoutPath // Clip content to the cutout shape
//                ),
            contentAlignment = Alignment.Center
        ) {
// Your content here
        }
    }
}
