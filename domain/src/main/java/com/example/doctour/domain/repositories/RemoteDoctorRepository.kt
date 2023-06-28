package com.example.doctour.domain.repositories

import androidx.paging.PagingData
import com.example.doctour.domain.model.Clinic
import com.example.doctour.domain.model.Doctor
import com.example.doctour.domain.model.Review
import com.example.doctour.domain.model.Service
import com.example.doctour.domain.model.Speciality
import kotlinx.coroutines.flow.Flow

interface RemoteDoctorRepository {

    fun getAllDoctors(
        specialties: String?,
        clinic: String?,
        categoryService: String?,
        city: String?,
        search: String?,
        ordering: String?
    ): Flow<PagingData<Doctor>>

    fun getSpecialityOfDoctors():Flow<PagingData<Speciality>>

    fun  getClinics():Flow<PagingData<Clinic>>

    fun getCategoryServicesOfDoctors(
        name:String?,
        price:String?,
        search:String?
    ):Flow<PagingData<Service>>

    fun getReviews():Flow<PagingData<Review>>
}