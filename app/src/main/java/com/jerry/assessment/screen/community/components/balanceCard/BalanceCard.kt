package com.jerry.assessment.screen.community.components.balanceCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.composes.card.CommonCardContainer
import com.jerry.assessment.screen.community.components.giveGet.ToGet
import com.jerry.assessment.screen.community.components.giveGet.ToGive

@Composable
fun BalanceCard(
    balance: String,
    toGiveAmount: String,
    toGetAmount: String,
) {
    CommonCardContainer {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom){
                Column {
                    Text(
                        text = "Shared Bills",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                            textAlign = TextAlign.Center,
                            color = Color(0XFF14141F),
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Text(
                        text = "Balance",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            //fontFamily = FontFamily(Font(Res.font.Inter)),
                            textAlign = TextAlign.Center,
                            color = Color(0XFF6A6A6A),
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                //
                Text(
                        text = "$$balance",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 20.sp,
                            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            lineHeight = 36.sp,
                            fontWeight = FontWeight(400)
                        )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.height(180.dp)) {
                //to Give
                ToGive(modifier = Modifier.weight(1f), amount = toGiveAmount)
                Spacer(modifier = Modifier.width(8.dp))
                ToGet(modifier = Modifier.weight(1f), amount = toGetAmount)
            }
        }
    }
}

@Preview
@Composable
private fun BalanceCardPreview() {
    BalanceCard(
        balance = "1200",
        toGiveAmount = "100.00",
        toGetAmount = "200.00"
    )
}