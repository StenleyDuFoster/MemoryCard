package com.memory.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.memory.card.component.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import memory.card.core.ui.theme.MemoryCardTheme
import memory.card.navigation.navigator.rememberNavigator
import memory.card.navigation.route.GroupRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryCardTheme {
                val navigator = rememberNavigator(GroupRoute())

                AppNavHost(
                    navigator,
                    Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoryCardTheme {
        Greeting("Android")
    }
}