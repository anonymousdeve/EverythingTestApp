package test.app.core.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import logW

abstract class BaseFragment<dataViewBinding : ViewDataBinding> : Fragment() {
    protected lateinit var binding: dataViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logW("onCreate->init ${javaClass.name}")
    }

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): dataViewBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = initBinding(inflater, container)
        binding.executePendingBindings()

        return binding.root
    }


}