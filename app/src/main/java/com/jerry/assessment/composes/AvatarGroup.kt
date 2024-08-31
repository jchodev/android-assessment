package com.jerry.assessment.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jerry.assessment.R


@Composable
fun AvatarGroup(avatars: List<Int>){
    val avatarSize = 36.dp
    val offsetX = 18.dp
    val maxAvatars = 4

    val displayAvatars = avatars.take(maxAvatars).takeIf { it.size >= 2 } ?: return

    Box(
        modifier = Modifier
            .width(avatarSize + offsetX * (displayAvatars.size - 1))
            .height(avatarSize)
    ) {
        displayAvatars.forEachIndexed { index, avatarRes ->
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Avatar ${index + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(avatarSize)
                    .align(Alignment.CenterEnd)
                    .offset(x = -offsetX * index)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape)
                    .zIndex(displayAvatars.size.toFloat() - index)
            )
        }
    }
}

@Preview
@Composable
private fun AvatarGroupPreview(){
    AvatarGroup(
        listOf(
            R.drawable.male1,
            R.drawable.male1,
            R.drawable.male1,
            R.drawable.male1,
            R.drawable.male1,
        )
    )
}