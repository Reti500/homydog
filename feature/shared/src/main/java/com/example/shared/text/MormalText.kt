package com.example.shared.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.shared.colors.Colors


@Composable
fun NormalText(
    text: String,
    fontSize: TextUnit = 14.sp,
    fontColor: Color = Colors.NormalTextColor,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = fontColor
        ),
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
@Preview
private fun PreviewNormalText() {
    MaterialTheme {
        NormalText("Hello this is a normal text")
    }
}

@Composable
@Preview
private fun PreviewNormalTextBold() {
    MaterialTheme {
        NormalText(
            "Hello this is a bold normal text",
            fontWeight = FontWeight.Bold
        )
    }
}