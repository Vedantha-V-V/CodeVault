package com.vedanthavv.codevault.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun toTagsList(tagsJson: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(tagsJson, type)
    }
}

