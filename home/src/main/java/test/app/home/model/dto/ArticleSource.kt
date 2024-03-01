package test.app.home.model.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class ArticleSource(
    @SerializedName("id")
    @Expose
    val id: String?, // wired
    @SerializedName("name")
    @Expose
    val name: String // Wired
)