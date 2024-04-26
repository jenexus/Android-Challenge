package android.main


import android.app.Application
import android.challenge.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseActivity)
            modules(appModule)
        }
    }
}
