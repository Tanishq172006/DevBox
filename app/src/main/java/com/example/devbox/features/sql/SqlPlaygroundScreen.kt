package com.example.devbox.features.sql

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.devbox.core.utils.ClipboardUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SqlPlaygroundScreen() {

    val context =
        LocalContext.current

    val viewModel = remember {
        SqlPlaygroundViewModel()
    }

    LaunchedEffect(Unit) {

        SqlUtils.initDatabase(context)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("SQL Playground")
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

                value = viewModel.query,

                onValueChange = {

                    viewModel.query = it
                },

                label = {
                    Text("SQL Query")
                },

                modifier =
                    Modifier.fillMaxWidth(),

                minLines = 8,

                textStyle = LocalTextStyle.current.copy(
                    fontFamily = FontFamily.Monospace
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(

                onClick = {

                    viewModel.execute()
                }
            ) {

                Text("Run Query")
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(

                text = "Result",

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

                Column(
                    modifier =
                        Modifier.padding(16.dp)
                ) {

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.End
                    ) {

                        IconButton(

                            onClick = {

                                ClipboardUtils
                                    .copyToClipboard(

                                        context,

                                        viewModel.result
                                    )
                            }
                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.ContentCopy,

                                contentDescription =
                                    "Copy"
                            )
                        }
                    }

                    Text(

                        text =
                            viewModel.result,

                        fontFamily =
                            FontFamily.Monospace
                    )
                }
            }

            viewModel.tableData?.let { table ->

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(

                    text = "Table Result",

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

                    Column(
                        modifier =
                            Modifier.padding(16.dp)
                    ) {

                        Row {

                            table.columns.forEach { column ->

                                Text(

                                    text = column,

                                    modifier =
                                        Modifier
                                            .weight(1f),

                                    style =
                                        MaterialTheme.typography
                                            .titleSmall
                                )
                            }
                        }

                        HorizontalDivider()

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        table.rows.forEach { row ->

                            Row {

                                row.forEach { cell ->

                                    Text(

                                        text = cell,

                                        modifier =
                                            Modifier
                                                .weight(1f),

                                        fontFamily =
                                            FontFamily.Monospace
                                    )
                                }
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}