package com.vedanthavv.codevault.domain.event

import com.vedanthavv.codevault.data.entity.Passcode
import com.vedanthavv.codevault.data.entity.SortType

sealed interface PasscodeEvent {
    object SavePasscode: PasscodeEvent
    data class SetTitle(val title: String): PasscodeEvent
    data class SetCategory(val category: String): PasscodeEvent
    data class SetPassword(val password: String): PasscodeEvent
    object ShowDialog: PasscodeEvent
    object HideDialog: PasscodeEvent
    data class SortPasscode(val sortType: SortType): PasscodeEvent
    data class DeletePasscode(val passcode: Passcode): PasscodeEvent
}
