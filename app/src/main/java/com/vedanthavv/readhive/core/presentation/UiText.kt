package com.vedanthavv.readhive.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.annotation.StringRes

sealed interface UiText {
    data class DynamicString(val value: String) : UiText

    data class StringResourceId(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResourceId -> {
                if (args.isEmpty()) {
                    stringResource(id = id)
                } else {
                    stringResource(id = id, formatArgs = args)
                }
            }
        }
    }
}