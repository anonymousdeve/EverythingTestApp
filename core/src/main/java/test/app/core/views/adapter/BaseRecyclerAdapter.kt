package test.app.core.views.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

abstract class BaseRecyclerAdapter<T>(itemDiffUtil: ItemDiffUtil<T>) :
    ListAdapter<T, BaseViewHolder<ViewDataBinding>>(itemDiffUtil) {
    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        bind(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent, viewType)

    private fun getViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    protected abstract fun bind(holder: BaseViewHolder<ViewDataBinding>, position: Int)
}