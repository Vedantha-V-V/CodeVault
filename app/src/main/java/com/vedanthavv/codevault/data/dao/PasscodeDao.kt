package com.vedanthavv.codevault.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vedanthavv.codevault.data.entity.Passcode
import kotlinx.coroutines.flow.Flow

@Dao
interface PasscodeDao {
    @Upsert
    suspend fun addPasscode(passcode: Passcode)
    @Delete
    suspend fun deletePasscode(passcode: Passcode)

    @Query("SELECT * FROM passcode ORDER BY title ASC")
    fun getPasscodeOrderedByTitle(): Flow<List<Passcode>>

    @Query("SELECT * FROM passcode ORDER BY createdAt ASC")
    fun getPasscodeOrderedByDate(): Flow<List<Passcode>>

    @Query("SELECT * FROM passcode ORDER BY category ASC")
    fun getPasscodeOrderedByCategory(): Flow<List<Passcode>>
}