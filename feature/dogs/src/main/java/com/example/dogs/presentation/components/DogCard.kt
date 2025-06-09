package com.example.dogs.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.dogs.R
import com.example.dogs.domain.models.Dog
import com.example.shared.colors.Colors
import com.example.shared.images.LocalRemoteImage
import com.example.shared.text.NormalText
import com.example.shared.text.TitleText

@Composable
fun DogCard(
    dog: Dog,
    imageWidth: Dp,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val imageWidthDp = with(density) { imageWidth + 16.dp } // 16.dp for margin into image and texts

    Box(
        modifier = modifier.padding(16.dp)
    ) {
        // Image at the top
        LocalRemoteImage(
            imageUrl = dog.imageUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(imageWidth, imageHeight)
                .zIndex(1f)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .height(IntrinsicSize.Min), // Height min for the content
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(Modifier.width(imageWidthDp))
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                        .align(Alignment.Bottom)
                ) {
                    Spacer(Modifier.height(8.dp))
                    TitleText(
                        title = dog.name,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(12.dp))
                    NormalText(
                        text = dog.description,
                        maxLines = 3,
                        minLines = 3
                    )
                    Spacer(Modifier.height(12.dp))
                    NormalText(
                        text = stringResource(R.string.dog_old, dog.age),
                        fontColor = Colors.TitleTextColor,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewDogCard() {
    MaterialTheme {
        DogCard(
            dog = Dog(
                name = "Rex",
                description = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
                age = 5,
                imageUrl = "https://static.wikia.nocookie.net/isle-of-dogs/images/6/6b/Spots.jpg/revision/latest/scale-to-width-down/666?cb=20180624191101"
            ),
            imageWidth = 120.dp,
            imageHeight = 190.dp
        )
    }
}

@Composable
@Preview
private fun PreviewDogCardShortDescription() {
    MaterialTheme {
        DogCard(
            dog = Dog(
                name = "Rex",
                description = "He is a leader of a pack of dogs",
                age = 5,
                imageUrl = ""
            ),
            imageWidth = 150.dp,
            imageHeight = 240.dp
        )
    }
}