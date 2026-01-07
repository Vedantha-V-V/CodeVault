package com.vedanthavv.codevault.data.remote.model

import com.vedanthavv.codevault.data.local.entity.Snippet
import com.google.firebase.firestore.PropertyName

data class FirebaseSnippet(
    @PropertyName("title")
    val title: String = "",

    @PropertyName("code")
    val code: String = "",

    @PropertyName("language")
    val language: String = "",

    @PropertyName("tags")
    val tags: List<String> = emptyList()
) {
    fun toSnippet(): Snippet = Snippet(
        title = title,
        code = code,
        language = language,
        tags = tags
    )
}
