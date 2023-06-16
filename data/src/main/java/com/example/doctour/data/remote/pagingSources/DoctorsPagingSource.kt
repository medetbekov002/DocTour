package com.example.doctour.data.remote.pagingSources

import com.example.doctour.data.base.BasePagingSource
import com.example.doctour.data.remote.apiservices.DoctourApiService
import com.example.doctour.data.remote.dtos.doctour.DoctourDto
import com.example.doctour.domain.model.Doctour

class DoctourPagingSource(
    private val service: DoctourApiService
) : BasePagingSource<DoctourDto, Doctour>(
    { service.fetchDoctourPaging(it)}
)
