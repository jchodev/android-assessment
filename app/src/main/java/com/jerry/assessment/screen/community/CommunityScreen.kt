package com.jerry.assessment.screen.community


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.composes.background.CommonBackground
import com.jerry.assessment.composes.icon.RoundedCardIcon
import com.jerry.assessment.composes.topbar.MyTopBar
import com.jerry.assessment.screen.community.components.overall.CommunityOverview
import com.jerry.assessment.screen.community.components.plan.PlanCardData
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestEvent
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestItemData
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestMoney


@Composable
fun CommunityScreenTopBar() {
    MyTopBar(
        title = "Community",
        subTitle = "Good Morning Sreejith !",
        navigationIcon = {
            RoundedCardIcon(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_four_dots),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        },

        actions = {
            RoundedCardIcon(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_bell),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    )
}

@Composable
fun CommunityScreen(){
    CommonBackground {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CommunityScreenTopBar()
            },
            containerColor = Color.Transparent,
            floatingActionButton = {
                FilledIconButton(
                    modifier = Modifier.size(60.dp),
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
        ) { paddingValues ->
            CommunityOverview(
                paddingValues = paddingValues,
                balance = "12344.00",
                toGiveAmount = "333.11",
                toGetAmount = "123.11",

                onSelectedDate = {},
                planCardDatas = listOf(
                    PlanCardData(
                        day = "23",
                        month = "Jan",
                        title = "Trip to London",
                        timePeriod = "12:00 - 16:00",
                        avatars = listOf(
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                        )
                    ),
                    PlanCardData(
                        day = "23",
                        month = "Jan",
                        title = "Trip to London",
                        timePeriod = "12:00 - 16:00",
                        avatars = listOf(
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                            "https://dummyimage.com/100x100/6699cc/000",
                        )
                    )
                ),
                requestItemDatas = listOf(
                    CommunityRequestItemData(
                        event = CommunityRequestEvent(
                            title = "this is title",
                            timePeriod = "12.00 - 16.00",
                            day = "23",
                            weekOfDate = "Sat",
                            dateTime = "12 Feb 2024"
                        )
                    ),
                    CommunityRequestItemData(
                        money = CommunityRequestMoney(
                            title = "thos is title",
                            subTitle = "this is sub title",
                            amount = "50.00",
                            dateTime = "12 Fev 2024"
                        )
                    )
                )
            )

        }
    }
}




