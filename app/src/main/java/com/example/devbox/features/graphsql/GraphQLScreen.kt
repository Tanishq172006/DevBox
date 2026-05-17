package com.example.devbox.features.graphql

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devbox.features.jsonformatter.JsonSyntaxHighlighter

@Composable
fun GraphQLScreen() {

    val viewModel:
            GraphQLViewModel =
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
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .background(MaterialTheme.colorScheme.background)
        ) {

            Text(

                text = "GraphQL Client",

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

                value =
                    viewModel.endpoint,

                onValueChange = {

                    viewModel.endpoint = it
                },

                label = {

                    Text("Endpoint")
                },

                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            OutlinedTextField(

                value =
                    viewModel.query,

                onValueChange = {

                    viewModel.query = it
                },

                label = {

                    Text("GraphQL Query")
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(220.dp),

                textStyle =
                    LocalTextStyle.current.copy(

                        fontFamily =
                            FontFamily.Monospace
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            OutlinedTextField(

                value =
                    viewModel.variables,

                onValueChange = {

                    viewModel.variables = it
                },

                label = {

                    Text("Variables JSON")
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(140.dp),

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

                    viewModel.executeQuery()
                }
            ) {

                Text("Execute")
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Text(

                text = "Response",

                color = MaterialTheme.colorScheme.onBackground,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Card(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(350.dp)
            ) {

                Column(

                    modifier =
                        Modifier
                            .padding(12.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                ) {

                    Text(

                        text = JsonSyntaxHighlighter.highlight(viewModel.response),

                        fontFamily =
                            FontFamily.Monospace
                    )
                }
            }
        }
    }

}