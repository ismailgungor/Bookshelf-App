package com.kekyazilim.bookshelf.util.categoryprocess

import android.content.Context
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class CategoryProcessModule {

    @Provides
    fun provideCategoryTypeConverter(): CategoryTypeConverter {
        return CategoryTypeConverter()
    }

    @Provides
    fun provideCategoryFileManager(context: Context): CategoryFileManager {
        return CategoryFileManager(context)
    }

    @Provides
    fun provideCategoryDataManagement(
        appRoomDatabase: AppRoomDatabase,
        categoryFileManager: CategoryFileManager,
        categoryTypeConverter: CategoryTypeConverter
    ): CategoryDataManagement {

        return CategoryDataManagement(
            appRoomDatabase.categoryDao(),
            categoryFileManager,
            categoryTypeConverter
        )

    }

}