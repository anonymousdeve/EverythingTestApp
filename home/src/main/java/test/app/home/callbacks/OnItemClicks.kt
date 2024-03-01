package test.app.home.callbacks

import test.app.home.model.dto.ArticleData

interface OnItemClicks {
    fun showItem(item: ArticleData)
}