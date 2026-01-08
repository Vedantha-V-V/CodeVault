package com.vedanthavv.codevault.data.database

import androidx.room.TypeConverter
import java.util.Date

object DateTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? = date?.time
}

