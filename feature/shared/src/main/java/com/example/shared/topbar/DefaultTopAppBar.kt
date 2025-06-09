package com.example.shared.topbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shared.text.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    backButtonTint: Color = Color.Gray,
    onBackPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            TitleText(
                title,
                fontSize = 28.sp,
                maxLines = 1,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = backButtonTint,
                    modifier = Modifier.size(48.dp)
                )
            }
        },
        actions = {},
    )
}

@Composable
@Preview
private fun PreviewDefaultTopAppBar() {
    MaterialTheme {
        DefaultTopAppBar(
            title = "Default App Bar"
        )
    }
}