package com.jerry.assessment.composes.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    TextButton(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.outline,
            disabledContentColor = MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = text,
            //fontFamily = FontFamily(Font(Res.font.Inter)),
            fontWeight = FontWeight(600),
            lineHeight = 24.sp,
            letterSpacing = 0.25.sp
        )
    }
}

@Preview
@Composable
private fun CommonButtonPreview(){
    CommonButton(
        onClick = { /*TODO*/ },
        text = "Next"
    )
}

@Preview
@Composable
private fun CommonButtonDisablePreview(){
    CommonButton(
        onClick = { /*TODO*/ },
        text = "Next",
        enabled = false
    )
}

