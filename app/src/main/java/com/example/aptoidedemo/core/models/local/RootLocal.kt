package com.example.aptoidedemo.core.models.local

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
