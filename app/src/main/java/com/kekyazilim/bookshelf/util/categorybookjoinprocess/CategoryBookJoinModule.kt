package com.kekyazilim.bookshelf.util.categorybookjoinprocess

import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class CategoryBookJoinModule {

    @Provides
    fun provideCategoryBookJoinDataManagement(apiRoomDatabase: AppRoomDatabase): CategoryBookJoinDataManagement {
        return CategoryBookJoinDataManagement(apiRoomDatabase.categoryBookJoinDao())
    }

}