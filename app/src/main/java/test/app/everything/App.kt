package test.app.everything

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.ViewModelStore
import androidx.work.Configuration
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), ViewModelStoreOwner, Configuration.Provider {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltWorkerFactoryEntryPoint {
        fun workerFactory(): HiltWorkerFactory
    }


    override val viewModelStore: ViewModelStore
        get() = appViewModelStore


    override val workManagerConfiguration = Configuration.Builder()
        .setWorkerFactory(EntryPoints.get(this, HiltWorkerFactoryEntryPoint::class.java).workerFactory())
        .setMinimumLoggingLevel(android.util.Log.DEBUG)
        .build()

    companion object {
        private val appViewModelStore: ViewModelStore by lazy {
            ViewModelStore()
        }
    }
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}