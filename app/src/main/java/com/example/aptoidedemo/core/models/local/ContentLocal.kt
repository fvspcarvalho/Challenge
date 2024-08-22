package com.example.aptoidedemo.core.models.local

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val id: Long = 0,
    val name: String = "",
    val packageField: String = "",
    val storeId: Long = 0,
    val storeName: String = "",
    val verName: String = "",
    val verCode: Long = 0,
    val md5sum: String = "",
    val apkTags: List<String> = emptyList(),
    val size: Long = 0,
    val downloads: Long = 0,
    val pDownloads: Long = 0,
    val added: String = "",
    val modified: String = "",
    val updated: String = "",
    val rating: Double = 0.0,
    val icon: String = "",
    val graphic: String = "",
    val upType: String = ""
)

fun List<Content>.mapToListContentEntity() = this.map { it.mapToContentEntity() }

fun Content.mapToContentEntity(): ContentEntity {
    return ContentEntity(
        id = this.id,
        name = this.name,
        packageField = this.packageField,
        storeId = this.storeId,
        storeName = this.storeName,
        verName = this.verName,
        verCode = this.verCode,
        md5sum = this.md5sum,
        apkTags = this.apkTags,
        size = this.size,
        downloads = this.downloads,
        pDownloads = this.pDownloads,
        added = this.added,
        modified = this.modified,
        updated = this.updated,
        rating = this.rating,
        icon = this.icon,
        graphic = this.graphic,
        upType = this.upType
    )
}