package com.example.happybirthday.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun Ruler(
    minValue: Int = 0, // 最小值
    maxValue: Int = 100, // 最大值
    majorStep: Int = 10, // 主刻度间隔
    minorStep: Int = 1, // 次刻度间隔
    initialValue: Int = 50, // 初始值
    onValueChange: (Int) -> Unit, // 选中值变化回调
    modifier: Modifier = Modifier
) {
    // 状态管理
    var currentValue by remember { mutableStateOf(initialValue) }
    val textMeasurer = rememberTextMeasurer()

    // Canvas 绘制
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    // 计算滑动导致的值变化
                    val pixelsPerUnit = size.width / (maxValue - minValue).toFloat()
                    val valueChange = (dragAmount / pixelsPerUnit).roundToInt()
                    val newValue = (currentValue + valueChange).coerceIn(minValue, maxValue)
                    currentValue = newValue
                    onValueChange(newValue)
                }
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val pixelsPerUnit = canvasWidth / (maxValue - minValue).toFloat()

        // 绘制刻度线
        for (value in minValue..maxValue step minorStep) {
            val x = (value - minValue) * pixelsPerUnit
            val isMajor = value % majorStep == 0

            // 刻度线高度：主刻度更高，次刻度较短
            val lineHeight = if (isMajor) canvasHeight * 0.4f else canvasHeight * 0.2f
            drawLine(
                color = Color.Gray,
                start = Offset(x, canvasHeight - lineHeight),
                end = Offset(x, canvasHeight),
                strokeWidth = if (isMajor) 4f else 2f
            )

            // 绘制主刻度的数值标签
            if (isMajor) {
                val text = value.toString()
                val textLayoutResult = textMeasurer.measure(text, style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp))
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(x - textLayoutResult.size.width / 2f, canvasHeight - lineHeight - 20f)
                )
            }
        }

        // 绘制中心指示器
        val currentX = (currentValue - minValue) * pixelsPerUnit
        drawLine(
            color = Color.Red,
            start = Offset(currentX, 0f),
            end = Offset(currentX, canvasHeight * 0.6f),
            strokeWidth = 4f
        )
    }
}