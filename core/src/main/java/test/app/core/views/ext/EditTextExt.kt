package test.app.core.views.ext

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.google.android.material.textfield.TextInputEditText


@BindingAdapter("bind:addTextListener")

fun EditText.addTextListener(
    observable: ObservableField<CharSequence>?
) {
    this.doAfterTextChanged {
        observable?.set(it)
    }
}

@BindingAdapter("bind:addTextListener")
fun TextInputEditText.addTextListener(
    observable: ObservableField<CharSequence>?
) {
    this.doAfterTextChanged {
        observable?.set(it)
    }
}