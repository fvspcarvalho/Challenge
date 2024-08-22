package com.example.aptoidedemo.core.local.database

import androidx.room.TypeConverter
import com.example.aptoidedemo.core.models.local.All
import com.example.aptoidedemo.core.models.local.Content
import com.example.aptoidedemo.core.models.local.Data
import com.example.aptoidedemo.core.models.local.Datasets
import com.example.aptoidedemo.core.models.local.Info
import com.example.aptoidedemo.core.models.local.ListApps
import com.example.aptoidedemo.core.models.local.Responses
import com.example.aptoidedemo.core.models.local.Time
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeConvertersUtil {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(", ")
    }

    @TypeConverter
    fun toList(list: String): List<String> {
        return list.split(", ")
    }
}
