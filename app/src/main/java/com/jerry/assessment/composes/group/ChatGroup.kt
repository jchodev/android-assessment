package com.jerry.assessment.composes.group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R
import com.jerry.assessment.ui.theme.AppTheme


@Composable
fun ChatGroup(
    modifier: Modifier = Modifier,
    members: List<Member>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((-12).dp),
    ) {
        items(members) {
            ProfileGroupItem(member = it)
        }
    }
}


@Preview
@Composable
private fun ChatGroupPreview() {
    AppTheme {
        ChatGroup(
            members = listOf(
                Member(name = "John", gender = Gender.MALE),
                Member(name = "Sri", gender = Gender.FEMALE),
                Member(name = "Steve", gender = Gender.MALE),
                Member(name = "Shela", gender = Gender.FEMALE),
            )
        )
    }
}

@Composable
fun ProfileGroupItem(
    modifier: Modifier = Modifier,
    member: Member,
) {
    val avatar = when (member.gender) {
        Gender.MALE -> painterResource(R.drawable.male1)
        Gender.FEMALE -> painterResource(R.drawable.male1)
    }

    val backgroundColor = when (member.gender) {
        Gender.MALE -> Color(0xff91b797)
        Gender.FEMALE -> Color(0xFFFA8CA9)
    }

    val borderStrokeColor = when (member.gender) {
        Gender.MALE -> Color(0xff9cd1b3)
        Gender.FEMALE -> Color(0xFFB2473F)
    }

    Box(
        modifier = modifier
            .requiredSize(size = 28.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .background(color = backgroundColor)
            .border(
                border = BorderStroke(1.dp, borderStrokeColor),
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Image(
            painter = avatar,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

enum class Gender {
    MALE, FEMALE
}

data class Member(
    val name: String,
    val gender: Gender
)


@Preview
@Composable
private fun ProfileGroupItemPreview() {
    AppTheme {
        ProfileGroupItem(
            member = Member(name = "John", gender = Gender.MALE)
        )
    }
}

