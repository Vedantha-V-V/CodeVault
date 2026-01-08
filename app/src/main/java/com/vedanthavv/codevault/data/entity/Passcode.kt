package com.vedanthavv.codevault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Passcode (
    val title:String,
    val category: String,
    val password: String,
    val createdAt: Date,
    val showPasscode: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = 0,
)