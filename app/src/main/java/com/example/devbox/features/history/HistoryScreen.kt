package com.example.devbox.features.history

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HistoryScreen() {

    val context =
        LocalContext.current

    val viewModel:
            HistoryViewModel = viewModel(

        factory =
            HistoryViewModelFactory(

                context.applicationContext
                        as Application
            )
    )

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

            Row(

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(

                    text = "Request History",

                    color = MaterialTheme.colorScheme.onBackground,

                    style =
                        MaterialTheme
                            .typography
                            .headlineMedium
                )

                Button(

                    onClick = {
                        viewModel.clearAll()
                    }
                ) {

                    Text("Clear All")
                }
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            LazyColumn {

                items(
                    viewModel.historyList
                ) { item ->

                    Card(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                    ) {

                        Row(

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Column(

                                modifier =
                                    Modifier.weight(1f)
                            ) {

                                Text(
                                    text =
                                        item.method
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(4.dp)
                                )

                                Text(
                                    text =
                                        item.url
                                )
                            }

                            IconButton(

                                onClick = {

                                    viewModel.deleteItem(
                                        item.id
                                    )
                                }
                            ) {

                                Icon(

                                    imageVector =
                                        Icons.Default.Delete,

                                    contentDescription =
                                        null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
