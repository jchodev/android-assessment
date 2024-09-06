package com.jerry.assessment.composes.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String = "this is title",
    subTitle: String? = null,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.offset(y = (-2).dp),
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                        textAlign = TextAlign.Center,
                        color = Color(0XFF1E1E1E),
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400)
                    )
                )
                subTitle?.let {
                    Text(
                        modifier = Modifier.offset(y = (-2).dp),
                        text = it,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                            textAlign = TextAlign.Center,
                            color = Color(0XFF1E1E1E),
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
            }
        },
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@Preview
@Composable
private fun FluckTopBarTitleOnlyPreview(){

    MyTopBar(
            title = "title"
        )

}

@Preview
@Composable
private fun FluckTopBarTitleSubTitlePreview(){
    MyTopBar(
        title = "title",
        subTitle = "this is sub title"

    )
}