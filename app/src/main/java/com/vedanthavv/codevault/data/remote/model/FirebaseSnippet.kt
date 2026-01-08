package com.vedanthavv.codevault.data.remote.model

import com.vedanthavv.codevault.data.local.entity.Snippet

// Data class mapping the Firestore document fields. Fields are nullable because
// Firestore may omit them; defaults are provided in conversion.
data class FirebaseSnippet(
    val title: String? = null,
    val code: String? = null,
    val language: String? = null,
    val tags: List<String>? = null
) {
    // Convert a Firestore-backed FirebaseSnippet into the local Snippet entity.
    fun toSnippet(): Snippet {
        return Snippet(
            // id left as default (0) so Room will auto-generate
            title = title ?: "",
            code = code ?: "",
            language = language ?: "",
            tags = tags ?: emptyList(),
            isSeeded = true
        )
    }
}

