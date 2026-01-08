package com.vedanthavv.codevault.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vedanthavv.codevault.domain.event.PasscodeEvent
import com.vedanthavv.codevault.domain.state.PasscodeState

@Composable
fun AddDialog(
    state: PasscodeState,
    onEvent: (PasscodeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(PasscodeEvent.HideDialog) },
        title = {
            Text(text  = "Add Passcode")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.title,
                    onValueChange = { onEvent(PasscodeEvent.SetTitle(it)) },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                TextField(
                    value = state.category,
                    onValueChange = { onEvent(PasscodeEvent.SetCategory(it)) },
                    label = { Text("Category eg: Work/Personal...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                TextField(
                    value = state.password,
                    onValueChange = { onEvent(PasscodeEvent.SetPassword(it)) },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
            }
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                Button(onClick = {
                    onEvent(PasscodeEvent.SavePasscode)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}

