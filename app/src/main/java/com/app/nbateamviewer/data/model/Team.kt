package com.app.nbateamviewer.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Team(
    @SerializedName("wins")
    var wins: Int = 0,
    @SerializedName("losses")
    var losses: Int = 0,
    @SerializedName("full_name")
    var fullName: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("players")
    var players: ArrayList<Player> = ArrayList()
) : Parcelable