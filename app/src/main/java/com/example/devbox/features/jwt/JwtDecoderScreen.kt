package com.example.devbox.features.jwt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.devbox.features.jsonformatter.JsonSyntaxHighlighter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JwtDecoderScreen() {

    val viewModel = remember {
        JwtDecoderViewModel()
    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("JWT Decoder")
                }
            )
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            OutlinedTextField(

                value = viewModel.jwtInput,

                onValueChange = {

                    viewModel.jwtInput = it
                },

                label = {
                    Text("Paste JWT")
                },

                modifier =
                    Modifier.fillMaxWidth(),

                minLines = 5
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(

                onClick = {

                    viewModel.decode()
                }
            ) {

                Text("Decode JWT")
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                AssistChip(

                    onClick = {},

                    colors = AssistChipDefaults.assistChipColors(

                        containerColor = when (

                            viewModel.expirationStatus

                        ) {

                            "Valid" -> Color(
                                0xFF4CAF50
                            )

                            "Expired" -> Color(
                                0xFFF44336
                            )

                            "No expiration" -> Color(
                                0xFFFFC107
                            )

                            else -> MaterialTheme.colorScheme.surfaceVariant
                        },

                        labelColor = Color.White
                    ),

                    label = {

                        Text(
                            "Status: ${viewModel.expirationStatus}"
                        )
                    }
                )

                AssistChip(

                    onClick = {},

                    label = {

                        Text(
                            viewModel.expirationDate
                        )
                    }
                )
            }

            Text(
                text = "Header",
                style =
                    MaterialTheme.typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(

                    text =
                        JsonSyntaxHighlighter
                            .highlight(
                                viewModel.header
                            ),

                    modifier =
                        Modifier.padding(16.dp),

                    fontFamily =
                        FontFamily.Monospace
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Payload",
                style =
                    MaterialTheme.typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(

                    text =
                        JsonSyntaxHighlighter
                            .highlight(
                                viewModel.payload
                            ),

                    modifier =
                        Modifier.padding(16.dp),

                    fontFamily =
                        FontFamily.Monospace
                )
            }
        }
    }
}