package hr.ferit.kristinadudjak.mycloset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyClosetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Greeting("Android")
                        Text(text = "blab")
                        Button(onClick = {}) {
                            Text(text = "blab")
                        }
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "blub",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyClosetTheme {
        Greeting("Android")
    }
}