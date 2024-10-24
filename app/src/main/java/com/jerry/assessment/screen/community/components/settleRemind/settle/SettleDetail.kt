package com.jerry.assessment.screen.community.components.settleRemind.settle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jerry.assessment.R
import com.jerry.assessment.composes.button.CommonOutlineButton
import com.jerry.assessment.composes.checkbox.CircleCheckBox
import com.jerry.assessment.composes.checkbox.CustomerCheckBox
import com.jerry.assessment.composes.icon.SmallRounderCardIcon
import com.jerry.assessment.screen.community.components.settleRemind.SettleItemData
import com.jerry.assessment.screen.community.components.settleRemind.SettleRemindItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettleDetail(
    data: SettleItemData,
    fullPaymentAmount : String,

) {

}

@Composable
private fun SettleDetailContent(
    item: SettleItemData,
    fullPaymentAmount : String,
    onFullPaymentPayNowClick: () -> Unit,
    onPartialPaymentPayNowClick: (Double) -> Unit,
){
    Column(modifier = Modifier
        .background(color = Color(0xFFF5F5F5))
        .padding(16.dp)
        .fillMaxWidth()
    )
    {
        SettleRemindItem(data = item, showTail = false)
        HorizontalDivider()
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleCheckBox(false)
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Full Payment",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    color = Color.Black,
                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = fullPaymentAmount,
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.weight(1f))
                CommonOutlineButton(
                    text = "Pay Now",
                    onClick = onFullPaymentPayNowClick,
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        PartialPaymentCard()

    }
}

data class PartialPaymentItemData (
    val title: String = "title",
    val amount: Double = 0.0,
    val selected: Boolean = false,
)

@Composable
private fun PartialPaymentItem(
    data: PartialPaymentItemData
) {

    Row (
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data.title,
            //fontFamily = FontFamily(Font(Res.font.Inter)),
            lineHeight = 19.sp,
            fontWeight = FontWeight(400),
            fontSize = 11.sp,
            color = Color(0XFF9D9D9D),
        )
        Spacer(modifier = Modifier.width(64.dp))
        Text(
            text = "$18",
            //fontFamily = FontFamily(Font(Res.font.Inter)),
            lineHeight = 24.sp,
            fontWeight = FontWeight(600),
            fontSize = 16.sp,
            color = Color(0XFF1F1F1F),
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomerCheckBox(
            isChecked = data.selected,
            shape = MaterialTheme.shapes.small,
        )
    }
}

@Preview
@Composable
private fun SettleDetailContentPreview(){
    SettleDetailContent(
        item = SettleItemData(),
        fullPaymentAmount = "$29",
        onFullPaymentPayNowClick = {},
        onPartialPaymentPayNowClick = {}
    )
}

@Composable
private fun PartialPaymentCard(){
    val lineColor = MaterialTheme.colorScheme.primary
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            val (
                checkbox,
                title,
                dot,
                line,
                icon,
                expenseArea,
                bottomText,
                totalAmount,
                payNowButton
            ) = createRefs()

            CustomerCheckBox(
                modifier = Modifier
                    .constrainAs(checkbox){},
                isChecked = false,
            )

            Text(
                modifier = Modifier.constrainAs(title){
                    start.linkTo(checkbox.end, 8.dp)
                    top.linkTo(checkbox.top)
                    bottom.linkTo(checkbox.bottom)
                },
                text = "Partial Payment",
                //fontFamily = FontFamily(Font(Res.font.Inter)),
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                fontSize = 12.sp,
                color = Color.Black,
            )

            Box(
                modifier = Modifier
                    .constrainAs(dot) {
                        start.linkTo(checkbox.start)
                        end.linkTo(checkbox.end)
                        top.linkTo(checkbox.bottom, 4.dp)
                    }
                    .size(12.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Box(
                modifier = Modifier
                    .constrainAs(expenseArea) {
                        start.linkTo(bottomText.start)
                        top.linkTo(checkbox.bottom, 24.dp)
                        end.linkTo(parent.end,)
                        width = Dimension.fillToConstraints  // 填充可用寬度
                    }
                    .height(160.dp)

            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        PartialPaymentItem(data = PartialPaymentItemData())
                    }
                    item {
                        PartialPaymentItem(data = PartialPaymentItemData(selected = true))
                    }
                    item {
                        PartialPaymentItem(data = PartialPaymentItemData(selected = true))
                    }
                    item {
                        PartialPaymentItem(data = PartialPaymentItemData())
                    }
                }
            }

            SmallRounderCardIcon(
                modifier = Modifier.constrainAs(icon){
                    start.linkTo(checkbox.start)
                    end.linkTo(checkbox.end)
                    top.linkTo(expenseArea.bottom, 32.dp)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_ticket),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                )
            }

            Canvas(
                modifier = Modifier
                    //.width(2.dp)
                    .constrainAs(line) {
                        top.linkTo(dot.bottom)
                        bottom.linkTo(icon.top)
                        start.linkTo(dot.start)
                        end.linkTo(dot.end)
                        height = Dimension.fillToConstraints
                    }
            ) {
                drawLine(
                    color = lineColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }

            Text(
                modifier = Modifier.constrainAs(bottomText){
                    start.linkTo(icon.end, 18.dp)
                    top.linkTo(icon.top)
                    bottom.linkTo(icon.bottom)
                },
                text = "Partial Payment",
                //fontFamily = FontFamily(Font(Res.font.Inter)),
                lineHeight = 19.sp,
                fontWeight = FontWeight(400),
                fontSize = 11.sp,
                color = Color(0XFF9D9D9D),
            )

            Text(
                modifier = Modifier.constrainAs(totalAmount){
                    start.linkTo(bottomText.end, 8.dp)
                    top.linkTo(icon.top)
                    bottom.linkTo(icon.bottom)
                },
                text = "$18",
                //fontFamily = FontFamily(Font(Res.font.Inter)),
                lineHeight = 24.sp,
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                color = Color(0XFF1F1F1F),
            )

            CommonOutlineButton(
                modifier = Modifier.constrainAs(payNowButton){
                    end.linkTo(parent.end)
                    top.linkTo(icon.top)
                    bottom.linkTo(icon.bottom)
                },
                text = "Pay Now"
            )
            
        }
    }
}