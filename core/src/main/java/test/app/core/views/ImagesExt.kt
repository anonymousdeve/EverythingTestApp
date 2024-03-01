package test.app.core.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import test.app.core.R

/*@BindingAdapter("bind:url")
fun ImageView.loadCircleImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}*/

@BindingAdapter("bind:loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.pleace_holder)
        .error(R.drawable.pleace_holder)
        .into(this)
}