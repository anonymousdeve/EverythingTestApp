package test.app.home.model.service

import retrofit2.http.GET
import retrofit2.http.QueryMap
import test.app.home.model.dto.NewsResponse

interface ArticleApiService  {

    @GET("everything")
    suspend fun getArticles(
        @QueryMap queryMap: Map<String, String?>
    ): NewsResponse


}