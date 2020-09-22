package com.app.nbateamviewer.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Player(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("position")
    var position: String = "",
    @SerializedName("number")
    var number: Int = 0
) : Parcelable