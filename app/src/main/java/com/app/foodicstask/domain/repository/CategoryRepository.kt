package com.app.foodicstask.domain.repository

import com.app.foodicstask.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}