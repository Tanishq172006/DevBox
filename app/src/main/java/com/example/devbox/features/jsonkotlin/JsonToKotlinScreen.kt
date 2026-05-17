package com.example.devbox.features.jsonkotlin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.ui.platform.LocalContext

@Composable
fun JsonToKotlinScreen() {

    var jsonInput by remember {
        mutableStateOf("")
    }

    var output by remember {
        mutableStateOf("")
    }

    val context =
        LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
    {

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .background(MaterialTheme.colorScheme.background)
        ) {

            Text(

                text = "JSON → Kotlin",

                color = MaterialTheme.colorScheme.onBackground,

                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            OutlinedTextField(

                value = jsonInput,

                onValueChange = {
                    jsonInput = it
                },

                label = {
                    Text("Paste JSON")
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp),

                textStyle =
                    LocalTextStyle.current.copy(
                        fontFamily =
                            FontFamily.Monospace
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Button(

                onClick = {

                    output =
                        JsonToKotlinUtils
                            .generateDataClass(
                                jsonInput
                            )
                }
            ) {

                Text("Generate")
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            OutlinedTextField(

                value = output,

                onValueChange = {},

                readOnly = true,

                label = {
                    Text("Generated Kotlin")
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(350.dp),

                textStyle =
                    LocalTextStyle.current.copy(
                        fontFamily =
                            FontFamily.Monospace
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Button(

                onClick = {

                    val clipboard =
                        context.getSystemService(
                            Context.CLIPBOARD_SERVICE
                        ) as ClipboardManager

                    clipboard.setPrimaryClip(

                        ClipData.newPlainText(
                            "kotlin",
                            output
                        )
                    )
                }
            ) {

                Text("Copy Code")
            }
        }
    }

}