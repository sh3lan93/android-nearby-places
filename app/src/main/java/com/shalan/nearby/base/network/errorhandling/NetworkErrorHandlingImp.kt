package com.shalan.nearby.base.network.errorhandling

import androidx.lifecycle.MutableLiveData
import com.shalan.nearby.base.states.IResult
import okhttp3.Headers
import org.koin.core.get
import org.koin.core.qualifier.named

class NetworkErrorHandlingImp<T>(
    result: MutableLiveData<IResult<T>>,
    loadMoreEvent: MutableLiveData<Boolean>? = null
) : DefaultErrorHandlingImp<T>(result, loadMoreEvent) {

    private val ioExceptionMessage: String = get(named("ioexception"))
    private val socketExceptionMessage: String = get(named("socketexception"))
    private val internalServerErrorExceptionMessage: String = get(named("internalserverexception"))


    override fun getIoExceptionMessage(): String = ioExceptionMessage

    override fun getSocketTimeExceptionMessage(): String = socketExceptionMessage

    override fun extractErrorMessagesIfAny(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleUnAuthorizationException(errorString: String?, headers: Headers?): String? {
        if (headers != null && headers.get(Constants.AUTHORIZATION_HEADER) != null) {
            TODO("Not yet implemented")
        } else {
            errorString?.let {
                return serializationService.deserialize(it, Error::class.java)?.message
            }
        }
        return null
    }

    override fun handleForbiddenStatus(errorBody: String?): String? = errorBody?.let {
        return serializationService.deserialize(it, Error::class.java)?.message
    }

    override fun handleInternalServerError(): String? = internalServerErrorExceptionMessage

    override fun handleNotFoundError(errorBody: String?): String? = errorBody?.let {
        serializationService.deserialize(it, Error::class.java)?.message
    }
}
