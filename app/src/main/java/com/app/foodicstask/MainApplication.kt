package com.app.foodicstask

import android.app.Application
import com.app.foodicstask.di.databaseModule
import com.app.foodicstask.di.networkModule
import com.app.foodicstask.di.repositoryModule
import com.app.foodicstask.di.useCaseModule
import com.app.foodicstask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
