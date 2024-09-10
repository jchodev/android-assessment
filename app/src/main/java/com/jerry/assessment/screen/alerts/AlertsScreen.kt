package com.jerry.assessment.screen.alerts

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.composes.topbar.MyTopBar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlertsScreen() {
    val gradientColors = Brush.linearGradient(
        colors = listOf(
            Color(0XFFF0F0FE),
            Color(0XFFFFFFFF)
        )
    )
    Box (modifier = Modifier.background(gradientColors)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                MyTopBar(
                    title = "top bar 2",
                    navigationIcon = {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(48.dp),
                            onClick = { /*TODO*/ },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp,
                            ),
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),

                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_four_dots),
                                    contentDescription = null,
                                    modifier = Modifier.size(25.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                    }
                )
            },
            containerColor = Color.Transparent
        ) {
                paddingValues ->

            Column (
                modifier = Modifier

                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                Text("this is some view ")
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp,
                    ),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                ) {
                }
            }

        }
    }
}

@Preview
@Composable
private fun AlertsScreenPreview() {
    AlertsScreen()
}