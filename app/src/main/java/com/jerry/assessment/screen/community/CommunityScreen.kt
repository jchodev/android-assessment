package com.jerry.assessment.screen.community

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import com.jerry.assessment.composes.background.CommonBackground
import com.jerry.assessment.composes.button.DoubleBorderIconButton
import com.jerry.assessment.composes.card.CommonCardContainer
import com.jerry.assessment.composes.icon.RoundedCardIcon
import com.jerry.assessment.composes.switchbutton.ButtonData
import com.jerry.assessment.composes.switchbutton.SwitchMultiButton
import com.jerry.assessment.composes.topbar.MyTopBar
import com.jerry.assessment.screen.community.components.giveGet.ToGet
import com.jerry.assessment.screen.community.components.giveGet.ToGive
import com.jerry.assessment.screen.community.components.plan.YourPlan
import com.jerry.assessment.screen.community.components.requestItem.EventRequestItem
import com.jerry.assessment.screen.community.components.requestItem.MoneyRequestItem


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
fun CommunityOverview(
    modifier:Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        //split your bills
        item {
            CommonCardContainer {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Split your bills",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                            textAlign = TextAlign.Center,
                            color = Color(0XFF1E1E1E),
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Text(
                                text = "Balance",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 14.sp,
                                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                                    textAlign = TextAlign.Center,
                                    color = Color(0XFF1E1E1E),
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(400)
                                )
                            )
                            Text(
                                text = "$1200.00",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 30.sp,
                                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                                    textAlign = TextAlign.Center,
                                    color = Color(0XFF1E1E1E),
                                    lineHeight = 36.sp,
                                    fontWeight = FontWeight(400)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        DoubleBorderIconButton(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color.White
                                )
                            },
                            text = "New"
                        )
                    }

                    Row(modifier = Modifier.height(180.dp)) {
                        //to Give
                        ToGive(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        ToGet(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        //your plan
        item {
            Text(
                text = "Your Plan",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                //fontFamily = FontFamily(Font(Res.font.Poppins_Regular))
            )
            Spacer(modifier = Modifier.height(16.dp))
            YourPlan()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        //request
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Request",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    //fontFamily = Poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "View All",
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color =  Color(0xFF69718A)
                    //fontFamily = Poppins
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "View All",
                    tint = Color(0xFF69718A)
                )
            }

            //request
            MoneyRequestItem()
            MoneyRequestItem()
            EventRequestItem()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityScreen() {

    val buttons = listOf(
        ButtonData("Overview") { },
        ButtonData("People") { },
    )
    var selectedIndex by remember { mutableStateOf(0) }


    CommonBackground {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CommunityScreenTopBar()
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column ( modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) )
            {

                SwitchMultiButton(
                    buttons = buttons,
                    selectedIndex = selectedIndex,
                    onButtonClick = { index ->
                        selectedIndex = index
                        buttons[index].onClick()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (selectedIndex == 0){
                    CommunityOverview(
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Text (" this is people")
                }

            }
        }
    }

}


@Preview
@Composable
private fun CommunityScreenPreview(){
    CommunityScreen()
}

