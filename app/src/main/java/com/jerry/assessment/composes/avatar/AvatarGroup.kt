package com.jerry.assessment.composes.avatar


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.SubcomposeAsyncImage


@Composable
fun AvatarGroup(
    avatarSize: Dp = 36.dp,
    modifier: Modifier = Modifier,
    avatars: List<String>
){
    val offsetX = avatarSize / 2
    val maxAvatars = 4

    val displayAvatars = avatars.take(maxAvatars).takeIf { it.size >= 2 } ?: return

    Box(
        modifier = modifier
            .width(avatarSize + offsetX * (displayAvatars.size - 1))
            .height(avatarSize)
    ) {
        displayAvatars.forEachIndexed { index, avatarImageUrl ->
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(avatarSize)
                    .align(Alignment.CenterEnd)
                    .offset(x = -offsetX * index)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape)
                    .zIndex(displayAvatars.size.toFloat() - index),
                model = avatarImageUrl,
                contentDescription = "Avatar ${index + 1}",
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
        }
    }
}

@Preview
@Composable
private fun AvatarGroupPreview(){
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