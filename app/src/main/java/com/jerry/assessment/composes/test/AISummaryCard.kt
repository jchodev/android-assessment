package com.jerry.assessment.composes.test

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import com.jerry.assessment.ui.theme.AppTheme

@Composable
fun AISummaryExpandableCard(
    headline: String,
    body: String,

    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (!expandedState) 0f else 180f,
        label = "",
    )
    val shape = if (!expandedState) {
        RoundedCornerShape(12.dp)
    } else {
        RoundedCornerShape(24.dp)
    }
    val titleColor = if (!expandedState) {
        Color.Red
        //AppTheme.colors.aiSummaryTitleExpandedColor
    } else {
        Color.Yellow
        //AppTheme.colors.aiSummaryTitleCollapsedColor
    }
    val labelModifier = if (!expandedState) {
        Modifier
            .animateContentSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
            .background(Brush.horizontalGradient(
                //AppTheme.colors.aiSummaryCardBorderColor
                listOf(Color.Blue, Color.White)
            ))
    } else {
        Modifier
            .animateContentSize()
            .width(62.dp)
            .height(28.dp)
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(
                //.AppTheme.colors.aiSummaryCardBorderColor
                listOf(Color.Blue, Color.White)
            ))
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = if (!expandedState) {
                    tween(
                        durationMillis = 600,
                        easing = LinearOutSlowInEasing,
                    )
                } else tween(
                    durationMillis = 600,
                    easing = LinearOutSlowInEasing,
                ),
            ),
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.horizontalGradient(
                //AppTheme.colors.aiSummaryCardBorderColor
                listOf(Color.Blue, Color.White)
            ),
        ),
        colors = CardDefaults.cardColors(
            //containerColor = AppTheme.colors.aiSummaryCardBackgroundColor,
            containerColor = Color.Blue
        ),
        //onClick = onClick,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
            ) {
                Box(
                    modifier = labelModifier.align(Alignment.CenterStart),
                )
                Row(
                    Modifier
                        .width(62.dp)
                        .height(28.dp)
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription ="",
                    )
                    Text(
                        modifier = Modifier.padding(
                            top = 10.dp,
                        ),
                        text =" stringResource(R.string.ai_summary_label)",
                        color = Color.Blue,//AppTheme.colors.aiSummaryLabelColor,
                        style = TextStyle(
                            //fontFamily = NotoSansTamil,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                        ),
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                        //.testTag("$TEXT${stringResource(R.string.ai_summary_title)}"),
                    text = "title",
                    color = titleColor,
                    style = TextStyle(
                        //fontFamily = NotoSansTamil,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    ),
                    maxLines = 1,
                    //overflow = TextOverflow.Ellipsis,
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp)
                        .rotate(rotationState),
                        //.testTag("$ICON_BUTTON${stringResource(R.string.expand_collapse_button)}"),
                    onClick = onClick,
                ) {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick,
                        ),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        tint = titleColor,
                        contentDescription = "stringResource(id = R.string.expand_collapse_button)",
                    )
                }
            }

            if (!expandedState) {
                Column(
                    Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 16.dp,
                    ),
                ) {
                    Text(
                        //modifier = Modifier.testTag("$TEXT$headline"),
                        text = headline,
                        style = TextStyle(
                            //fontFamily = NotoSansTamil,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = Color.Blue//AppTheme.colors.aiSummaryHeadlineColor,
                        ),
                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                            //.testTag("$TEXT$body"),
                        text = body,
                        style = TextStyle(
                            //fontFamily = NotoSansTamil,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = Color.Red//AppTheme.colors.aiSummaryBodyColor,
                        ),
                    )
                    Text(
                        //modifier = Modifier.testTag("$TEXT${stringResource(R.string.ai_summary_footer)}"),
                        text = "stringResource(R.string.ai_summary_footer)",
                        style = TextStyle(
                            //fontFamily = NotoSansTamil,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = Color.Yellow// AppTheme.colors.aiSummaryFooterColor,
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AISummaryExpandableCardPreview() {
    AppTheme {
        AISummaryExpandableCard(
            headline = "Biden drops out of 2024 race after disastrous debate inflamed age concerns",
            body = "Joe Biden is the 46th president of the United States (2021– ). " +
                    "He was born on November 20, 1942, in Scranton, Pennsylvania, " +
                    "and he served as a U.S. senator representing Delaware from 1972 to 2009. " +
                    "He was vice president in the Barack Obama administration from 2009 to 2017. " +
                    "In July 2024 Biden announced he would not seek a second term as president.\n" +
                    "In a statement posted on social media on July 21, 2024, Joe Biden said that " +
                    "“while it has been my intention to seek reelection, " +
                    "I believe it is in the best interest of my party and the country for me to stand down and " +
                    "to focus solely on fulfilling my duties as President for the remainder of my term.”",
            //isExpanded = false,
        )
    }
}


@Preview
@Composable
private fun AISummaryExpandableCardExpandedIsFalsePreview() {
    AppTheme {
        AISummaryExpandableCard(
            headline = "Biden drops out of 2024 race after disastrous debate inflamed age concerns",
            body = "Joe Biden is the 46th president of the United States (2021– ). " +
                    "He was born on November 20, 1942, in Scranton, Pennsylvania, " +
                    "and he served as a U.S. senator representing Delaware from 1972 to 2009. " +
                    "He was vice president in the Barack Obama administration from 2009 to 2017. " +
                    "In July 2024 Biden announced he would not seek a second term as president.\n" +
                    "In a statement posted on social media on July 21, 2024, Joe Biden said that " +
                    "“while it has been my intention to seek reelection, " +
                    "I believe it is in the best interest of my party and the country for me to stand down and " +
                    "to focus solely on fulfilling my duties as President for the remainder of my term.”",
            //isExpanded = false,
        )
    }
}