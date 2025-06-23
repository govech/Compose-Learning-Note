package com.example.happybirthday.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

/**
 * 为Modifier添加点击缩放效果的函数
 *
 * 此函数通过动画缩放UI元素，以响应点击事件它使用协程和动画来实现缩放效果
 *
 * @param scaleDown 缩放后的尺寸比例默认为0.95f，表示缩小到原大小的95%
 * @param animationDuration 动画持续时间，默认为100毫秒
 * @param onClick 点击事件的回调函数，在缩放动画完成后调用
 * @return 返回带有点击缩放效果的Modifier
 */
fun Modifier.clickScale(
    scaleDown: Float = 0.95f,
    animationDuration: Int = 60,
    onClick: () -> Unit
): Modifier = composed {
    // 创建并记住缩放动画的状态
    val scale = remember { Animatable(1f) }
    // 创建并记住协程的作用域，用于执行动画和点击事件
    val coroutineScope = rememberCoroutineScope()

    this
        .graphicsLayer(
            scaleX = scale.value,
            scaleY = scale.value
        )
        .pointerInput(Unit) {
            // 检测点击事件并处理缩放动画
            detectTapGestures(
                onPress = {
                    try {
                        // 在按下时启动缩放动画
                        coroutineScope.launch {
                            scale.animateTo(scaleDown, animationSpec = tween(animationDuration))
                        }
                        // 等待点击事件释放，如果成功则恢复原大小并调用点击事件回调
                        val success = tryAwaitRelease()
                        coroutineScope.launch {
                            scale.animateTo(1f, animationSpec = tween(animationDuration))
                        }
                        if (success) onClick()
                    } catch (e: CancellationException) {
                        // 如果动画被取消，恢复原大小
                        coroutineScope.launch {
                            scale.animateTo(1f, animationSpec = tween(animationDuration))
                        }
                    }
                }
            )
        }
}



