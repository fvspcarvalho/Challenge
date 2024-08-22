package com.example.aptoidedemo.core.models.remote

import com.example.aptoidedemo.core.models.local.All
import com.example.aptoidedemo.core.models.local.Content
import com.example.aptoidedemo.core.models.local.Data
import com.example.aptoidedemo.core.models.local.Datasets
import com.example.aptoidedemo.core.models.local.Info
import com.example.aptoidedemo.core.models.local.ListApps
import com.example.aptoidedemo.core.models.local.Responses
import com.example.aptoidedemo.core.models.local.Root
import com.example.aptoidedemo.core.models.local.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RootRemote(
    val status: String?,
    val responses: ResponsesRemote?
)

@Serializable
data class ResponsesRemote(val listApps: ListAppsRemote?)

@Serializable
data class ListAppsRemote(
    val info: InfoRemote?,
    val datasets: DatasetsRemote?
)

@Serializable
data class InfoRemote(
    val status: String?,
    val time: TimeRemote?
)

@Serializable
data class TimeRemote(
    val seconds: Double?,
    val human: String?
)

@Serializable
data class DatasetsRemote(
    val all: AllRemote?
)

@Serializable
data class AllRemote(
    val info: InfoRemote?,
    val data: DataRemote?
)

@Serializable
data class DataRemote(
    val total: Long?,
    val offset: Long?,
    val limit: Long?,
    val next: Long?,
    val hidden: Long?,
    @SerialName("list") val content: List<ContentRemote> = emptyList()
)

@Serializable
data class ContentRemote(
    val id: Long?,
    val name: String?,
    @SerialName("package") val packageField: String?,
    @SerialName("store_id") val storeId: Long?,
    @SerialName("store_name") val storeName: String?,
    @SerialName("vername") val verName: String?,
    @SerialName("vercode") val verCode: Long?,
    val md5sum: String?,
    @SerialName("apk_tags") val apkTags: List<String>?,
    val size: Long?,
    val downloads: Long?,
    @SerialName("pdownloads") val pDownloads: Long?,
    val added: String?,
    val modified: String?,
    val updated: String?,
    val rating: Double?,
    val icon: String?,
    val graphic: String?,
    @SerialName("uptype") val upType: String?
)

fun RootRemote.mapToRoot(): Root {
    return Root(
        status = this.status ?: "",
        responses = this.responses?.mapToResponses() ?: Responses()
    )
}

fun ResponsesRemote.mapToResponses(): Responses {
    return Responses(
        listApps = this.listApps?.mapToListApps() ?: ListApps()
    )
}

fun ListAppsRemote.mapToListApps(): ListApps {
    return ListApps(
        info = this.info?.mapToInfo() ?: Info(),
        datasets = this.datasets?.mapToDataSets() ?: Datasets()
    )
}

fun InfoRemote.mapToInfo(): Info {
    return Info(
        status = this.status ?: "",
        time = this.time?.mapToTime() ?: Time()
    )
}

fun DatasetsRemote.mapToDataSets(): Datasets {
    return Datasets(
        all = this.all?.mapToAll() ?: All()
    )
}

fun TimeRemote.mapToTime(): Time {
    return Time(
        seconds = this.seconds ?: 0.0,
        human = this.human ?: ""
    )
}

fun AllRemote.mapToAll(): All {
    return All(
        info = this.info?.mapToInfo() ?: Info(),
        data = this.data?.mapToData() ?: Data()
    )
}

fun DataRemote.mapToData(): Data {
    return Data(
        total = this.total ?: 0,
        offset = this.offset ?: 0,
        limit = this.limit ?: 0,
        next = this.next ?: 0,
        hidden = this.hidden ?: 0,
        content = this.content.map { it.mapToContent() }
    )
}

fun ContentRemote.mapToContent(): Content {
    return Content(
        id = this.id ?: 0,
        name = this.name ?: "",
        packageField = this.packageField ?: "",
        storeId = this.storeId ?: 0,
        storeName = this.storeName ?: "",
        verName = this.verName ?: "",
        verCode = this.verCode ?: 0,
        md5sum = this.md5sum ?: "",
        apkTags = this.apkTags ?: emptyList(),
        size = this.size ?: 0,
        downloads = this.downloads ?: 0,
        pDownloads = this.pDownloads ?: 0,
        added = this.added ?: "",
        modified = this.modified ?: "",
        updated = this.updated ?: "",
        rating = this.rating ?: 0.0,
        icon = this.icon ?: "",
        graphic = this.graphic ?: "",
        upType = this.upType ?: ""
    )
}