package com.vedanthavv.codevault.domain.extensions

import com.vedanthavv.codevault.data.local.entity.Snippet

// Helper extension to collect unique tags from a list of snippets
fun List<Snippet>.uniqueTags(): Set<String> {
    val tags = mutableSetOf<String>()
    for (snippet in this) {
        tags.addAll(snippet.tags)
    }
    return tags
}
