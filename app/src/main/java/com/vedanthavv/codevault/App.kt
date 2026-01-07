package com.vedanthavv.codevault

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.vedanthavv.codevault.book.presentation.booklist.BookListScreenRoot
import com.vedanthavv.codevault.book.presentation.booklist.BookListViewModel

@Composable
@Preview
fun App(){
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}