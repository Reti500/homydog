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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.example.dogs.R
import com.example.dogs.domain.models.Dog

@Composable
fun DogCard(
    dog: Dog,
    modifier: Modifier = Modifier,
    imageWidth: Int = 490,
) {
    val density = LocalDensity.current
    val imageWidthDp = with(density) { (imageWidth * .8).toInt().toDp() }

    Box(
        modifier = modifier.padding(16.dp)
    ) {
        // Image at the top
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(dog.imageUrl)
                .size(imageWidth)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
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
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = dog.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        minLines = 3,
                        maxLines = 3
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.dog_old, dog.age),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
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
                imageUrl = ""
            )
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
            )
        )
    }
}