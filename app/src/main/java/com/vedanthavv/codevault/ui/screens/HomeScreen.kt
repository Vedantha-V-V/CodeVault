package com.vedanthavv.codevault.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vedanthavv.codevault.data.entity.SortType
import com.vedanthavv.codevault.domain.event.PasscodeEvent
import com.vedanthavv.codevault.domain.state.PasscodeState
import com.vedanthavv.codevault.ui.components.AddDialog

@Composable
fun MainScreen(
    state: PasscodeState,
    onEvent: (PasscodeEvent) -> Unit
){
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(PasscodeEvent.ShowDialog) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add passcode")
        }

    }) { padding ->
        if(state.isAddingPasscode){
            AddDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),

        ){
            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SortType.entries.forEach { sortType ->
                        Row(modifier = Modifier.clickable{
                            onEvent(PasscodeEvent.SortPasscode(sortType))
                        },
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(PasscodeEvent.SortPasscode(sortType))
                                })
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.passcodes){ passcode ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text="${passcode.title}", fontSize = 20.sp)
                    }
                    IconButton(onClick = { onEvent(PasscodeEvent.DeletePasscode(passcode)) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete passcode")
                    }
                }

            }
        }
    }
}