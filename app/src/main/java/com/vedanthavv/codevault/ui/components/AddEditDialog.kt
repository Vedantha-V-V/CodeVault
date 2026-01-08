package com.vedanthavv.codevault.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vedanthavv.codevault.data.local.entity.Snippet

/**
 * Dialog for adding or editing a Snippet.
 * - snippet: nullable; when null the dialog is for adding a new snippet.
 * - onDismiss: called when dialog is cancelled or after successful save
 * - onSave: called with the Snippet to save
 */
@Composable
fun AddEditDialog(
    snippet: Snippet?,
    onDismiss: () -> Unit,
    onSave: (Snippet) -> Unit
) {
    // Use rememberSaveable to survive configuration changes
    var title by rememberSaveable { mutableStateOf(snippet?.title ?: "") }
    var language by rememberSaveable { mutableStateOf(snippet?.language ?: "") }
    var code by rememberSaveable { mutableStateOf(snippet?.code ?: "") }
    var tagsText by rememberSaveable { mutableStateOf((snippet?.tags?.joinToString(", ") ?: "")) }

    // Simple validation
    val isTitleValid = title.trim().isNotEmpty()
    val isCodeValid = code.trim().isNotEmpty()
    val isFormValid = isTitleValid && isCodeValid

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = if (snippet == null) "Add Snippet" else "Edit Snippet")
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = language,
                    onValueChange = { language = it },
                    label = { Text("Language") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text("Code") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    maxLines = 12
                )

                OutlinedTextField(
                    value = tagsText,
                    onValueChange = { tagsText = it },
                    label = { Text("Tags (comma-separated)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (!isFormValid) {
                    Text(
                        text = "Please provide a title and code.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                // Parse tags into list
                val tags = tagsText.split(',')
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }

                val newSnippet = Snippet(
                    id = snippet?.id ?: 0L,
                    title = title.trim(),
                    code = code,
                    language = language.trim(),
                    tags = tags,
                    isSeeded = snippet?.isSeeded ?: false
                )

                onSave(newSnippet)
                onDismiss()
            }, enabled = isFormValid) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AddEditSnippetDialogPreview() {
    AddEditDialog(
        snippet = null,
        onDismiss = {},
        onSave = {}
    )
}

