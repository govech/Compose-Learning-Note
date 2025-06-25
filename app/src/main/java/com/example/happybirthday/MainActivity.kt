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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.happybirthday.game.WordGameActivity
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

        NavigationList(modifier=Modifier.padding(8.dp))

    }
}


@Composable
fun NavigationButton(
    targetActivity: Class<*>,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, targetActivity))
        },
        modifier = modifier
    ) {
        Text(text = buttonText)
    }
}


data class NavigationItem(
    val title: String,
    val targetActivity: Class<*>
)

@Composable
fun NavigationList(modifier: Modifier = Modifier) {
    val itemsNavigation = listOf(
        NavigationItem("跳转到WorkManage", WorkManageActivity::class.java),
        NavigationItem("跳转到目标Compose文章", ComposeArticleActivity::class.java),
        NavigationItem("跳转到TestActivity", TestActivity::class.java),
        NavigationItem("跳转到目标Card页面", CardActivity::class.java),
        NavigationItem("跳转到DiceRoller", DiceRollerActivity::class.java),
        NavigationItem("跳转到TipTime", TipTimeActivity::class.java),
        NavigationItem("跳转到Affirmations", AffirmationsActivity::class.java),
        NavigationItem("跳转到TopicListMainActivity", TopicListMainActivity::class.java),
        NavigationItem("跳转到WoofActivity", WoofActivity::class.java),
        NavigationItem("跳转到WordGameActivity", WordGameActivity::class.java),
    )
    LazyColumn(modifier = modifier) {
        items(itemsNavigation) { item ->
            NavigationButton(
                targetActivity = item.targetActivity,
                buttonText = item.title
            )
        }
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