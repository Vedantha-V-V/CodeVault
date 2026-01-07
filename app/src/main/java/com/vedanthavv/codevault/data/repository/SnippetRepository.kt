package com.vedanthavv.codevault.data.repository

import com.vedanthavv.codevault.data.local.database.SnippetDao
import com.vedanthavv.codevault.data.local.entity.Snippet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SnippetRepository @Inject constructor(
    private val snippetDao: SnippetDao
) {
    suspend fun insertSnippet(snippet: Snippet): Long {
        return snippetDao.insertSnippet(snippet)
    }

    suspend fun updateSnippet(snippet: Snippet) {
        snippetDao.updateSnippet(snippet)
    }

    suspend fun deleteSnippet(snippet: Snippet) {
        snippetDao.deleteSnippet(snippet)
    }

    suspend fun getSnippetById(id: Long): Snippet? {
        return snippetDao.getSnippetById(id)
    }

    fun getAllSnippets(): Flow<List<Snippet>> {
        return snippetDao.getAllSnippets()
    }

    fun getSnippetsByLanguage(language: String): Flow<List<Snippet>> {
        return snippetDao.getSnippetsByLanguage(language)
    }

    suspend fun deleteSnippetById(id: Long) {
        snippetDao.deleteSnippetById(id)
    }
}

