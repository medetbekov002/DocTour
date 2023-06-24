package com.example.doctour.data.base

import android.util.Log
import android.webkit.MimeTypeMap
import com.example.doctour.data.BuildConfig
import com.example.doctour.data.utils.DataMapper
import com.example.doctour.data.utils.fromJson
import com.example.doctour.domain.core.Either
import com.example.doctour.domain.core.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.InterruptedIOException

abstract class BaseRepository {

    protected fun <T : DataMapper<S>, S> doNetworkRequestWithMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, S>> = doNetworkRequest(request) { body ->
        Either.Right(body.mapToDomain())
    }

    protected fun <T> doNetworkRequestWithoutMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, T>> = doNetworkRequest(request) { body ->
        Either.Right(body)
    }

    protected fun <T : DataMapper<S>, S> doNetworkRequestForList(
        request: suspend () -> Response<List<T>>
    ): Flow<Either<NetworkError, List<S>>> = doNetworkRequest(request) { body ->
        Either.Right(body.map { it.mapToDomain() })
    }

    protected fun <T> doNetworkRequestUnit(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, Unit>> = doNetworkRequest(request) {
        Either.Right(Unit)
    }

    private fun <T, S> doNetworkRequest(
        request: suspend () -> Response<T>,
        successful: (T) -> Either.Right<S>
    ) = flow {
        request().let {
            when {
                it.isSuccessful && it.body() != null -> {
                    emit(successful.invoke(it.body()!!))
                }

                !it.isSuccessful && it.code() == 422 -> {
                    emit(Either.Left(NetworkError.ApiInputs(it.errorBody().toApiError())))
                }

                else -> {
                    emit(Either.Left(NetworkError.Api(it.errorBody().toApiError())))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        when (exception) {
            is InterruptedIOException -> {
                emit(Either.Left(NetworkError.Timeout))
            }

            else -> {
                val message = exception.localizedMessage ?: "Error Occurred!"
                if (BuildConfig.DEBUG) Log.d(this@BaseRepository.javaClass.simpleName, message)
                emit(Either.Left(NetworkError.Unexpected(message)))
            }
        }
    }

    private inline fun <reified T> ResponseBody?.toApiError(): T? {
        return this?.string()?.let { fromJson<T>(it) }
    }

    protected inline fun <T : Response<S>, S> T.onSuccess(block: (S) -> Unit): T {
        this.body()?.let(block)
        return this
    }

    protected fun File.toMultipartBodyPart(
        formDataName: String
    ) = MultipartBody.Part.createFormData(
        name = formDataName,
        filename = name,
        body = asRequestBody(
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)?.toMediaTypeOrNull()
        )
    )

    protected fun <T : DataMapper<S>, S> doLocalRequest(
        request: () -> Flow<T>
    ): Flow<S> = request().map { data -> data.mapToDomain() }

    protected fun <T : DataMapper<S>, S> doLocalRequestForList(
        request: () -> Flow<List<T>>
    ): Flow<List<S>> = request().map { list -> list.map { data -> data.mapToDomain() } }

}
