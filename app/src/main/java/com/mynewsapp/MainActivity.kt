package com.mynewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynewsapp.ui.theme.MyNewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Column(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()) {
            Text(text = "Top news")
            Row {
                Text(text = "First news")
                Text(text = "Second news")
            }
        }
        Column(modifier = Modifier
            .background(color = Color.Cyan)
            .padding(10.dp)) {
            Text(
                text = "Hello $name! 4",
                modifier = modifier
            )
            Text(text = "this  is a news app")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewsAppTheme {
        Greeting("Android")
    }
}