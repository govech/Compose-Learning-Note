package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_text),
                        frome = stringResource(R.string.signature_text),
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun GreetingText(message: String, frome: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = frome,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun GreetingImage(message: String, frome: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.8f
        )

        GreetingText(
            message, frome, modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )

        Column(modifier = modifier.padding(10.dp)) {
            TiaoZhuanButton()
            ToWorkManagerButton()
            TestButton()
            ToCardButton()
        }
    }
}

@Composable
fun TestButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, TestActivity::class.java))
        }
    ) {
        Text(text = "跳转到TestActivity")
    }
}


@Composable
fun TiaoZhuanButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, WorkManageActivity::class.java))
        }

    ) {
        Text(text = "跳转到WorkManage")
    }
}

@Composable
fun ToWorkManagerButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, ComposeArticleActivity::class.java))
        }
    ) {
        Text(text = "跳转到目标Compose文章")
    }
}

@Composable
fun ToCardButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, CardActivity::class.java))
        }
    ) {
        Text(text = "跳转到Card页面")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HappyBirthdayTheme {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            frome = stringResource(R.string.signature_text)
        )
    }
}