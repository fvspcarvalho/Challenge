package com.example.aptoidedemo.data.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Root(
    val status: String = "",
    val responses: Responses = Responses()
)

@Serializable
data class Responses(val listApps: ListApps = ListApps())

@Serializable
data class ListApps(
    val info: Info = Info(),
    val datasets: Datasets = Datasets()
)

@Serializable
data class Info(
    val status: String = "",
    val time: Time = Time()
)

@Serializable
data class Time(
    val seconds: Double = 0.0,
    val human: String = ""
)

@Serializable
data class Datasets(
    val all: All = All()
)

@Serializable
data class All(
    val info: Info = Info(),
    val data: Data = Data()
)

@Serializable
data class Data(
    val total: Long = 0,
    val offset: Long = 0,
    val limit: Long = 0,
    val next: Long = 0,
    val hidden: Long = 0,
   val content: List<Content> = emptyList()
)

@Serializable
data class Content(
    val id: Long = 0,
    val name: String = "",
    @SerialName("package") val packageField: String = "",
    @SerialName("store_id") val storeId: Long = 0,
    @SerialName("store_name") val storeName: String = "",
    @SerialName("vername") val verName: String = "",
    @SerialName("vercode") val verCode: Long = 0,
    val md5sum: String = "",
    @SerialName("apk_tags") val apkTags: List<String> = emptyList(),
    val size: Long = 0,
    val downloads: Long = 0,
    @SerialName("pdownloads") val pDownloads: Long = 0,
    val added: String = "",
    val modified: String = "",
    val updated: String = "",
    val rating: Double = 0.0,
    val icon: String = "",
    val graphic: String = "",
    @SerialName("upType") val upType: String = ""
)