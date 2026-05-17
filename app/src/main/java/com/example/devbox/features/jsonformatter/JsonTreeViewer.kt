package com.example.devbox.features.jsonformatter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JsonTreeNode(
    keyName: String,
    value: Any?,
    indent: Int = 0
) {

    var expanded by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.padding(
            start = (indent * 12).dp
        )
    ) {

        when (value) {

            is Map<*, *> -> {

                Row(
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                ) {

                    Text(
                        text =
                            if (expanded) "▼ "
                            else "▶ "
                    )

                    Text(
                        text = "$keyName { }"
                    )
                }

                if (expanded) {

                    value.forEach { (k, v) ->

                        JsonTreeNode(
                            keyName = k.toString(),
                            value = v,
                            indent = indent + 1
                        )
                    }
                }
            }

            is List<*> -> {

                Row(
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                ) {

                    Text(
                        text =
                            if (expanded) "▼ "
                            else "▶ "
                    )

                    Text(
                        text =
                            "$keyName [${value.size}]"
                    )
                }

                if (expanded) {

                    value.forEachIndexed { index, item ->

                        JsonTreeNode(
                            keyName = "[$index]",
                            value = item,
                            indent = indent + 1
                        )
                    }
                }
            }

            else -> {

                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    )
                ) {

                    Text(
                        text = keyName
                    )

                    Text(
                        text = value.toString()
                    )
                }
            }
        }
    }
}