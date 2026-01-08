package com.vedanthavv.codevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vedanthavv.codevault.ui.theme.CodeVaultTheme

//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeVaultTheme {
                //MainScreen()
                HelloWord()
            }
        }
    }
}

@Composable
fun HelloWord(){
    Text("Hello")
}

