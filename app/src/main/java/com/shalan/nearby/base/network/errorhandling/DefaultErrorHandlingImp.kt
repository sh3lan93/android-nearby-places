package com.shalan.nearby.base.network.errorhandling

import androidx.lifecycle.MutableLiveData
import com.shalan.nearby.base.services.SerializationService
import com.shalan.nearby.base.states.IResult
import okhttp3.Headers
import org.koin.core.inject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import com.shalan.nearby.base.states.Result


abstract class DefaultErrorHandlingImp<T>(
    private val result: MutableLiveData<IResult<T>>,
    private val loadMoreEvent: MutableLiveData<Boolean>? = null
) : IErrorHandling {

    protected val serializationService by inject<SerializationService>()

    companion object {
        val TAG = DefaultErrorHandlingImp::class.java.simpleName
    }

    override fun accept(t: Throwable?) {
        t?.let {
            loadMoreEvent?.value = false
            when (it) {
                is HttpException -> handleHttpException(it)
                is IOException -> result.value = Result.error(getIoExceptionMessage())
                is SocketTimeoutException -> result.value =
                    Result.error(getSocketTimeExceptionMessage())
                else -> result.value = Result.error(it.localizedMessage)
            }
        }
    }

    private fun handleHttpException(exception: HttpException) {
        val message: String?
        when (exception.code()) {
            Constants.UNAUTHORIZED_CODE -> message = handleUnAuthorizationException(
                exception.response()?.errorBody()?.string(), exception.response()?.headers()
            )
            Constants.FORBIDDEN_CODE -> message =
                handleForbiddenStatus(exception.response()?.errorBody()?.string())
            Constants.INTERNAL_SERVER_ERRO_CODE -> message = handleInternalServerError()
            Constants.NOT_FOUND_CODE -> message =
                handleNotFoundError(exception.response()?.errorBody()?.string())
            else -> message = extractErrorMessagesIfAny(exception.response()?.errorBody()?.string())
        }
        result.value = Result.error(message)
    }

    abstract fun getIoExceptionMessage(): String

    abstract fun getSocketTimeExceptionMessage(): String

    abstract fun extractErrorMessagesIfAny(errorBody: String?): String?

    abstract fun handleUnAuthorizationException(errorString: String?, headers: Headers?): String?

    abstract fun handleForbiddenStatus(errorBody: String?): String?

    abstract fun handleInternalServerError(): String?

    abstract fun handleNotFoundError(errorBody: String?): String?

}
