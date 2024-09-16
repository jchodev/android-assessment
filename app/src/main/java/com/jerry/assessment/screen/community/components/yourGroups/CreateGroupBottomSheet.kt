package com.jerry.assessment.screen.community.components.yourGroups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.jerry.assessment.R
import com.jerry.assessment.composes.bottomsheet.BetterModalBottomSheet
import com.jerry.assessment.composes.button.CommonButton
import com.jerry.assessment.composes.checkbox.CircleCheckBox
import com.jerry.assessment.composes.icon.RoundedCardIcon
import com.jerry.assessment.composes.loading.LoadingCompose
import com.jerry.assessment.composes.placeholder.PlaceHolderText
import com.jerry.assessment.composes.search.SearchField
import com.jerry.assessment.composes.textField.MyTextField
import com.jerry.assessment.composes.topbar.MyTopBar


@Composable
fun CreateGroupBottomSheet(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    viewModel: CreateGroupViewModel = CreateGroupViewModel()
){
    val uiState = viewModel.uiState.collectAsState().value

    CreateGroupBottomSheetContent(
        showSheet = showSheet,
        onDismissRequest = onDismissRequest,
        contacts = uiState.contacts,
        onContactSelect = viewModel::onContactSelect,
        onNextClick = viewModel::onNextClick,
        selectPageIndex = uiState.selectedPageIndex,

        groupName = uiState.groupName,
        groupNameError = uiState.groupNameError,
        onGroupNameUpdate = viewModel::onGroupNameUpdate,
        aboutGroup = uiState.aboutGroup,
        aboutGroupError = uiState.aboutGroupError,
        onAboutGroupUpdate = viewModel::onAboutGroupUpdate,
        permanentGroup = uiState.permanentGroup,
        onPermanentGroupChange = viewModel::onPermanentGroupChange,
        simplifiedPayment = uiState.simplifiedPayment,
        onSimplifiedPaymentChange = viewModel::onSimplifiedPaymentChange,
        onBackButtonClick = viewModel::onBackButtonClick,
        nextButtonEnabled = uiState.nextButtonEnabled,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupBottomSheetContent(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    contacts: List<CreateGroupContact> = emptyList(),
    onContactSelect: (CreateGroupContact) -> Unit = {},
    selectPageIndex: Int = 0,

    isLoading: Boolean = false,

    //createGroupArea
    groupName: String = "",
    groupNameError: String? = null,
    onGroupNameUpdate: (String) -> Unit = {},
    aboutGroup: String = "",
    aboutGroupError: String? = null,
    onAboutGroupUpdate: (String) -> Unit = {},
    permanentGroup: Boolean = true,
    onPermanentGroupChange: (Boolean) -> Unit = {},
    simplifiedPayment: Boolean = true,
    onSimplifiedPaymentChange: (Boolean) -> Unit = {},
    onBackButtonClick: () -> Unit = {},

    onNextClick: () -> Unit,
    nextButtonEnabled: Boolean = false
) {

    BetterModalBottomSheet(
        showSheet = showSheet,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MyTopBar(
                title = if (selectPageIndex==2)  "Success" else "Create a group",
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

            if (isLoading){
                LoadingCompose()
            } else {
                //differ page
                if (selectPageIndex == 0){
                    SelectedContactContent(
                        contacts = contacts,
                        onContactSelect = onContactSelect,
                    )
                } else if (selectPageIndex == 1) {
                    CreateGroupContent(
                        groupName = groupName,
                        groupNameError = groupNameError,
                        onGroupNameUpdate = onGroupNameUpdate,
                        aboutGroup = aboutGroup,
                        aboutGroupError = aboutGroupError,
                        onAboutGroupUpdate = onAboutGroupUpdate,
                        permanentGroup = permanentGroup,
                        onPermanentGroupChange = onPermanentGroupChange,
                        simplifiedPayment = simplifiedPayment,
                        onSimplifiedPaymentChange = onSimplifiedPaymentChange,
                        onBackButtonClick = onBackButtonClick,

                        contacts = contacts,
                        onContactSelect = onContactSelect,
                    )
                } else {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            modifier = Modifier.size(277.dp),
                            painter = painterResource(R.drawable.create_group_success_thumb_up),
                            contentDescription = "success",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 32.dp),
                            text = "Group Created Successfully.\n" +
                                    "Start Adding Expenses to Your New Group",
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                            textAlign = TextAlign.Center,
                            color = Color(0XFF686675),
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400)
                        )
                    }
                }

                //bottom next button
                if (selectPageIndex < 2) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CommonButton(
                            onClick = onNextClick,
                            text = "Next",
                            enabled = nextButtonEnabled,
                        )
                    }
                }

            }

        }
    }

}

