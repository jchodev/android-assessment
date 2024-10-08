package com.jerry.assessment.screen.community.components.giveGet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jerry.assessment.R
import com.jerry.assessment.composes.avatar.AvatarGroup
import com.jerry.assessment.composes.button.DoubleBorderIconButton
import com.jerry.assessment.composes.card.DoubleBoarderCard
import com.jerry.assessment.composes.ext.visible

@Composable
fun ToGive(
    modifier: Modifier = Modifier,
    amount: String,
) {
    DoubleBoarderCard(
        modifier = modifier,
        content = {
            ToGiveContent(amount = amount)
        }
    )
}

@Composable
fun ToGiveContent(
    modifier: Modifier = Modifier,
    amount: String,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth().background(
            color = Color(0xFFF5F5F5)
        )
    ){
        val (title, rightArrow, moneyText, avatarGroup, viewText, bottomButton) = createRefs()

        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .constrainAs(title) {},
            text = "To Give",
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        CircleArrow(
            modifier = Modifier
                .offset(y = (-16.dp), x = (16.dp))
                .constrainAs(rightArrow) {
                    end.linkTo(parent.end)
                }
            ,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right_up),
                    contentDescription = "To Give",
                    tint = Color.White
                )
            }
        )

        Text(
            modifier = Modifier
                .constrainAs(moneyText){
                    top.linkTo(title.bottom, 8.dp)
                    start.linkTo(parent.start , 16.dp)
                },
            text = "$$amount",
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color.Black
        )

        AvatarGroup(
            modifier = Modifier
                .constrainAs(avatarGroup){
                    top.linkTo(moneyText.bottom, 8.dp)
                    start.linkTo(parent.start , 16.dp)
                },
            avatars = listOf(
                "https://dummyimage.com/100x100/6699cc/000",
                "https://dummyimage.com/100x100/6699cc/000",
                "https://dummyimage.com/100x100/6699cc/000",
                "https://dummyimage.com/100x100/6699cc/000",
                "https://dummyimage.com/100x100/6699cc/000",
        ))

        Text(
            modifier = Modifier
                .visible(false)
                .constrainAs(viewText) {
                    start.linkTo(avatarGroup.end, 8.dp)
                    bottom.linkTo(avatarGroup.bottom)
                },
            text = "View",
            lineHeight = 16.sp,
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )

        DoubleBorderIconButton(
            modifier = Modifier
                .offset(y = (2.dp), x = (16.dp))
                .constrainAs(bottomButton) {
                    end.linkTo(parent.end)
                    top.linkTo(viewText.bottom, 16.dp)
                },
            icon = {}
        )
    }
}


@Preview
@Composable
private fun ToGivePreview(){
    ToGive(amount = "400.53")
}

@Preview
@Composable
private fun ToGiveContentPreview(){
    ToGiveContent(amount = "400.53")
}