package com.vedanthavv.codevault.domain.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vedanthavv.codevault.data.dao.PasscodeDao
import com.vedanthavv.codevault.data.entity.Passcode
import com.vedanthavv.codevault.data.entity.SortType
import com.vedanthavv.codevault.domain.event.PasscodeEvent
import com.vedanthavv.codevault.domain.state.PasscodeState
import com.vedanthavv.codevault.util.EncryptionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class PasscodeViewModel(private val dao: PasscodeDao): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.TITLE)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _passcodes = _sortType.flatMapLatest { sortType ->
        when(sortType) {
            SortType.TITLE -> dao.getPasscodeOrderedByTitle()
            SortType.CATEGORY -> dao.getPasscodeOrderedByCategory()
            SortType.DATE -> dao.getPasscodeOrderedByDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(PasscodeState())
    val state = combine(_state, _sortType, _passcodes) { state, sortType, passcodes ->
        state.copy(
            passcodes = passcodes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PasscodeState())

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: PasscodeEvent){
        when(event){
            is PasscodeEvent.DeletePasscode -> {
                viewModelScope.launch {
                    dao.deletePasscode(event.passcode)
                }
            }
            PasscodeEvent.HideDialog -> {
                _state.update { it.copy(isAddingPasscode = false) }
            }
            PasscodeEvent.SavePasscode -> {
                val title = state.value.title
                val category = state.value.category
                val password = state.value.password

                if(title.isBlank()||category.isBlank()||password.isBlank()){
                    return
                }

                val encrypted = EncryptionManager.encrypt(password)

                val passcode = Passcode(
                    title = title,
                    category = category,
                    password = encrypted,
                    createdAt = Date.from(Instant.now())
                )
                viewModelScope.launch{
                    dao.addPasscode(passcode)
                }
                _state.update { it.copy(isAddingPasscode = false, title = "", category = "", password = "") }
            }
            is PasscodeEvent.SetTitle -> {
                _state.update { it.copy(title = event.title) }
            }
            is PasscodeEvent.SetCategory -> {
                _state.update { it.copy(category = event.category) }
            }
            is PasscodeEvent.SetPassword -> {
                _state.update { it.copy(password = event.password) }
            }
            PasscodeEvent.ShowDialog -> {
                _state.update { it.copy(isAddingPasscode = true) }
            }
            is PasscodeEvent.SortPasscode -> {
                _sortType.value = event.sortType
            }
        }
    }
}