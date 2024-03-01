package test.app.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import test.app.core.network.BaseErrorServerResponse
import test.app.core.network.BaseResponse
import test.app.network.di.BAD_INTERNET
import test.app.network.di.NO_INTERNET

import java.net.SocketTimeoutException


abstract class BaseRepository {


    private val defaultDispatcher = Dispatchers.IO
    protected suspend fun <T : Any> buildApi(task: suspend () -> T) = flow<BaseResponse<T>> {
        emit(BaseResponse.Success(data = task()))
    }
        .flowOn(defaultDispatcher)
        .onStart { emit(BaseResponse.Loading(loading = true)) }
        .catch { throwable ->
            emit(BaseResponse.Error(throwable = throwable, errorBody = getErrorBody(throwable)))
        }


    protected suspend fun buildLocal(task: suspend () -> Any?) = withContext(defaultDispatcher) {
        task()
    }


    private fun getErrorBody(throwable: Throwable): BaseErrorServerResponse = when (throwable) {
        is SocketTimeoutException -> BaseErrorServerResponse(
            statusCode = BAD_INTERNET,
            code = "",
            status = "",
            message = "",
        )

        is HttpException -> BaseErrorServerResponse(
            code = "",
            status = "error",
            statusCode = throwable.response()?.code()!!,
            message = throwable.response()?.errorBody()?.string() ?: throwable.message ?: ""
        )

        else -> BaseErrorServerResponse(
            NO_INTERNET, code = "",
            status = "",
            message = "",
        )
    }
}