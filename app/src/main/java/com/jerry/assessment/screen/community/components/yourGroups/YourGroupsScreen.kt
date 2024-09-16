package com.jerry.assessment.screen.community.components.yourGroups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.composes.background.CommonBackground
import com.jerry.assessment.composes.card.CommonCardContainer
import com.jerry.assessment.composes.card.DoubleBoarderCard
import com.jerry.assessment.composes.group.Gender
import com.jerry.assessment.composes.group.GroupItem
import com.jerry.assessment.composes.group.GroupTile
import com.jerry.assessment.composes.group.Member
import com.jerry.assessment.composes.icon.RoundedCardIcon
import com.jerry.assessment.composes.row.RowTextWithRightViewAll
import com.jerry.assessment.composes.search.SearchField
import com.jerry.assessment.composes.topbar.MyTopBar
import com.jerry.assessment.ui.theme.AppTheme


@Composable
private fun YourGroupsScreenTopBar() {
    MyTopBar (
        title = "Your Groups",
        navigationIcon = {
            RoundedCardIcon(
                icon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        //painter = painterResource(Res.drawable.ic_four_dots),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        },

        actions = {
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
        }
    )
}

@Composable
fun YourGroupsScreen() {
    CommonBackground {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            topBar = {
                YourGroupsScreenTopBar()
            },
            containerColor = Color.Transparent
        ) { paddingValues ->

            Column ( modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) )
            {
                SearchField(
                    hint = "Search for Names or Groups"
                ) {}
                Spacer(modifier = Modifier.height(16.dp))
                RowTextWithRightViewAll (
                    text = "Request"
                )
                Spacer(modifier = Modifier.height(16.dp))

                CommonCardContainer{
                    Column (modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        DoubleBoarderCard {
                            GroupTile(
                                groupItem = GroupItem(
                                    plans = 3,
                                    messages = 3,
                                    expenses = 3,
                                    members = listOf(
                                        Member(name = "John", gender = Gender.MALE),
                                        Member(name = "Sri", gender = Gender.FEMALE),
                                        Member(name = "Steve", gender = Gender.MALE),
                                        Member(name = "Shela", gender = Gender.FEMALE),
                                    )
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        DoubleBoarderCard {
                            GroupTile(
                                groupItem = GroupItem(
                                    plans = 3,
                                    messages = 3,
                                    expenses = 3,
                                    members = listOf(
                                        Member(name = "John", gender = Gender.MALE),
                                        Member(name = "Sri", gender = Gender.FEMALE),
                                        Member(name = "Steve", gender = Gender.MALE),
                                        Member(name = "Shela", gender = Gender.FEMALE),
                                    )
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                RowTextWithRightViewAll (
                    text = "All Group"
                )

                Spacer(modifier = Modifier.height(16.dp))
                CommonCardContainer{
                    Column (modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        DoubleBoarderCard {
                            GroupTile(
                                groupItem = GroupItem(
                                    plans = 3,
                                    messages = 3,
                                    expenses = 3,
                                    members = listOf(
                                        Member(name = "John", gender = Gender.MALE),
                                        Member(name = "Sri", gender = Gender.FEMALE),
                                        Member(name = "Steve", gender = Gender.MALE),
                                        Member(name = "Shela", gender = Gender.FEMALE),
                                    )
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        DoubleBoarderCard {
                            GroupTile(
                                groupItem = GroupItem(
                                    plans = 3,
                                    messages = 3,
                                    expenses = 3,
                                    members = listOf(
                                        Member(name = "John", gender = Gender.MALE),
                                        Member(name = "Sri", gender = Gender.FEMALE),
                                        Member(name = "Steve", gender = Gender.MALE),
                                        Member(name = "Shela", gender = Gender.FEMALE),
                                    )
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

        }
    }
}

@Preview
@Composable
private fun YourGroupsScreenPreview(){
    AppTheme {
        YourGroupsScreen()
    }
}