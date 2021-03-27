package com.mualim.githubusers

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var userName: String = "",
    var name: String = "",
    var location: String = "",
    var repository: Int? = null,
    var avatar: Int? = null,
    var company: String? = "",
    var followers: Int? = null,
    var following: Int? = null,
    var githubLink: String = ""
) : Parcelable