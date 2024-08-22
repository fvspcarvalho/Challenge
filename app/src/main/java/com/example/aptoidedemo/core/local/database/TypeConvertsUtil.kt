package com.example.aptoidedemo.core.local.database

import androidx.room.TypeConverter

class TypeConvertersUtil {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(", ")
    }

    @TypeConverter
    fun toList(list: String): List<String> {
        return list.split(", ")
    }
}
