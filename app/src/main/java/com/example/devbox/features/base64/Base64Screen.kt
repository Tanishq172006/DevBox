package com.example.devbox.features.base64

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.devbox.core.utils.ClipboardUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Base64Screen() {

    val context = LocalContext.current

    val viewModel = remember {
        Base64ToolViewModel()
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Base64 Tool")
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

                value = viewModel.input,

                onValueChange = {

                    viewModel.input = it
                },

                label = {
                    Text("Input")
                },

                modifier =
                    Modifier.fillMaxWidth(),

                minLines = 6
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Button(

                    onClick = {

                        viewModel.encode()
                    }
                ) {

                    Text("Encode")
                }

                Button(

                    onClick = {

                        viewModel.decode()
                    }
                ) {

                    Text("Decode")
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Output",
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

                                        viewModel.output
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

                        text = viewModel.output,

                        fontFamily =
                            FontFamily.Monospace
                    )
                }
            }
        }
    }
}