package com.example.devbox.features.websocket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WebSocketScreen() {

    val viewModel:
            WebSocketViewModel =
        viewModel()

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
                    .background(MaterialTheme.colorScheme.background)
        ) {

            Text(

                text = "WebSocket Tester",

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

                value = viewModel.url,

                onValueChange = {

                    viewModel.url = it
                },

                label = {

                    Text("WebSocket URL")
                },

                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(

                text =
                    "Status: ${viewModel.connectionStatus}",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Row {

                Button(

                    onClick = {

                        viewModel.connect()
                    }
                ) {

                    Text("Connect")
                }

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                Button(

                    onClick = {

                        viewModel.disconnect()
                    }
                ) {

                    Text("Disconnect")
                }
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            OutlinedTextField(

                value =
                    viewModel.messageInput,

                onValueChange = {

                    viewModel.messageInput = it
                },

                label = {

                    Text("Message")
                },

                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Button(

                onClick = {

                    viewModel.sendMessage()
                }
            ) {

                Text("Send")
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            LazyColumn(

                modifier =
                    Modifier.weight(1f)
            ) {

                items(
                    viewModel.messages
                ) { message ->

                    Card(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                    ) {

                        Text(

                            text = message,

                            modifier =
                                Modifier.padding(
                                    12.dp
                                )
                        )
                    }
                }
            }
        }
    }

}