package appssquare.projects.network.service
import androidx.annotation.Keep

@Keep
data class BaseErrorServerResponse(val statusCode:Int, val errorMessage:String)
