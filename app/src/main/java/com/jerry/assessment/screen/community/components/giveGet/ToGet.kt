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

@Composable
fun ToGet(
    modifier: Modifier = Modifier
) {
    DoubleBoarderCard(
        modifier = modifier,
        content = {
            ToGetContent()
        }
    )
}

@Composable
fun ToGetContent(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF5F5F5)
            )
    ){
        val (title, arrow, moneyText, avatarGroup, viewText, bottomButton) = createRefs()

        CircleArrow(
            modifier = Modifier
                .offset(y = (-16.dp), x = (-16.dp))
                .constrainAs(arrow) {}
            ,
            color = Color.Black,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left_down),
                    contentDescription = "To Give",
                    tint = Color.White
                )
            }
        )

        Text(
            modifier = Modifier
                .padding(start = 36.dp, top = 16.dp)
                .constrainAs(title) {
                    end.linkTo(parent.end, 16.dp)
                },
            text = "To Get",
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )


        Text(
            modifier = Modifier
                .constrainAs(moneyText){
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(title.bottom, 8.dp)
                },
            text = "$500.23",
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            fontSize = 20.sp,
            color = Color.Black
        )

        AvatarGroup(
            modifier = Modifier
                .constrainAs(avatarGroup){
                    top.linkTo(moneyText.bottom, 8.dp)
                    end.linkTo(parent.end, 16.dp)
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
                .constrainAs(viewText) {
                    end.linkTo(avatarGroup.start, 8.dp)
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
                .offset(y = (2.dp), x = (-16.dp))
                .constrainAs(bottomButton) {
                    start.linkTo(parent.start)
                    top.linkTo(viewText.bottom, 16.dp)
                },
            color = Color.Black,
            icon = {},
            text = "Remind"
        )
    }
}


@Preview
@Composable
private fun ToGetPreview(){
    ToGet()
}

@Preview
@Composable
private fun ToGetContentPreview(){
    ToGetContent()
}