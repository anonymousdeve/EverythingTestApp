package test.app.core.views.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class ItemDiffUtil<T> : DiffUtil.ItemCallback<T>()