package com.jerry.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.jerry.assessment.composes.test.AISummaryExpandableCard
import com.jerry.assessment.composes.test.ExpandableCard
import com.jerry.assessment.screen.community.CommunityScreen
import com.jerry.assessment.screen.community.components.yourGroups.CreateGroupBottomSheet
import com.jerry.assessment.screen.community.components.yourGroups.CreateGroupViewModel
import com.jerry.assessment.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CreateGroupViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CommunityScreen()
        }
//        setContent {
//            var showSheet by remember { mutableStateOf(false) }
//            var text by remember { mutableStateOf("Hello") }
//
//            if (showSheet) {
//                CreateGroupBottomSheet(
//                    viewModel = viewModel,
//                    showSheet = showSheet,
//                    onDismissRequest = { showSheet = false },
//                )
//
////                val items: MutableList<SettleItemData> = mutableListOf()
////                for (i in 1..100) {
////                    items.add(SettleItemData())
////                }
////                SettingRemindBottomSheet(
////                    showSheet = showSheet,
////                    onDismissRequest = { showSheet = false },
////                    items = items
////                )
//            }
//            Scaffold(
//                modifier = Modifier.fillMaxSize(),
//               // color = MaterialTheme.colorScheme.background
//            ) { paddingValues ->
//                Column(modifier = Modifier
//                    .padding(paddingValues)
//                    .fillMaxWidth()) {
//                    Button(onClick = {
//                        viewModel.fetchContact()
//                        showSheet = true
//                    }) {
//                        Text(text = "Show BottomSheet")
//                    }
//
//                    ExpandableCard(
//                        headline =  "Biden drops out of 2024 race after disastrous debate inflamed age concerns",
//                        body =  "Joe Biden is the 46th president of the United States (2021– ). " +
//                                "He was born on November 20, 1942, in Scranton, Pennsylvania, " +
//                                "and he served as a U.S. senator representing Delaware from 1972 to 2009. " +
//                                "He was vice president in the Barack Obama administration from 2009 to 2017. " +
//                                "In July 2024 Biden announced he would not seek a second term as president.\n" +
//                                "In a statement posted on social media on July 21, 2024, Joe Biden said that " +
//                                "“while it has been my intention to seek reelection, " +
//                                "I believe it is in the best interest of my party and the country for me to stand down and " +
//                                "to focus solely on fulfilling my duties as President for the remainder of my term.”",
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//                    AISummaryExpandableCard(
//                        headline = "Biden drops out of 2024 race after disastrous debate inflamed age concerns",
//                        body = "Joe Biden is the 46th president of the United States (2021– ). " +
//                                "He was born on November 20, 1942, in Scranton, Pennsylvania, " +
//                                "and he served as a U.S. senator representing Delaware from 1972 to 2009. " +
//                                "He was vice president in the Barack Obama administration from 2009 to 2017. " +
//                                "In July 2024 Biden announced he would not seek a second term as president.\n" +
//                                "In a statement posted on social media on July 21, 2024, Joe Biden said that " +
//                                "“while it has been my intention to seek reelection, " +
//                                "I believe it is in the best interest of my party and the country for me to stand down and " +
//                                "to focus solely on fulfilling my duties as President for the remainder of my term.”",
//                        //isExpanded = true,
//                    )
//                }
//                //CommunityOverview()
//            }
//        }
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
    AppTheme {
        Greeting("Android")
    }
}