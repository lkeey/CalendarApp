package aleshka.developement.calendarapp.app

import aleshka.developement.calendarapp.di.viewModelsModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(org.koin.core.logger.Level.DEBUG)
            androidContext(this@App)
            modules(listOf(
                viewModelsModule
            ))
        }
    }
}