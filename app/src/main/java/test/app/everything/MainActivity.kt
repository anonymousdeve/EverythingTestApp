package test.app.everything

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import test.app.core.views.activity.BaseActivity
import test.app.everything.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}