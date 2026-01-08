package com.vedanthavv.codevault.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vedanthavv.codevault.data.dao.PasscodeDao
import com.vedanthavv.codevault.data.entity.Passcode

@Database(
    entities = [Passcode::class],
    version = 1,
    exportSchema = false // disable schema export to silence kapt warning
)
@TypeConverters(DateTypeConverters::class)
abstract class PasscodeDatabase: RoomDatabase() {
    abstract val dao: PasscodeDao

}