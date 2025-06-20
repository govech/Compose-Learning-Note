package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class ComposeArticleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Zuhe(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }


}

@Composable
private fun BackImg(modifier: Modifier = Modifier) {
    val img = painterResource(R.drawable.androidparty)
    Image(
        painter = img,
        alpha = 0.3f,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun TomTitle(modifier: Modifier = Modifier) {
    val title = "Jetpack Compose tutorial"
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun ContentText(modifier: Modifier = Modifier) {
    val content =
        "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs."
    Text(
        text = content,
        textAlign = TextAlign.Justify,      //文本对齐方式设置为Justify
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)

    )
}

@Composable
fun BottomText(modifier: Modifier = Modifier) {
    val content =
        "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."
    Text(
        text = content,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(16.dp)
    )
}


@Composable
fun Zuhe(modifier: Modifier = Modifier) {
    Box {
        BackImg()
        Column {
            TomTitle()
            ContentText()
            BottomText()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    HappyBirthdayTheme {
        Zuhe()
    }
}
