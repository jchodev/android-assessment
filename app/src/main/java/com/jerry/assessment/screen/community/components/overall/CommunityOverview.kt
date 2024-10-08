package com.jerry.assessment.screen.community.components.overall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.composes.row.RowTextWithRightViewAll
import com.jerry.assessment.screen.community.components.balanceCard.BalanceCard
import com.jerry.assessment.screen.community.components.message.ChatroomItemData
import com.jerry.assessment.screen.community.components.message.OverallMessageCard
import com.jerry.assessment.screen.community.components.plan.PlanCardData
import com.jerry.assessment.screen.community.components.plan.YourPlan
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestEvent
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestItemData
import com.jerry.assessment.screen.community.components.requestItem.CommunityRequestMoney
import com.jerry.assessment.screen.community.components.requestItem.EventRequestItem
import com.jerry.assessment.screen.community.components.requestItem.MoneyRequestItem
import kotlinx.datetime.LocalDate

@Composable
fun CommunityOverview(
    paddingValues: PaddingValues,
    //balance
    balance: String,
    toGiveAmount: String,
    toGetAmount: String,

    //plan card:
    onSelectedDate: (LocalDate) -> Unit,
    planCardDatas: List<PlanCardData>,

    //request:
    requestItemDatas: List<CommunityRequestItemData>,
) {

    Column ( modifier = Modifier
        .padding(paddingValues)
        .padding(16.dp) )
    {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //BalanceCard
            item {
                BalanceCard(
                    balance = balance,
                    toGetAmount = toGetAmount,
                    toGiveAmount = toGiveAmount
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            //your plan
            item {
                YourPlan(
                    onSelectedDate = onSelectedDate,
                    planCardDatas = planCardDatas
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                OverallMessageCard(
                    items = listOf(
                        ChatroomItemData(
                            avatars = listOf(
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                            ),
                            title = "this is title",
                            subTitle = "this is subtitle",
                            dateTime = "12:23",
                            unseenCount = 1,
                        ),
                        ChatroomItemData(
                            avatars = listOf(
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                            ),
                            title = "this is title",
                            subTitle = "this is subtitle",
                            dateTime = "12:23",
                            unseenCount = 1,
                        ),
                        ChatroomItemData(
                            avatars = listOf(
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                                "https://dummyimage.com/100x100/6699cc/000",
                            ),
                            title = "this is title",
                            subTitle = "this is subtitle",
                            dateTime = "12:23",
                            unseenCount = 1,
                        )
                    )
                )
            }

            //requests
            item {
                Spacer(modifier = Modifier.height(8.dp))
                RowTextWithRightViewAll(
                    text = "Requests",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(items = requestItemDatas) { index, item ->
                if (item.event!=null){
                    EventRequestItem(event = item.event)
                } else if (item.money!=null){
                    MoneyRequestItem(item = item.money)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }

}

@Preview
@Composable
private fun CommunityOverviewPreview(){
    CommunityOverview(
        paddingValues = PaddingValues(0.dp),
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

