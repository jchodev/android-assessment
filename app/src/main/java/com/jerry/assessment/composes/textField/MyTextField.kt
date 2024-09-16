package com.jerry.assessment.composes.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholderText: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    readOnly: Boolean = false,
) {
    val textGradient = Brush.linearGradient(
        colors = listOf(
            Color(0XFF5B0AF8),
            Color(0XFFFD4485),
        ),
        start = Offset(0.0f, 145.0f),
        end = Offset(150.0f, 250.0f),
        tileMode = TileMode.Clamp
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.50.dp,
                brush = textGradient,
                shape = RoundedCornerShape(32.dp)
            ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            MyTextFieldPlaceHolder(
                placeholderText = placeholderText,
                placeholder = placeholder
            )
        },
        shape = RoundedCornerShape(32.dp),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        isError = isError,
        readOnly = readOnly,
    )
}

@Composable
fun MyTextFieldPlaceHolder(
    placeholderText: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
) {
    when {
        placeholder != null -> placeholder()
        !placeholderText.isNullOrEmpty() -> Text(text = placeholderText)
    }
}