@Composable
private fun SelectedContactContent(
    contacts: List<CreateGroupContact> = emptyList(),
    onContactSelect: (CreateGroupContact) -> Unit = {},
){
    var searchQuery by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        //
        SearchField(
            hint = "Search",
            onSearchParamChange = {
                searchQuery = it
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (contacts.filter { it.selected } .isNotEmpty()){
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = contacts.filter { it.selected } ,
                    key = { contact -> contact.id }
                ) { contact ->
                    SelectedContentItem(
                        contact,
                        onCrossClick = {
                            onContactSelect.invoke(it)
                        }
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledIconButton(
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add new contacts",
                        fontSize = 14.sp,
                        //fontFamily = FontFamily(Font(Res.font.Inter)),
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.height(250.dp)) {
            items(
                if (searchQuery.isNotBlank()){
                    contacts.filter { it.name.contains(searchQuery, ignoreCase = true) }
                } else {
                    contacts
                }
            ) { contact ->
                ContactItem(
                    contact = contact,
                    onSelect = onContactSelect
                )
            }
        }
    }
}

@Composable
private fun CreateGroupContent(
    groupName: String = "",
    groupNameError: String? = null,
    onGroupNameUpdate: (String) -> Unit = {},
    aboutGroup: String = "",
    aboutGroupError: String? = null,
    onAboutGroupUpdate: (String) -> Unit = {},

    permanentGroup: Boolean = true,
    onPermanentGroupChange: (Boolean) -> Unit = {},
    simplifiedPayment: Boolean = true,
    onSimplifiedPaymentChange: (Boolean) -> Unit = {},

    contacts: List<CreateGroupContact> = emptyList(),
    onContactSelect: (CreateGroupContact) -> Unit = {},
    onBackButtonClick: () -> Unit = {},
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        MyTextField(
            value = groupName,
            onValueChange = onGroupNameUpdate,
            placeholder = {
                PlaceHolderText("Enter group name")
            },
            isError = groupNameError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        MyTextField(
            value = aboutGroup,
            onValueChange = onAboutGroupUpdate,
            placeholder = {
                PlaceHolderText("About Group")
            },
            isError = aboutGroupError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Permanent Group",
                fontSize = 12.sp,
                //fontFamily = FontFamily(Font(Res.font.Inter)),
                lineHeight = 16.sp,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = permanentGroup, onCheckedChange = onPermanentGroupChange)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Simplified Payments",
                fontSize = 12.sp,
                //fontFamily = FontFamily(Font(Res.font.Inter)),
                lineHeight = 16.sp,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = simplifiedPayment, onCheckedChange = onSimplifiedPaymentChange)
        }
        Spacer(modifier = Modifier.height(8.dp))

        if (contacts.filter { it.selected } .isNotEmpty()){
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = contacts.filter { it.selected } ,
                    key = { contact -> contact.id }
                ) { contact ->
                    SelectedContentItem(
                        contact,
                        onCrossClick = {
                            onContactSelect.invoke(it)
                        }
                    )
                }

                item {
                    FilledIconButton(
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = onBackButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

//
//@Preview
//@Composable
//private fun CreateGroupBottomSheetPreview(){
//
//    CreateGroupBottomSheet(
//        showSheet = true,
//        onDismissRequest = {}
//    )
//
//}

@Composable
fun ContactItem(
    contact: CreateGroupContact,
    onSelect: (CreateGroupContact) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color.White, CircleShape),
            model = contact.imageUrl,
            contentDescription = contact.name,
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
        )
        Text(
            text = contact.name,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        CircleCheckBox(
            isChecked = contact.selected,
            onCheckedChange = {
                onSelect(contact)
            },
        )
    }
}

@Composable
private fun SelectedContentItem(
    contact: CreateGroupContact,
    onCrossClick: (CreateGroupContact) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .size(40.dp)
    ) {
        SubcomposeAsyncImage(
            model = contact.imageUrl,
            contentDescription = contact.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    // You can add a placeholder or error image here
                }
                else -> {
                    SubcomposeAsyncImageContent()
                }
            }
        }

        Box(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.TopEnd)
                .background(MaterialTheme.colorScheme.error, CircleShape)
                //.border(2.dp, Color.White, CircleShape)
                .clickable {
                    onCrossClick(contact)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = MaterialTheme.colorScheme.onError
            )
        }
    }
}
