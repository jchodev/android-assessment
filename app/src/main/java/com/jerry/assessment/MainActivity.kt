package com.jerry.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.jerry.assessment.composes.bottomsheet.BetterModalBottomSheet
import com.jerry.assessment.composes.bottomsheet.CountryList
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            var showSheet by remember { mutableStateOf(false) }
            var text by remember { mutableStateOf("Hello") }

            if (showSheet) {
                BetterModalBottomSheet(
                    showSheet = showSheet,
                    content = {
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Label") }
                        )
                        CountryList()
                        Button(onClick = { }) {
                            Text("this is button")
                        }
                    },
                    onDismissRequest = {
                        showSheet = false
                    }
                )
            }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
               // color = MaterialTheme.colorScheme.background
            ) { paddingValues ->
                Column(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()) {
                    Button(onClick = {
                        showSheet = true
                    }) {
                        Text(text = "Show BottomSheet")
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssessmentprojectTheme {
        Greeting("Android")
    }
}