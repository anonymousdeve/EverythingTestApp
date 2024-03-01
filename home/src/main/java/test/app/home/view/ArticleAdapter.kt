package test.app.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import test.app.core.views.adapter.BaseRecyclerAdapter
import test.app.core.views.adapter.BaseViewHolder
import test.app.core.views.adapter.ItemDiffUtil
import test.app.home.callbacks.OnItemClicks
import test.app.home.databinding.ItemArticaleBinding
import test.app.home.model.dto.ArticleData
import test.app.home.viewmodel.SingleArticleViewModel

class ArticleAdapter : BaseRecyclerAdapter<ArticleData>(itemDiffUtil = object : ItemDiffUtil<ArticleData>() {
    override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem == newItem

    override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem.title == newItem.title
}) {
    override fun createBinding(parent: ViewGroup, viewType: Int) =
        ItemArticaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        val viewMode = SingleArticleViewModel(currentList[position], onItemClicks)
        val binding = holder.binding as ItemArticaleBinding
        binding.viewModel = viewMode
        binding.source.backgroundTintList = viewMode.loadRandomColor()
    }

    var onItemClicks: OnItemClicks? = null

}