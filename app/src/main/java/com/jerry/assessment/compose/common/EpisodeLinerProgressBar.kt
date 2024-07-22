package com.jerry.assessment.compose.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.ext.convertTimeFormat
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun EpisodeLinerProgressBar(
    currentPosition: Long = 0,
    duration: Long = 0,
    displayDuration: String = "",
) {
    val progress = if (currentPosition.toInt() ==0 && duration.toInt() == 0){
        0f
    } else {
        (currentPosition.toFloat() / duration)
    }
    Column(modifier = Modifier.padding(8.dp)) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = progress,
            strokeCap = StrokeCap.Square
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = currentPosition.convertTimeFormat(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = displayDuration,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }

}

@DevicePreviews
@Composable
private fun EpisodeLinerProgressBarPreview(){
    AssessmentprojectTheme {
        EpisodeLinerProgressBar(
            displayDuration = "10:00",
            currentPosition = 1L,
            duration = 2L
        )
    }
}