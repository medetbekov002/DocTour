package com.example.doctour.presentation.ui.fragments.main.category.favoriteDoctors

import com.example.doctour.base.BaseViewModel
import com.example.doctour.domain.usecases.GetFavoriteClinicsUseCase
import com.example.doctour.domain.usecases.GetFavoriteDoctorsUseCase
import com.example.doctour.presentation.model.FavoriteClinicUI
import com.example.doctour.presentation.model.FavoriteDoctorUI
import com.example.doctour.presentation.model.toFavoriteClinicUI
import com.example.doctour.presentation.model.toFavoriteDoctorUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteDoctorsViewModel @Inject constructor(
    private val getFavoriteDoctorsUseCase: GetFavoriteDoctorsUseCase,
    private val getFavoriteClinicsUseCase: GetFavoriteClinicsUseCase
):BaseViewModel() {

    private val _getfavDoctor = MutableUIStateFlow<FavoriteDoctorUI>()
    val getfavDoctor = _getfavDoctor.asStateFlow()
    private val _getfavClinic= MutableUIStateFlow<FavoriteClinicUI>()
    val getfavClinic = _getfavClinic.asStateFlow()

    fun getFavoriteDoctors() = getFavoriteDoctorsUseCase().collectPagingRequest {
        it.toFavoriteDoctorUI()
    }

    fun getFavoriteClinics() = getFavoriteClinicsUseCase().collectPagingRequest {
        it.toFavoriteClinicUI()
    }


}