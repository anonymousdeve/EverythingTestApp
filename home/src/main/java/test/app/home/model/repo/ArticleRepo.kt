package test.app.home.model.repo

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import test.app.core.network.BaseResponse
import test.app.home.model.service.ArticleApiService
import test.app.home.model.dto.NewsResponse
import test.app.network.BaseRepository
import javax.inject.Inject

class ArticleRepo @Inject constructor(private val apiService: ArticleApiService) : BaseRepository() {
    val articleResponse = MutableSharedFlow<BaseResponse<NewsResponse>>(replay = 0)

    /**
     * Suspended function to retrieve articles based on specified parameters.
     *
     * This function uses Kotlin Coroutines and Flow to asynchronously emit the result of the API call.
     * @return A Flow emitting the result of the API call as an ArticleResponse.
     *
     * @see kotlinx.coroutines.flow.Flow
     * @see ArticleResponse
     */
    suspend fun getArticles(
        mapOrQuery: Map<String, String>
    ) = articleResponse.emitAll(
        flow = buildApi {
            val map = mapOrQuery.toMutableMap()
            if (!mapOrQuery.containsKey("page")) {
                map["page"] = "1"
            }
            if (!mapOrQuery.containsKey("pageSize")){
                map["pageSize"] = "10"
            }
            apiService.getArticles(map)
        })
}