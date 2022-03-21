package com.example.chucknorrisjokes

import com.google.gson.annotations.SerializedName

class Joke (
    @SerializedName("categories")
    val mCategories: List<Any?>,

    @SerializedName("created_at")
    val mCreateAt: String,

    @SerializedName("icon_url")
    val mIconUrl: String,

    @SerializedName("id")
    val mId: String,

    @SerializedName("updated_at")
    val mUpdatedAt: String,

    @SerializedName("url")
    val mUrl: String,

    @SerializedName("value")
    val mValue: String

)