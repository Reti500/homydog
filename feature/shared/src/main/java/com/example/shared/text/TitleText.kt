package com.example.shared.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.shared.colors.Colors

@Composable
fun TitleText(
    title: String,
    fontSize: TextUnit = 22.sp,
    fontColor: Color = Colors.TitleTextColor,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = fontSize,
            color = fontColor
        ),
        maxLines = maxLines
    )
}

@Composable
@Preview
private fun PreviewTitleText() {
    MaterialTheme {
        TitleText("Hello")
    }
}