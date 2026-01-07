package com.vedanthavv.codevault.domain.extensions

import com.vedanthavv.codevault.domain.model.SnippetUIState
import com.vedanthavv.codevault.data.local.entity.Snippet
import java.util.ArrayList

fun Snippet.toUIState(): SnippetUIState {
    return SnippetUIState(
        id = id,
        title = title,
        code = code,
        language = language,
        tags = tags,
        isSeeded = isSeeded
    )
}

fun SnippetUIState.toEntity(): Snippet {
    return Snippet(
        id = id,
        title = title,
        code = code,
        language = language,
        tags = tags,
        isSeeded = isSeeded
    )
}

fun List<Snippet>.toUIStateList(): List<SnippetUIState> {
    val result = ArrayList<SnippetUIState>(this.size)
    for (snippet in this) {
        result.add(snippet.toUIState())
    }
    return result
}

