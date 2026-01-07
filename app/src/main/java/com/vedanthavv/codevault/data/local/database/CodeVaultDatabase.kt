package com.vedanthavv.codevault.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vedanthavv.codevault.data.local.converters.Converters
import com.vedanthavv.codevault.data.local.entity.Snippet

@Database(
    entities = [Snippet::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CodeVaultDatabase : RoomDatabase() {
    abstract fun snippetDao(): SnippetDao
}

