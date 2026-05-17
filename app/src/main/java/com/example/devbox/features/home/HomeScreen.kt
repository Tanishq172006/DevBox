package com.example.devbox.features.home

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import com.example.devbox.core.models.ToolCategory
import com.example.devbox.navigation.Routes
import com.example.devbox.ui.design.DevToolCard


data class ToolItem(

    val title: String,
    val description: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen(

    navController: NavController
) {

    var searchQuery by remember {

        mutableStateOf("")
    }

    val categories = listOf(

        ToolCategory(

            title = "Networking",

            tools = listOf(

                ToolItem(
                    title = "API Tester",
                    description = "Test REST APIs and requests",
                    route = Routes.API,
                    icon = Icons.Default.Cloud
                ),

                ToolItem(
                    title = "GraphQL",
                    description = "Execute GraphQL queries",
                    route = Routes.GRAPHSQL,
                    icon = Icons.Default.Hub
                ),

                ToolItem(
                    title = "WebSocket",
                    description = "Realtime socket testing",
                    route = Routes.WEBSOCK,
                    icon = Icons.Default.Wifi
                )
            )
        ),

        ToolCategory(

            title = "Data Tools",

            tools = listOf(

                ToolItem(
                    title = "JSON Formatter",
                    description = "Beautify and validate JSON",
                    route = Routes.JSON,
                    icon = Icons.Default.DataObject
                ),

                ToolItem(
                    title = "JSON → Kotlin",
                    description = "Generate Kotlin data classes",
                    route = Routes.KOTLINJSON,
                    icon = Icons.Default.Code
                ),

                ToolItem(
                    title = "SQL Playground",
                    description = "Execute SQL queries",
                    route = Routes.SQL,
                    icon = Icons.Default.Storage
                )
            )
        ),

        ToolCategory(

            title = "Security",

            tools = listOf(

                ToolItem(
                    title = "JWT Decoder",
                    description = "Decode JWT tokens",
                    route = Routes.JWT,
                    icon = Icons.Default.Security
                ),

                ToolItem(
                    title = "Base64",
                    description = "Encode and decode Base64",
                    route = Routes.BASE64,
                    icon = Icons.Default.Lock
                )
            )
        ),

        ToolCategory(

            title = "Utilities",

            tools = listOf(

                ToolItem(
                    title = "Regex Playground",
                    description = "Test regex patterns live",
                    route = Routes.REGEX,
                    icon = Icons.Default.Search
                ),

                ToolItem(
                    title = "Request History",
                    description = "View previous requests",
                    route = Routes.HIS,
                    icon = Icons.Default.History
                )
            )
        )
    )

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
    {

        LazyColumn(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
        ) {

            item {

                Text(

                    text = "DevBox",
                    color = MaterialTheme.colorScheme.onBackground,

                    style =
                        MaterialTheme
                            .typography
                            .headlineLarge,

                    fontFamily =
                        FontFamily.Monospace
                )

                Text(

                    text =
                        "Developer Toolkit",
                    color = MaterialTheme.colorScheme.onBackground,

                    style =
                        MaterialTheme
                            .typography
                            .bodyLarge
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                OutlinedTextField(

                    value = searchQuery,

                    onValueChange = {

                        searchQuery = it
                    },

                    label = {

                        Text("Search Tools")
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    leadingIcon = {

                        Icon(

                            imageVector =
                                Icons.Default.Search,

                            contentDescription =
                                null
                        )
                    }
                )

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )
            }

            categories.forEach { category ->

                item {

                    Text(

                        text = category.title,

                        color = MaterialTheme.colorScheme.onBackground,

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )
                }

                items(

                    category.tools.filter {

                        it.title.contains(
                            searchQuery,
                            ignoreCase = true
                        )
                    }
                ) { tool ->

                    DevToolCard(

                        title =
                            tool.title,

                        description =
                            tool.description,

                        onClick = {

                            navController.navigate(
                                tool.route
                            )
                        }
                    )
                }

                item {

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )
                }
            }
        }

    }
}