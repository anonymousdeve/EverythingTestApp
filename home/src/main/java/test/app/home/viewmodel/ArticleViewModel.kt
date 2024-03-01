package test.app.home.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import logE
import logW
import test.app.core.network.BaseResponse
import test.app.core.viewmodel.BaseViewModel
import test.app.home.callbacks.OnItemClicks
import test.app.home.model.repo.ArticleRepo
import test.app.home.view.ArticleAdapter
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(@ApplicationContext context: Context, private val repo: ArticleRepo) :
    BaseViewModel() {
    val articleAdapter = ArticleAdapter()
    val listGeneralKeys = listOf(
        "qInTitle",
        "q",
        "content",
        "description",
    )
    val isLoading = repo.articleResponse.map { it is BaseResponse.Loading }.asLiveData()
    val isEmptyData = MutableLiveData<Boolean>()
    private val articleResponse = repo.articleResponse.asSharedFlow()

    fun setAdapterItemClick(itemClicks: OnItemClicks) {
        articleAdapter.onItemClicks = itemClicks
    }

    private var totalPageCount = 0
    private var currentPage = 1

    init {
        articleResponse.onSuccessCollect {
            isLoadingMore = false
            val newData = articleAdapter.currentList.toMutableList()
            newData.addAll(it.articles)
            viewModelScope.launch(Dispatchers.Main) {
                articleAdapter.submitList(newData)
            }
            isEmptyData.postValue(articleAdapter.currentList.isEmpty())
            totalPageCount = it.totalResults / 20

        }
        onError = {
            isLoadingMore = false
            viewModelScope.launch(Dispatchers.Main) {
                if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                isEmptyData.postValue(articleAdapter.currentList.isEmpty())
            }
        }

    }

    fun canLoadMore() = currentPage <= totalPageCount

    var isLoadingMore = false

    fun loadArticles(
        map: Map<String, String>
    ) = launchTask {
        queryMap = map
        logW("Map$queryMap")
        repo.getArticles(map)
    }

    var queryMap = mapOf<String, String>()
    fun loadMore() {
        isLoadingMore = true
        val mapToUpdate = queryMap.toMutableMap()
        queryMap["page"]?.let {
            try {
                var page = it.toInt()
                page += 1
                currentPage = page
                mapToUpdate["page"] = "$page"
            } catch (e: Throwable) {
                logE(e.message.toString())
            }
        }
        loadArticles(mapToUpdate)
    }


}