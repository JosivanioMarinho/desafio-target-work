package com.josivaniomarinho.desafiotargetwork.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val title: String,
    val pubDate: String,
    val link: String?,
    val guid: String,
    val author: String,
    val thumbnail: String?,
    val description: String,
    val content: String,
    val enclosure: Enclosure,
    val categories: List<String>
): Parcelable
