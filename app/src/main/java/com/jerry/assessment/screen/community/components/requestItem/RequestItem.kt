package com.jerry.assessment.screen.community.components.requestItem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.composes.card.DoubleBoarderCard
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import com.jerry.assessment.composes.avatar.AvatarGroup

@Composable
fun MoneyRequestItem() {
    DoubleBoarderCard {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFEBF4EF),
                            Color(0xFFF0F5F2),
                            Color(0xFFF6F6F6),
                        )
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape)
                ,
                model = "https://aaa.comm",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            VerticalDivider(modifier = Modifier.height(48.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = "Walter Kozey",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 15.sp,
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                )
                Text(
                    text = "Food and Drinks",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 15.sp,
                    fontWeight = FontWeight(300),
                    fontSize = 10.sp,
                )
                Text(
                    text = "$50.00",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                )
            }
            RequestItemTail()
        }
    }
}

@Composable
fun EventRequestItem() {
    DoubleBoarderCard {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFF4F0FE),
                        )
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.width(42.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "32",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                )
                Text(
                    text = "Sat",
                    //fontFamily = FontFamily(Font(Res.font.Inter)),
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(300),
                    fontSize = 12.sp,
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))
            VerticalDivider(modifier = Modifier.height(48.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Row (
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.Center
            ){
                Column {
                    Text("trip to london", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clock),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("12:00 - 16:00", color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                AvatarGroup(
                    avatars = listOf(
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                    )
                )
            }
            RequestItemTail()
        }
    }
}

@Preview
@Composable
private fun MoneyRequestItemPreview(){
    MoneyRequestItem()
}

@Preview
@Composable
private fun EventRequestItemPreview(){
    EventRequestItem()
}