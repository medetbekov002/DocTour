package com.example.doctour.presentation.model

import com.example.doctour.base.IBaseDiffModel
import com.example.doctour.domain.model.City
import com.example.doctour.domain.model.Clinics
import com.example.doctour.domain.model.Doctor
import com.example.doctour.domain.model.Review
import com.example.doctour.domain.model.Speciality

data class DoctorUi(
    override val id: String,
    val average_rating: String?,
    val clinic:List<ClinicsUi>?= emptyList(),
    val experience: Int?,
    val full_name: String?,
    val num_reviews:String?,
    val photo: String?,
    val price: Int?,
    val specialties: List<SpecialityUi>?= emptyList(),
    val summary: String?,
    val instagram :String?,
    val doctor_reviews:List<ReviewUi>?= emptyList(),
    val isChoosen :Boolean = false
) : IBaseDiffModel<String>,java.io.Serializable
fun Doctor.toDoctorUi() = DoctorUi(
    id=id,
    average_rating=average_rating,
    clinic?.map { it.toClinicsUi() },
    experience=experience,
    full_name=full_name,
    num_reviews=num_reviews,
    photo=photo,
    price=price,
    specialties?.map { it.toSpecialityUi() },
    summary=summary,
    instagram=instagram,
    doctor_reviews?.map { it.toReviewUi() },
    isChoosen
)
fun  DoctorUi.toDoctorDmn()=Doctor(
    id,
    clinic?.map { it.toClinicDmn() },
    experience,
    full_name,
    id,
    num_reviews,
    photo,
    price,
    specialties?.map { it.toSpecialityDmn() },
    summary,
    instagram
)
fun SpecialityUi.toSpecialityDmn()=Speciality(
    id, name
)
fun ClinicsUi.toClinicDmn()=Clinics(
    address, contacts1,
    contacts2, descriptions,
    ending_working_day, id, link_2gis,
    link_clinic, photo,
    starting_working_day, title,
    weekday, weekend, slug
)

data class ReviewUi(
    val doctor: String?,
    override val id: String,
    val stars: Int?,
    val text: String?,
    val doctor_name:String?,
    val user_name : String?,
    val user :Int?,
    val date:String?
):IBaseDiffModel<String>

fun Review.toReviewUi() = ReviewUi(
    id=id, text=text, stars=stars, doctor=doctor,
    doctor_name = doctor_name,
    user_name = user_name,
    user =user,
    date = date
)

data class SpecialityUi(
    override val id: String,
    val name: String?
):IBaseDiffModel<String>

fun Speciality.toSpecialityUi() = SpecialityUi(
    id=id, name=name
)
data class ClinicsUi(
    val address: String?,
    val contacts1: Long?,
    val contacts2: Long?,
    val descriptions: String?,
    val ending_working_day: String?,
    override val id: String,
    val link_2gis: String?,
    val link_clinic: String?,
    val photo: String?,
    val starting_working_day: String?,
    val title: String?,
    val weekday: String?,
    val weekend: String?,
    val slug:String?
):IBaseDiffModel<String>

fun Clinics.toClinicsUi() = ClinicsUi(
    id=id,
    photo=photo,
    link_clinic=link_clinic,
    link_2gis=link_2gis,
    descriptions=descriptions,
    starting_working_day=starting_working_day,
    ending_working_day=ending_working_day,
    address=address,
    contacts1=contacts1,
    contacts2=contacts2,
    weekday=weekday,
    weekend=weekend,
    title=title,
    slug = slug
)

data class CityUi(
    val id: String,
    val name: String?
)

fun City.toCityUi() = CityUi(
    id=id, name=name
)