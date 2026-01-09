package com.vedanthavv.codevault.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vedanthavv.codevault.domain.event.PasscodeEvent
import com.vedanthavv.codevault.domain.state.PasscodeState
import com.vedanthavv.codevault.ui.theme.CardColor
import com.vedanthavv.codevault.ui.theme.HeaderColor
import com.vedanthavv.codevault.ui.theme.IconColor
import com.vedanthavv.codevault.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDialog(
    state: PasscodeState,
    onEvent: (PasscodeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        containerColor = CardColor,
        onDismissRequest = { onEvent(PasscodeEvent.HideDialog) },
        title = {
            Text(text  = "Add Passcode",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = HeaderColor)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.title,
                    onValueChange = { onEvent(PasscodeEvent.SetTitle(it)) },
                    label = { Text("Title",color = IconColor) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    colors = textFieldColors(focusedTextColor = IconColor, unfocusedTextColor = IconColor, focusedContainerColor = TextColor,unfocusedContainerColor = TextColor, focusedIndicatorColor = TextColor)
                )

                TextField(
                    value = state.category,
                    onValueChange = { onEvent(PasscodeEvent.SetCategory(it)) },
                    label = { Text("Category eg: Work/Personal...",color = IconColor) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    colors = textFieldColors(focusedTextColor = IconColor, unfocusedTextColor = IconColor, focusedContainerColor = TextColor,unfocusedContainerColor = TextColor, focusedIndicatorColor = TextColor)
                )

                TextField(
                    value = state.password,
                    onValueChange = { onEvent(PasscodeEvent.SetPassword(it)) },
                    label = { Text("Password",color = IconColor) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    colors = textFieldColors(focusedTextColor = IconColor, unfocusedTextColor = IconColor, focusedContainerColor = TextColor,unfocusedContainerColor = TextColor, focusedIndicatorColor = TextColor)
                )
            }
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                Button(onClick = {
                    onEvent(PasscodeEvent.SavePasscode)
                },colors = buttonColors(containerColor = HeaderColor)) {
                    Text(text = "Save", color = IconColor)
                }
            }
        }
    )
}

