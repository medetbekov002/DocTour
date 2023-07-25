package com.example.doctour.data.model

import com.example.doctour.data.utils.DataMapper
import com.example.doctour.domain.model.Profile

data class ProfileDt(
    val id: String,
    val fullname: String,
    val phone_number: String,
    val birthday: String,
    val gender: String
) : DataMapper<Profile> {
    override fun mapToDomain() = Profile(
        id, fullname, phone_number, birthday, gender
    )
}