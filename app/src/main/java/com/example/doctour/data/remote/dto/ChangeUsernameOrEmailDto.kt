package com.example.doctour.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ChangeUsernameOrEmailDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
)