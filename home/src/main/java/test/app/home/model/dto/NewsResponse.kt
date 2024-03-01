package test.app.home.model.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class NewsResponse(
    @SerializedName("articles")
    @Expose
    val articles: List<ArticleData>,
    @SerializedName("status")
    @Expose
    val status: String, // ok
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int // 13852
) {

}