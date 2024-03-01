package test.app.core.network

import androidx.annotation.Keep

@Keep
data class BaseErrorServerResponse(

    val statusCode: Int,
    val code: String,
    val status: String,
    val message: String
)
