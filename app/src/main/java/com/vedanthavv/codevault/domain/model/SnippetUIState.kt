package com.vedanthavv.codevault.domain.model

import java.util.Collections

data class SnippetUIState(
    val id: Long = 0,
    val title: String = "",
    val code: String = "",
    val language: String = "",
    val tags: List<String> = Collections.emptyList(),
    val isSeeded: Boolean = false
)