package com.jerry.assessment.screen.community.components.settleRemind

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.composes.button.CommonOutlineButton
import com.jerry.assessment.composes.text.TextWithStyle
import com.jerry.assessment.composes.text.textMultiStyle


@Composable
private fun keyWordStyle(): TextStyle {
    return MaterialTheme.typography.titleMedium.copy(
        //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
        lineHeight = 16.sp,
        fontWeight = FontWeight(700),
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.primary,
    )
}


@Composable
fun normalWordStyle(): TextStyle  {
    return MaterialTheme.typography.titleMedium.copy(
        //fontFamily = FontFamily(Font(Res.font.Inter)),
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Composable
private fun NameText(name: String){
    Text(
        text = name,
        fontSize = 14.sp,
        //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
        color = Color(0XFF69718A),
        lineHeight = 20.sp,
        fontWeight = FontWeight(400)
    )
}

@Composable
private fun AmountText(isSettle: Boolean = true, amount: String){
    val normalText = if (isSettle) "You owe " else "Owes you"
    Text(
        text = textMultiStyle(
            originalText = "$normalText ${amount}",
            customTextList = listOf(
                TextWithStyle(
                    customText = amount,
                    style = keyWordStyle()
                ),
                TextWithStyle(
                    customText = normalText,
                    style = normalWordStyle()
                ),
            )
        )
    )
}

@Composable
fun SettleRemindItem(
    data: SettleItemData,
    showTail:Boolean = true,
//    imageUrl: String = "https://dummyimage.com/100x100/6699cc/000",
//    name: String = "name",
//    amount: String = "$100",
//    isSettle: Boolean = true,
) {
    ListItem(
        leadingContent = {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                model = data.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
        },
        headlineContent = {
            if (data.isSettle){
                NameText(data.name)
            } else {
                AmountText(amount = data.amount, isSettle = data.isSettle)
            }
        },
        supportingContent = {
            if (data.isSettle){
                AmountText(amount = data.amount, isSettle = data.isSettle)
            } else {
                NameText(data.name)
            }
        },
        trailingContent = {
            if (showTail) {
                CommonOutlineButton(
                    text = if (data.isSettle) "Settle" else "Remind",
                    onClick = {}
                )
            }
        }
    )
}

@Preview
@Composable
private fun SettleItemPreview(){
    SettleRemindItem(
        data = SettleItemData(
            isSettle = true
        )
    )
}

@Preview
@Composable
private fun RemindItemPreview(){
    SettleRemindItem(
        data = SettleItemData(
            isSettle = false
        )
    )
}

data class SettleItemData(
    val imageUrl: String = "https://dummyimage.com/100x100/6699cc/000",
    val name: String = "name",
    val amount: String = "$100",
    val isSettle: Boolean = true,
)