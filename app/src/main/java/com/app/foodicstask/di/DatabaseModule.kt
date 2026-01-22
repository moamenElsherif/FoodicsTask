package com.app.foodicstask.di

import androidx.room.Room
import com.app.foodicstask.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().productDao() }
}
