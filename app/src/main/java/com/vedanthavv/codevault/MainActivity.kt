package com.vedanthavv.codevault

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vedanthavv.codevault.data.database.PasscodeDatabase
import com.vedanthavv.codevault.domain.viewmodel.PasscodeViewModel
import com.vedanthavv.codevault.ui.screens.MainScreen
import com.vedanthavv.codevault.ui.theme.CodeVaultTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PasscodeDatabase::class.java,
            "passcode.db"
        ).build()
    }
    private val viewModel by viewModels<PasscodeViewModel>(factoryProducer = {
        object: ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>):T{
                return PasscodeViewModel(db.dao) as T
            }
        }
    })
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeVaultTheme {
                val state by viewModel.state.collectAsState()
                MainScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

