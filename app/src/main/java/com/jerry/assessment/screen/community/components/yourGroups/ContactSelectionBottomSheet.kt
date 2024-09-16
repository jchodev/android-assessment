//package com.jerry.assessment.screen.community.components.yourGroups
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material3.Button
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import coil.compose.SubcomposeAsyncImage
//import com.jerry.assessment.composes.bottomsheet.BetterModalBottomSheet
//import com.jerry.assessment.composes.icon.RoundedCardIcon
//import com.jerry.assessment.composes.search.SearchField
//import com.jerry.assessment.composes.topbar.MyTopBar
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ContactSelectionBottomSheet(
//    showSheet: Boolean,
//    onDismissRequest: () -> Unit,
//    contacts: List<Contact> = emptyList(),
//    onContactSelected: (Contact) -> Unit = {}
//) {
//    BetterModalBottomSheet(
//        showSheet = showSheet,
//        onDismissRequest = onDismissRequest,
//        sheetState = rememberModalBottomSheetState(),
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            MyTopBar(
//                title = "Create a group",
//                navigationIcon = {
//                    RoundedCardIcon(
//                        onClick = onDismissRequest,
//                        icon = {
//                            Icon(
//                                imageVector = Icons.Default.Close,
//                                contentDescription = null,
//                                modifier = Modifier.size(24.dp),
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    )
//                },
//                actions = {}
//            )
//
//            //if
//            Column(
//                modifier = Modifier.fillMaxWidth().padding(16.dp)
//            ) {
//                SearchField(
//                    hint = "Search"
//                ) {}
//
//                LazyColumn(modifier = Modifier.height(250.dp)) {
//                    items(contacts) { contact ->
//                        ContactItem(contact, onContactSelected)
//                    }
//                }
//
//                Button(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = {}
//                ){
//                    Text(text = "Next")
//                }
//            }
//
//        }
//    }
//
//}
//
//
