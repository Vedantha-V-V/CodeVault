package com.vedanthavv.codevault.data.di

import android.content.Context
import androidx.room.Room
import com.vedanthavv.codevault.data.local.database.CodeVaultDatabase
import com.vedanthavv.codevault.data.local.database.SnippetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideCodeVaultDatabase(
        @ApplicationContext context: Context
    ): CodeVaultDatabase {
        return Room.databaseBuilder(
            context,
            CodeVaultDatabase::class.java,
            "codevault.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSnippetDao(database: CodeVaultDatabase): SnippetDao {
        return database.snippetDao()
    }
}

