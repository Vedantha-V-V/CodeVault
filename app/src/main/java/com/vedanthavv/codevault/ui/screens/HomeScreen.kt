package com.vedanthavv.codevault.ui.screens

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vedanthavv.codevault.R
import com.vedanthavv.codevault.data.entity.SortType
import com.vedanthavv.codevault.domain.event.PasscodeEvent
import com.vedanthavv.codevault.domain.state.PasscodeState
import com.vedanthavv.codevault.ui.components.AddDialog
import com.vedanthavv.codevault.ui.theme.BgColor
import com.vedanthavv.codevault.ui.theme.CardColor
import com.vedanthavv.codevault.ui.theme.HeaderColor
import com.vedanthavv.codevault.ui.theme.IconColor
import com.vedanthavv.codevault.ui.theme.TextColor
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    state: PasscodeState,
    onEvent: (PasscodeEvent) -> Unit
){
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(PasscodeEvent.ShowDialog) },containerColor = HeaderColor) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add passcode",tint = IconColor)
        }

    }) { padding ->
        if(state.isAddingPasscode){
            AddDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BgColor),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(12.dp),

        ){
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "CodeVault",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeaderColor
                    )
                }
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
                            Text(text = sortType.name, color = TextColor)
                        }
                    }
                }
            }

            items(state.passcodes, key = { it.id ?: 0 }){ passcode ->
                var visible by remember(passcode.id) { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)) {
                        // Title
                        Text(
                            text = passcode.title,
                            fontSize = 18.sp,
                            color = TextColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Password
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .background(color = HeaderColor, shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = if (visible) passcode.password else "*".repeat(8),
                                fontFamily = FontFamily.Monospace,
                                color = IconColor,
                                modifier = Modifier.weight(1f)
                            )

                            // Copy password Button
                            IconButton(onClick = {
                                val clipData = ClipData.newPlainText("plain text", passcode.password)
                                val clipEntry = ClipEntry(clipData)
                                scope.launch{
                                    clipboard.setClipEntry(clipEntry)
                                }
                            }){
                                Icon(painterResource(id = R.drawable.content_copy), contentDescription = "Copy password", tint = IconColor)
                            }

                            // Show Button
                            IconButton(onClick = { visible = !visible }) {
                                if(!visible){
                                    Icon(painterResource(id = R.drawable.view_eye),contentDescription = "Show",tint = IconColor)
                                }else{
                                    Icon(imageVector = Icons.Default.Close,contentDescription = "Hide", tint = IconColor)
                                }
                            }
                            // Delete Button
                            IconButton(onClick = { onEvent(PasscodeEvent.DeletePasscode(passcode)) }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete passcode", tint = IconColor)
                            }

                        }
                    }
                }
            }
        }
    }
}
