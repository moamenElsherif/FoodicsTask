package com.app.foodicstask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.foodicstask.data.local.CategoryEntity
import com.app.foodicstask.data.local.ProductEntity
import com.app.foodicstask.data.local.dao.CategoryDao
import com.app.foodicstask.data.local.dao.ProductDao

@Database(
    entities = [CategoryEntity::class, ProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}
