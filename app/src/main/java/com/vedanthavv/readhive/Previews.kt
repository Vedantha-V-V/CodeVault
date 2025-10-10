package com.vedanthavv.readhive

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vedanthavv.readhive.book.presentation.booklist.components.BookSearchBar

@Preview
@Composable
private fun BookSearchBarPreview(){
    MaterialTheme{
        BookSearchBar(
            searchQuery = "Kotlin",
            onSearchQueryChange = {},
            onImeSearch = {},modifier = Modifier.fillMaxWidth()
        )
    }
}