package com.example.devbox.features.jsonformatter

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.devbox.core.utils.ClipboardUtils
import com.example.devbox.core.utils.toList
import com.example.devbox.core.utils.toMap
import org.json.JSONArray
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JsonFormatterScreen() {

    val context = LocalContext.current

    val viewModel = remember {
        JsonFormatterViewModel()
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        topBar = {

            TopAppBar(
                title = {
                    Text("JSON Formatter")
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                value = viewModel.inputText,

                onValueChange = {
                    viewModel.inputText = it
                },

                label = {
                    Text("Enter JSON")
                },

                modifier = Modifier.fillMaxWidth(),

                minLines = 10,

                textStyle = LocalTextStyle.current.copy(
                    fontFamily = FontFamily.Monospace
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    onClick = {
                        viewModel.beautifyJson()
                    }
                ) {
                    Text("Beautify")
                }

                Button(
                    onClick = {
                        viewModel.minifyJson()
                    }
                ) {
                    Text("Minify")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                IconButton(
                    onClick = {

                        viewModel.inputText =
                            ClipboardUtils.getClipboardText(context)
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ContentPaste,
                        contentDescription = "Paste"
                    )
                }

                IconButton(
                    onClick = {

                        ClipboardUtils.copyToClipboard(
                            context,
                            viewModel.outputText
                        )
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy"
                    )
                }

                IconButton(
                    onClick = {

                        val shareIntent = Intent().apply {

                            action = Intent.ACTION_SEND

                            putExtra(
                                Intent.EXTRA_TEXT,
                                viewModel.outputText
                            )

                            type = "text/plain"
                        }

                        context.startActivity(
                            Intent.createChooser(
                                shareIntent,
                                "Share JSON"
                            )
                        )
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )
                }

                IconButton(
                    onClick = {

                        viewModel.inputText = ""
                        viewModel.outputText = ""
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Output",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                SelectionContainer {

                    Text(
                        text = JsonSyntaxHighlighter
                            .highlight(viewModel.outputText),
                        modifier = Modifier.padding(16.dp),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "JSON Tree",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))


            val jsonTree = try {

                when {

                    viewModel.outputText
                        .trim()
                        .startsWith("{") -> {

                        JSONObject(viewModel.outputText)
                            .toMap()
                    }

                    viewModel.outputText
                        .trim()
                        .startsWith("[") -> {

                        JSONArray(viewModel.outputText)
                            .toList()
                    }

                    else -> null
                }

            } catch (_: Exception) {

                null
            }
            jsonTree?.let {

                JsonTreeNode(
                    keyName = "root",
                    value = it
                )
            }
        }
    }
}