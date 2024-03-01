package test.app.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import logW
import test.app.core.views.fragments.BaseFragment
import test.app.home.databinding.FragmentArticleBinding
import test.app.home.searchdialog.SearchDialog
import test.app.home.viewmodel.ArticleViewModel

@AndroidEntryPoint

class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArticleBinding.inflate(inflater, container, false)

    val viewModel by viewModels<ArticleViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executePendingBindings()
        binding.viewModel = viewModel
        if (viewModel.queryMap.isEmpty()) {
            SearchDialog.intDialog(requireActivity(), viewModel.queryMap) {
                viewModel.loadArticles(it)
                binding.title.text = it.filter { value ->
                    value.key in viewModel.listGeneralKeys && value.value.isNotEmpty()
                }.values.firstOrNull()
            }
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.search.setOnClickListener {
            SearchDialog.intDialog(requireActivity(), viewModel.queryMap) {
                viewModel.loadArticles(it)
            }
        }


        binding.data.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.data.layoutManager
                layoutManager?.let {
                    layoutManager as LinearLayoutManager
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && viewModel.canLoadMore() && !viewModel.isLoadingMore
                    ) {
                        viewModel.loadMore()
                    }

                }


            }
        })
    }
}