package com.vedanthavv.codevault.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vedanthavv.codevault.data.local.entity.Snippet

/**
 * A card that shows a snippet's title, language badge, tags as chips and a code preview.
 * Click the card to expand/collapse the full code. Action callbacks are provided for edit/delete/copy.
 */
@Composable
fun SnippetCard(
    snippet: Snippet,
    onEdit: (Snippet) -> Unit,
    onDelete: (Snippet) -> Unit,
    onCopy: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(false) }

    // Compute preview text: first 3 lines or full code when expanded
    val lines = snippet.code.lines()
    val previewText = if (expanded.value) {
        snippet.code
    } else {
        when {
            lines.size <= 3 -> snippet.code
            else -> lines.subList(0, 3).joinToString("\n") + "\n..."
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded.value = !expanded.value }
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = snippet.title.ifEmpty { "Untitled" }, style = MaterialTheme.typography.titleMedium)

                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = snippet.language,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tags row
            val scroll = rememberScrollState()
            Row(modifier = Modifier.horizontalScroll(scroll), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (tag in snippet.tags) {
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                        modifier = Modifier
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Code preview
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 0.5.dp,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = previewText,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Actions
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = { onCopy(snippet.code) }) { Text(text = "Copy") }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = { onEdit(snippet) }) { Text(text = "Edit") }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = { onDelete(snippet) }) { Text(text = "Delete") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnippetCardPreview() {
    val sample = Snippet(
        id = 1,
        title = "Sample Snippet",
        code = "fun hello() {\n    println(\"Hello\")\n}\n\n// extra line 1\n// extra line 2",
        language = "Kotlin",
        tags = listOf("android", "compose"),
        isSeeded = false
    )

    SnippetCard(
        snippet = sample,
        onEdit = {},
        onDelete = {},
        onCopy = {}
    )
}
