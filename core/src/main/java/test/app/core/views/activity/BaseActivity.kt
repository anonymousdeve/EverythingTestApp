package test.app.core.views.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import logW

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: viewBinding
    abstract fun initBinding(): viewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        logW("onCreate->init ${javaClass.name}")
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
    }
}