package com.vedanthavv.codevault.domain.state

import com.vedanthavv.codevault.data.entity.Passcode
import com.vedanthavv.codevault.data.entity.SortType

data class PasscodeState (
    val passcodes: List<Passcode> = emptyList(),
    val title: String = "",
    val category: String = "",
    val password: String = "",
    val showPasscode: Boolean = false,
    val isAddingPasscode: Boolean = false,
    val sortType: SortType = SortType.TITLE
)