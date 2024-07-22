package com.jerry.assessment.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun CoilLoadingCompose() {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}

@DevicePreviews
@Composable
private fun CoilLoadingComposePreview(){
    AssessmentprojectTheme {
        CoilLoadingCompose()
    }
}