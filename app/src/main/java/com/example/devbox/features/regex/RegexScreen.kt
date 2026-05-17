package com.example.devbox.features.regex

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.devbox.features.regex.RegexUtils.regexPresets

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
    )
@Composable
fun RegexScreen() {

    val viewModel = remember {
        RegexViewModel()
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Regex Playground")
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

            Text(

                text = "Regex Presets",

                style =
                    MaterialTheme.typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(

                modifier =
                    Modifier.horizontalScroll(
                        rememberScrollState()
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                regexPresets.forEach { preset ->

                    SuggestionChip(

                        onClick = {

                            viewModel.regexPattern =
                                preset.pattern
                        },

                        label = {

                            Text(preset.name)
                        }
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                }
            }

            OutlinedTextField(

                value = viewModel.regexPattern,

                onValueChange = {

                    viewModel.regexPattern = it
                },

                label = {
                    Text("Regex Pattern")
                },

                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(

                value = viewModel.sampleText,

                onValueChange = {

                    viewModel.sampleText = it
                },

                label = {
                    Text("Sample Text")
                },

                modifier =
                    Modifier.fillMaxWidth(),

                minLines = 8
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(

                onClick = {

                    viewModel.testRegex()
                }
            ) {

                Text("Test Regex")
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            if (viewModel.error.isNotBlank()) {

                Text(
                    text = viewModel.error,

                    color =
                        MaterialTheme.colorScheme.error
                )
            }

            Text(
                text =
                    "Matches (${viewModel.matches.size})",

                style =
                    MaterialTheme.typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Live Highlight",

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
                        RegexUtils.highlightMatches(

                            viewModel.regexPattern,

                            viewModel.sampleText
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

                text = "Capture Groups",

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

                    viewModel.groups.forEachIndexed {

                            index,

                            group
                        ->

                        Text(

                            text =
                                "Match ${index + 1}",

                            style =
                                MaterialTheme.typography
                                    .titleSmall
                        )

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )

                        Text(

                            text =
                                "Full: ${group.fullMatch}",

                            fontFamily =
                                FontFamily.Monospace
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        group.groups.forEachIndexed {

                                groupIndex,

                                value
                            ->

                            Text(

                                text =
                                    "Group ${groupIndex + 1}: $value",

                                fontFamily =
                                    FontFamily.Monospace
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(16.dp)
                        )

                        HorizontalDivider()

                        Spacer(
                            modifier =
                                Modifier.height(16.dp)
                        )
                    }
                }
            }

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

                    viewModel.matches.forEach {

                        Text(

                            text = it,

                            fontFamily =
                                FontFamily.Monospace
                        )

                        HorizontalDivider()

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )
                    }
                }
            }
        }
    }
}