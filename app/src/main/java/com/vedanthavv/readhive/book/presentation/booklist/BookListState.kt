package com.vedanthavv.readhive.book.presentation.booklist

import com.vedanthavv.readhive.book.domain.Book
import com.vedanthavv.readhive.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val searchTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
