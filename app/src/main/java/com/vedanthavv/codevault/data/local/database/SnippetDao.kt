package com.vedanthavv.codevault.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vedanthavv.codevault.data.local.entity.Snippet
import kotlinx.coroutines.flow.Flow

@Dao
interface SnippetDao {
    @Insert
    suspend fun insertSnippet(snippet: Snippet): Long

    @Update
    suspend fun updateSnippet(snippet: Snippet)

    @Delete
    suspend fun deleteSnippet(snippet: Snippet)

    @Query("SELECT * FROM snippets WHERE id = :id")
    suspend fun getSnippetById(id: Long): Snippet?

    @Query("SELECT * FROM snippets ORDER BY id DESC")
    fun getAllSnippets(): Flow<List<Snippet>>

    @Query("SELECT * FROM snippets WHERE language = :language ORDER BY id DESC")
    fun getSnippetsByLanguage(language: String): Flow<List<Snippet>>

    @Query("DELETE FROM snippets WHERE id = :id")
    suspend fun deleteSnippetById(id: Long)
}

