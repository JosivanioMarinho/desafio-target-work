package com.josivaniomarinho.desafiotargetwork.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RSSObject(
    val status: String,
    val feed: Feed,
    val items: List<Item>
) : Parcelable