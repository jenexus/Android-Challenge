package android.challenge.module


import android.challenge.viewmodel.MainViewModel
import android.challenge.repository.LevelRepository
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Gson() }
    single { LevelRepository() }
    viewModel { MainViewModel(get(),get()) }
}