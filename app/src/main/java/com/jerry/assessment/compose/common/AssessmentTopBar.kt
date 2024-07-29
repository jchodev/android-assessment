package com.jerry.assessment.compose.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.jerry.assessment.R
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.ui.theme.AssessmentprojectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentTopBar(
    modifier: Modifier = Modifier,
    title: String = "title",
    titleCompose: @Composable (() -> Unit)? = null,
    showBack: Boolean = true,
    onBackClick: () -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = colors,
        modifier = modifier,
        title = {
            if (titleCompose != null) {
                titleCompose()
            } else {
                Text(
                    modifier = Modifier.testTag(TEST_TAG_TITLE),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    //overflow: TextOverflow = TextOverflow.Clip,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = {
                    onBackClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = stringResource(id = R.string.hint_back),
                    )
                }
            }
        },
        actions = actions

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun AssessmentTopBarPreview(){
    AssessmentprojectTheme {
        AssessmentTopBar()
    }
}