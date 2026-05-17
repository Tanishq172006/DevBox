package com.example.devbox.features.apitester


import android.app.Application
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devbox.core.models.HeaderItem
import com.example.devbox.core.utils.ClipboardUtils
import com.example.devbox.core.utils.toList
import com.example.devbox.core.utils.toMap
import com.example.devbox.features.jsonformatter.JsonSyntaxHighlighter
import com.example.devbox.features.jsonformatter.JsonTreeNode
import org.json.JSONArray
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiTesterScreen() {


    val context = LocalContext.current

    val viewModel: ApiTesterViewModel = viewModel(

        factory = ApiTesterViewModelFactory(

            context.applicationContext as Application
        )
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    var curlInput by remember {

        mutableStateOf("")
    }

    val methods = listOf(
        "GET",
        "POST",
        "PUT",
        "DELETE"
    )

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("API Tester")
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

                value = curlInput,

                onValueChange = {

                    curlInput = it
                },

                label = {

                    Text("Paste CURL Command")
                },

                modifier =
                    Modifier.fillMaxWidth(),

                minLines = 4
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(

                onClick = {

                    val parsed =
                        CurlParser.parseCurl(
                            curlInput
                        )

                    viewModel.selectedMethod =
                        parsed.method

                    viewModel.url =
                        parsed.url

                    viewModel.requestBody =
                        parsed.body

                    viewModel.headers.clear()

                    parsed.headers.forEach {

                        viewModel.headers.add(

                            HeaderItem(

                                key = it.first,

                                value = it.second
                            )
                        )
                    }
                }
            ) {

                Text("Import CURL")
            }

            OutlinedTextField(
                value = viewModel.url,

                onValueChange = {
                    viewModel.url = it
                },

                label = {
                    Text("Enter URL")
                },

                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenuBox(
                expanded = expanded,

                onExpandedChange = {
                    expanded = !expanded
                }
            ) {

                OutlinedTextField(
                    value = viewModel.selectedMethod,

                    onValueChange = {},

                    readOnly = true,

                    label = {
                        Text("Method")
                    },

                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,

                    onDismissRequest = {
                        expanded = false
                    }
                ) {

                    methods.forEach { method ->

                        DropdownMenuItem(

                            text = {
                                Text(method)
                            },

                            onClick = {

                                viewModel.selectedMethod =
                                    method

                                expanded = false
                            }
                        )
                    }
                }
            }
            Text(
                text = "Headers",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))
            viewModel.headers.forEachIndexed { index, header ->

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    OutlinedTextField(

                        value = header.key,

                        onValueChange = {

                            viewModel.headers[index] =
                                header.copy(key = it)
                        },

                        label = {
                            Text("Key")
                        },

                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(

                        value = header.value,

                        onValueChange = {

                            viewModel.headers[index] =
                                header.copy(value = it)
                        },

                        label = {
                            Text("Value")
                        },

                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
            OutlinedButton(

                onClick = {

                    viewModel.headers.add(
                        HeaderItem()
                    )
                }
            ) {

                Text("Add Header")
            }

            OutlinedTextField(
                value = viewModel.requestBody,

                onValueChange = {
                    viewModel.requestBody = it
                },

                label = {
                    Text("Request Body")
                },

                modifier = Modifier.fillMaxWidth(),

                minLines = 8
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Button(
                    onClick = {

                        viewModel.sendRequest()
                    }
                ) {

                    Text(
                        "Send ${viewModel.selectedMethod}"
                    )
                }

                FilledIconButton(

                    onClick = {

                        ClipboardUtils.copyToClipboard(
                            context,
                            viewModel.responseText
                        )
                    }
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.ContentCopy,

                        contentDescription = "Copy"
                    )
                }
            }



            Spacer(modifier = Modifier.height(20.dp))

            if (viewModel.loading) {

                CircularProgressIndicator()

            } else {
                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    AssistChip(
                        onClick = {},
                        label = {
                            Text("Status: ${viewModel.statusCode}")
                        }
                    )

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(viewModel.responseTime)
                        }
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = JsonSyntaxHighlighter
                            .highlight(viewModel.responseText),

                        modifier = Modifier.padding(16.dp),

                        fontFamily = FontFamily.Monospace
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Response Tree",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
            val responseTree = try {

                when {

                    viewModel.responseText
                        .trim()
                        .startsWith("{") -> {

                        JSONObject(viewModel.responseText)
                            .toMap()
                    }

                    viewModel.responseText
                        .trim()
                        .startsWith("[") -> {

                        JSONArray(viewModel.responseText)
                            .toList()
                    }

                    else -> null
                }

            } catch (_: Exception) {

                null
            }
            responseTree?.let {

                JsonTreeNode(
                    keyName = "root",
                    value = it
                )
            }
        }
    }
}