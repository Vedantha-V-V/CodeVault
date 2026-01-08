package com.vedanthavv.codevault.domain.model

import com.vedanthavv.codevault.data.local.entity.Snippet

// Sealed UI state representing loading, success with snippets and selected tags, and error.
sealed interface SnippetUiState {
    object Loading : SnippetUiState
    data class Success(val snippets: List<Snippet>, val selectedTags: Set<String>) : SnippetUiState
    data class Error(val message: String) : SnippetUiState
}
