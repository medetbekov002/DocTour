package com.example.doctour.data.model

import com.example.doctour.data.utils.DataMapper
import com.example.doctour.domain.model.FavoriteDoctor

data class FavoriteDoctorDt(
    val id:Int,
    val doctor_data:String,
    val user_data:String,
    val doctor:String
):DataMapper<FavoriteDoctor>{
    override fun mapToDomain()= FavoriteDoctor (
        id, doctor_data, user_data, doctor
            )
}
