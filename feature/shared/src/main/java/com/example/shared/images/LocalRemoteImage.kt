package com.example.shared.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Composable
fun LocalRemoteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    scale: ContentScale = ContentScale.Crop
) {
    val painterState = remember { mutableStateOf<Painter?>(null) }

    LaunchedEffect(imageUrl) {
        painterState.value = loadImagePainter(imageUrl)
    }

    painterState.value?.let { painter ->
        Image(
            painter = painter,
            contentDescription = "Image from URL",
            modifier = modifier,
            contentScale = scale
        )
    } ?: run {
        // Show a placeholder or loading indicator while the image is loading
        CircularProgressIndicator()
    }
}

suspend fun loadImagePainter(url: String): Painter? {
    return withContext(Dispatchers.IO) {
        try {
            val connection = URL(url).openConnection() as HttpsURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream
            val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            val imageBitmap = bitmap.asImageBitmap()
            BitmapPainter(imageBitmap)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadImageFromUrl() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocalRemoteImage(
            imageUrl = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101",
            modifier = Modifier.size(100.dp)
        )
    }
}