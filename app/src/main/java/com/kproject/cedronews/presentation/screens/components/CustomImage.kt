package com.kproject.cedronews.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kproject.cedronews.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    imageModel: Any,
    contentScale: ContentScale = ContentScale.Crop,
) {
    CoilImage(
        imageModel = { imageModel },
        failure = {
            FailureIndicator()
        },
        previewPlaceholder = R.drawable.outline_photo_24,
        imageOptions = ImageOptions(
            contentScale = contentScale,
        ),
        modifier = modifier
    )
}

@Composable
private fun BoxScope.FailureIndicator() {
    Box(
        modifier = Modifier
            .matchParentSize()
            .background(Color(0x99AA0003))
    ) {
        Image(
            painter = painterResource(id = R.drawable.outline_image_not_supported_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFFCECECE)),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(12.dp)
        )
    }
}