package android.com.githubtask

import android.app.Application
import android.com.githubtask.di.NetworkModule
import android.com.githubtask.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            module {
                androidContext(this@App)

                modules(
                    listOf(
                        NetworkModule.networkModule,
                        ViewModelModule.appModule
                    )
                )
            }
        }
    }
}