package com.jerry.assessment.screen.community.components.settleRemind

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.composes.bottomsheet.BetterModalBottomSheet
import com.jerry.assessment.composes.bottomsheet.BetterModalLazyColumnBottomSheet
import com.jerry.assessment.composes.icon.RoundedCardIcon
import com.jerry.assessment.composes.topbar.MyTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingRemindBottomSheet(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    isSettle: Boolean = true,
    items: List<SettleItemData> = emptyList(),
) {
    val title = if (isSettle) "Settle" else "Remind"
    BetterModalBottomSheet(
        showSheet = showSheet,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MyTopBar(
                title = title,
                navigationIcon = {
                    RoundedCardIcon(
                        onClick = onDismissRequest,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                },
                actions = {}
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items) { item ->
                    SettleRemindItem(data = item)
                }
            }

        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SettleRemindBottomSheetPreview(){

    BetterModalLazyColumnBottomSheet(
        sheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        ),
        showSheet = true,
        onDismissRequest =  {},
        topBar = {
            MyTopBar(
                title = "this is title",
                navigationIcon = {
                    RoundedCardIcon(
                        onClick = {  },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                },
                actions = {}
            )
        },
        content = {
            items(100) { index ->
                Text("Item $index")
            }
        },
        bottomBar = {
            Text (text =  "this is bottom bar")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SettleRemindBottomSheetPreview2(){

}