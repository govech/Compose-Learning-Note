package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositeLayout()
                }

            }
        }
    }
}


// 定义卡片使用的颜色常量
private val cardColors = listOf(
    Color(0xFFEADDFF), // TopLeft
    Color(0xFFD0BCFF), // TopRight
    Color(0xFFB69DF8), // BottomLeft
    Color(0xFFF6EDFF)  // BottomRight
)


/**
 * 可重用的卡片组件
 *
 * @param title 卡片标题
 * @param content 卡片内容
 * @param color 卡片背景色
 * @param modifier 修饰符
 */
@Composable
fun InfoCard(
    title: String,
    content: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp)

    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}


/**
 * 组合布局，将屏幕分成四等份
 *
 * @param modifier 修饰符
 */
@Composable
fun CompositeLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        // 顶部行：两等分
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = "Text composable",
                content = "Displays text and follows the recommended Material Design guidelines.",
                color = cardColors[0],
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                title = "Image composable",
                content = "Creates a composable that lays out and draws a given Painter class object.",
                color = cardColors[1],
                modifier = Modifier.weight(1f)
            )
        }

        // 底部行：两等分
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = "Row composable",
                content = "A layout composable that places its children in a horizontal sequence.",
                color = cardColors[2],
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                title = "Column composable",
                content = "A layout composable that places its children in a vertical sequence.",
                color = cardColors[3],
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Preview
@Composable
fun TestPreview() {
    HappyBirthdayTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CompositeLayout(modifier = Modifier.padding(innerPadding))
        }
    }
}