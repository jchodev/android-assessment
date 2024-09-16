package com.jerry.assessment.composes.group


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jerry.assessment.ui.theme.AppTheme

@Composable
fun GroupTile(
    modifier: Modifier = Modifier,
    groupItem: GroupItem
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .border(
                border = BorderStroke(4.dp, Color.White),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0xFFEBF4EF),
                            Color(0xFFF0F5F2),
                            Color(0xFFF6F6F6)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChatGroup(members = groupItem.members)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "College Friends",
                    color = Color(0xff1d1a31),
                    lineHeight = 1.43.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = groupItem.plans.toString(),
                    color = Color.White,
                    lineHeight = 12.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredSize(size = 15.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = Color(0xff7539f5))

                )
                Text(
                    text = "Plans",
                    color = Color(0xff686675),
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = groupItem.messages.toString(),
                    color = Color.White,
                    lineHeight = 12.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredSize(size = 15.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = Color(0xff7539f5))
                )
                Text(
                    text = "Messages",
                    color = Color(0xff686675),
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = groupItem.expenses.toString(),
                    color = Color.White,
                    lineHeight = 12.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredSize(size = 15.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = Color(0xff7539f5))
                )
                Text(
                    text = "Expenses",
                    color = Color(0xff686675),
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun GroupTilePreview() {
    AppTheme {
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

data class GroupItem(
    val plans: Int,
    val messages: Int,
    val expenses: Int,
    val members: List<Member>
)