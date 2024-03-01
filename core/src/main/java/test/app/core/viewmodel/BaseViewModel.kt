package test.app.core.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import logW
import test.app.core.R
import test.app.core.network.BaseErrorServerResponse
import test.app.core.network.BaseResponse

abstract class BaseViewModel : ViewModel() {
    protected fun launchTask(task: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) { task() }


    private fun onError(statusCode: Int, message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            /*
            * this code is yto handle status codes in error mode of server
            * */
            val newMessage = try {
                Gson().fromJson(message, BaseErrorServerResponse::class.java).message
            } catch (e: Throwable) {
                message
            }

            onError(message)
        }
    }

    var onError: (String) -> Unit = {}

    protected fun <T : Any> Flow<BaseResponse<T>>.onSuccessCollect(onSuccess: (T) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            this@onSuccessCollect.collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> Unit
                    is BaseResponse.Success -> onSuccess(response.data)
                    is BaseResponse.Error -> {
                        logW("Error When Code ${response.errorBody.statusCode} -> ${response.throwable.message}")
                        onError(
                            response.errorBody.statusCode,
                            response.errorBody.message
                        )
                    }

                    is BaseResponse.None -> Unit
                }
            }
        }
    }

}