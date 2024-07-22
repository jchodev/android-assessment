package com.jerry.assessment.compose.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun ErrorDialog(
    text: String = "this is text",
    onRetryRequest: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    CustomAlertDialog(
        icon = {
            Icon(
                modifier = Modifier
                    .size(160.dp)
                    .padding(top = 32.dp),
                tint = MaterialTheme.colorScheme.error,
                imageVector = Icons.Outlined.Error,
                contentDescription = stringResource(id = R.string.error)
            )
        },
        title = null,
        message = text,
        leftBtnStr= stringResource(R.string.close),
        onLeftClick = onDismissRequest,
        rightBtnStr = stringResource(R.string.retry),
        onRightClick = onRetryRequest
    )
}

@DevicePreviews
@Composable
private fun ErrorDialogPreview(){
    AssessmentprojectTheme {
        ErrorDialog()
    }
}