package com.example.doctour.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TokensDto(
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("access")
    val access: String
)