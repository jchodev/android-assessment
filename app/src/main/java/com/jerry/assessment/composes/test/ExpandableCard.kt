package com.jerry.assessment.composes.test

import android.graphics.Paint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.composes.ext.visible

@Composable
fun ExpandableCard(
    headline: String,
    titleFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    body: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.titleSmall.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    padding: Dp = 12.dp
) {
    var expandedState by remember { mutableStateOf(false) }


    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    val progress by animateFloatAsState(
        targetValue = if (expandedState) 1f else 0f,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        // card content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(padding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = if (expandedState) {
                                listOf(Color(0xFFFF9A9E), Color(0xFFFAD0C4), Color(0xFFFAD0C4))
                            } else {
                                listOf(Color(0xFFFF758C), Color(0xFFFF7EB3))
                            }
                        )
                    ),
            ) {
                CustomLinearProgressIndicator(
                    progress = progress,
                    expandedState = expandedState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(24.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                    text = "Try me",
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }

            if (expandedState) {
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

@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    expandedState: Boolean,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = expandedState, label = "expandTransition")

    val gradientProgress by transition.animateFloat(
        label = "gradientProgress",
        transitionSpec = { tween(durationMillis = 500) }
    ) { expanded ->
        if (expanded) 1f else 0f
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
            .visible( progress > 0 && progress < 1)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(
                    brush = Brush.horizontalGradient(
                        colorStops = if (expandedState) {
                            arrayOf(
                                0f to Color.Transparent,
                                0.1f to Color(0xFFFF9A9E),
                                0.5f to Color(0xFFFAD0C4),
                                0.9f to Color(0xFFFAD0C4),
                                1f to Color.Transparent
                            )
                        } else {
                            arrayOf(
                                0f to Color.Transparent,
                                0.1f to Color(0xFFFAD0C4),
                                0.5f to Color(0xFFFAD0C4),
                                0.9f to Color(0xFFFF9A9E),
                                1f to Color.Transparent
                            )
                        },
                        startX = 0f,
                        endX = with(LocalDensity.current) { 300.dp.toPx() } * gradientProgress
                    )
                )
        )
    }
}

