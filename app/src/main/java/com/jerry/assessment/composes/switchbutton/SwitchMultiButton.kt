package com.jerry.assessment.composes.switchbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.ui.theme.AppTheme


@Composable
fun SwitchMultiButton(
    buttons: List<ButtonData>,
    selectedIndex: Int,
    onButtonClick: (Int) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        //fontFamily = FontFamily(Font(Res.font.LufgaBlack)),
        lineHeight = 21.sp,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
    )
) {
    require(buttons.size in 2..3) { "SwitchMultiButton supports only 2 or 3 buttons" }

    val selectedBg = MaterialTheme.colorScheme.primary
    val nonSelectBg = MaterialTheme.colorScheme.onPrimary
    val selectedTextColor = MaterialTheme.colorScheme.onPrimary
    val nonSelectedTextColor = Color.Black
    val shape = MaterialTheme.shapes.medium

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = shape
            )
            .padding(4.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttons.forEachIndexed { index, buttonData ->
            Button(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = if (index == selectedIndex) selectedBg else nonSelectBg,
                        shape = shape
                    )
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = { onButtonClick(index) },
            ) {
                Text(
                    text = buttonData.text,
                    color = if (index == selectedIndex) selectedTextColor else nonSelectedTextColor,
                    style = textStyle,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

data class ButtonData(
    val text: String,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
private fun Screen2ContentPreview() {
    AppTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        val buttons = listOf(
            ButtonData("Button 1") { },
            ButtonData("Button 2") { },
            ButtonData("Button 3") { },
        )

        SwitchMultiButton(
            buttons = buttons,
            selectedIndex = selectedIndex,
            onButtonClick = { index ->
                selectedIndex = index
                buttons[index].onClick()
            }
        )
    }
}