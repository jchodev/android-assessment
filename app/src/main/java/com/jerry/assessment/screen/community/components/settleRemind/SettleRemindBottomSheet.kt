package com.jerry.assessment.screen.community.components.settleRemind

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
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
    title: String = "Settle",
) {
    BetterModalLazyColumnBottomSheet(
        showSheet = showSheet,
        onDismissRequest = onDismissRequest,
        sheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        ),
        topBar = {
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
        },
        content = {
            items(100) { index ->
                Text("Item $index")
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SettleRemindBottomSheetPreview(){
    BetterModalBottomSheet(
        showSheet = true,
        onDismissRequest = {},
        sheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        ),
        content = {
            Text("tus us aaaa")
        }
    )

    BetterModalLazyColumnBottomSheet(
        sheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        ),
        showSheet = true,
        onDismissRequest =  {},
        topBar = {
            MyTopBar(
                title = "this is tilte",
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
        }
    )
}