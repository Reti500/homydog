package com.example.shared.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest

@Composable
fun CoilRemoteImage(
    imageUrl: String,
    imageWidth: Int,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .size(imageWidth)
            .build(),
        contentDescription = null,
        modifier = modifier
    )
}