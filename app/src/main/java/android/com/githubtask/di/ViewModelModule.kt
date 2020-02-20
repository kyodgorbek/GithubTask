package android.com.githubtask.di

import android.com.githubtask.ui.main.MainActivityViewModel
import android.com.githubtask.ui.main.MainActivityViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {

    companion object {
        val appModule = module {
            viewModel<MainActivityViewModel> {
                MainActivityViewModelImpl(
                    get()
                )
            }
        }
    }
}