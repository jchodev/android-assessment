package com.jerry.assessment.screen.billSplit.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jerry.assessment.composes.ext.visible

@Composable
fun BillSpitStatusBarText(
    modifier: Modifier = Modifier,
    text: String = "Start"
) {
    Text (
        modifier = modifier,
        text = text,
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        color = Color.Black,
        //fontFamily = FontFamily(Font(Res.font.Inter)),
    )
}

@Composable
fun BillSplitStatusCircle (
    modifier: Modifier = Modifier,
    isTick: Boolean = true,
) {
    Box(
        modifier = Modifier.size(32.dp).then(modifier)
    ){
        if (isTick){
            Box(
                modifier = Modifier.fillMaxSize()
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        } else {
            Box(
                modifier = modifier
                    //.offset(y = (-16.dp), x = (16.dp))
                    .fillMaxSize()
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
fun BillSpitStatusBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0
) {

    val isPeopleTick = selectedIndex >= 1
    val isSplitTick = selectedIndex == 2
    val lineColor = MaterialTheme.colorScheme.primary

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val (startImage, startText,
            peopleImage, peopleText,
            splitImage, splitText,
            line1, line2
        ) = createRefs()

        //Start area
        BillSplitStatusCircle(
            modifier = Modifier.constrainAs(startImage) {
                start.linkTo(parent.start)
            },
            isTick = true
        )
        BillSpitStatusBarText(
            modifier = Modifier.constrainAs(startText) {
                top.linkTo(startImage.bottom)
                centerHorizontallyTo(startImage)
            },
            text = "Start"
        )

        //people area
        BillSplitStatusCircle(
            modifier = Modifier.constrainAs(peopleImage) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            },
            isTick = isPeopleTick
        )
        BillSpitStatusBarText(
            modifier = Modifier.constrainAs(peopleText) {
                top.linkTo(peopleImage.bottom)
                centerHorizontallyTo(peopleImage)
            },
            text = "People"
        )

        //Split area
        BillSplitStatusCircle(
            modifier = Modifier.constrainAs(splitImage) {
                end.linkTo(parent.end)
            },
            isTick = isSplitTick
        )
        BillSpitStatusBarText(
            modifier = Modifier.constrainAs(splitText) {
                top.linkTo(splitImage.bottom)
                centerHorizontallyTo(splitImage)
            },
            text = "Split"
        )


        // 第一條線（開始到人）
        Canvas(
            modifier = Modifier
                .height(2.dp)
                .constrainAs(line1) {
                    top.linkTo(startImage.top)
                    bottom.linkTo(startImage.bottom)
                    start.linkTo(startImage.end)
                    end.linkTo(peopleImage.start)
                    width = Dimension.fillToConstraints
                }
        ) {
            drawLine(
                color = lineColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2.dp.toPx()
            )
        }

        // 第二條線（人到分割）
        Canvas(
            modifier = Modifier
                .height(2.dp)
                .constrainAs(line2) {
                    top.linkTo(peopleImage.top)
                    bottom.linkTo(peopleImage.bottom)
                    start.linkTo(peopleImage.end)
                    end.linkTo(splitImage.start)
                    width = Dimension.fillToConstraints
                }
        ) {
            drawLine(
                color = lineColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2.dp.toPx()
            )
        }

    }

}


@Preview
@Composable
private fun BillSpitStatusBarPreviewStartStatus() {
    BillSpitStatusBar(
        selectedIndex = 0 // Change this to 0, 1, or 2 to see different states
    )
}

@Preview
@Composable
private fun BillSpitStatusBarPreviewPeopleStatus() {
    BillSpitStatusBar(
        selectedIndex = 1 // Change this to 0, 1, or 2 to see different states
    )
}

@Preview
@Composable
private fun BillSpitStatusBarPreviewSplileStatus() {
    BillSpitStatusBar(
        selectedIndex = 2 // Change this to 0, 1, or 2 to see different states
    )
}