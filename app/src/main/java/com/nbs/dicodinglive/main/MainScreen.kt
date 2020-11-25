package com.nbs.dicodinglive.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nbs.dicodinglive.R
import com.nbs.dicodinglive.ui.DicodingLiveTheme
import kotlin.math.roundToLong
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
                EllipseCalculation()
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
fun EllipseCalculation(){
    val result = remember { mutableStateOf(0.0) }
    val radius = remember { mutableStateOf("") }

    FormLayout(radius = radius.value,
            onRadiusChanged = {
                radius.value = it
            }, result = result.value,
            onResultChanged = {
                result.value = it
            }, calculate = {
        result.value = (Math.PI * sqrt(radius.value.toDouble())).roundToLong().toDouble()
    })
}

@Composable
fun FormLayout(radius: String, onRadiusChanged: (String) -> Unit,
               result: Double, onResultChanged: (Double) -> Unit,
               calculate: () -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TextField(value = radius, onValueChange = {
            onRadiusChanged.invoke(it)
        }, label = {
            Text(text = stringResource(id = R.string.label_radius))
        }, modifier = Modifier.fillMaxWidth().testTag("RadiusField"), keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
        ))

        Divider(color = Color.Transparent, modifier = Modifier.preferredHeight(16.dp))

        Row {
            Button(onClick = {
                if (radius.isNotEmpty()){
                    calculate.invoke()
                }
            }) {
                Text(text = stringResource(id = R.string.label_calculate))
            }

            Spacer(modifier = Modifier.preferredWidth(16.dp))

            Button(onClick = {
                onRadiusChanged.invoke("")
                onResultChanged.invoke(0.0)
            }) {
                Text(text = stringResource(id = R.string.label_delete))
            }
        }

        Divider(color = Color.Transparent, modifier = Modifier.preferredHeight(16.dp))

        showResult(result)
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
        EllipseCalculation()
    }
}