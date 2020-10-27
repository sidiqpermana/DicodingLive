package com.nbs.dicodinglive

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nbs.dicodinglive.ui.DicodingLiveTheme
import kotlin.math.sqrt

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun App(){
    DicodingLiveTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            AppContent()
        }
    }
}

@Composable
fun AppContent(){
    Scaffold(
        topBar = {
            appBar()
        },
        bodyContent = {
            FormLayout()
        }
    )
}

@Composable
fun appBar(){
    TopAppBar(
        title = {
            Text(text = "Radius Calculator")
        }
    )
}

@Composable
fun FormLayout(){
    val result = remember { mutableStateOf(0.0) }
    val radius = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TextField(value = radius.value, onValueChange = {
            radius.value = it
        }, label = {
            Text(text = "Radius")
        }, modifier = Modifier.fillMaxWidth(), keyboardType = KeyboardType.Number)

        Divider(color = Color.Transparent, modifier = Modifier.preferredHeight(16.dp))

        Row {
            Button(onClick = {
                if (radius.value.isNotEmpty()){
                    result.value = Math.PI * sqrt(radius.value.toDouble())
                }
            }) {
                Text(text = "Hitung")
            }

            Spacer(modifier = Modifier.preferredWidth(16.dp))

            Button(onClick = {
                radius.value = ""
                result.value = 0.0
            }) {
                Text(text = "Hapus")
            }
        }

        Divider(color = Color.Transparent, modifier = Modifier.preferredHeight(16.dp))

        showResult(result.value)
    }
}

@Composable
fun showResult(value: Double) {
    Text(text = value.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DicodingLiveTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    DicodingLiveTheme {
        FormLayout()
    }
}