package com.example.devbox.ui.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DevToolCard(

    title: String,

    description: String,

    onClick: () -> Unit
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {

                    onClick()
                },

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    MaterialTheme
                        .colorScheme
                        .surface
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
    ) {

        Column(

            modifier =
                Modifier.padding(20.dp)
        ) {

            Text(

                text = title,

                style =
                    MaterialTheme
                        .typography
                        .titleLarge,

                fontFamily =
                    FontFamily.Monospace
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(

                text = description,

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Row {

                SuggestionChip(

                    onClick = {},

                    label = {

                        Text("Dev Tool")
                    }
                )

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                SuggestionChip(

                    onClick = {},

                    label = {

                        Text("Compose")
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF0D1117
)
@Composable
fun DevToolCardPreview() {

    MaterialTheme {

        DevToolCard(

            title = "API Tester",

            description =
                "Test REST APIs with headers, JSON bodies and authentication.",

            onClick = {}
        )
    }
}