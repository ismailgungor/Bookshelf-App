package com.kekyazilim.bookshelf.databaseprocess.dao

import androidx.room.*
import com.kekyazilim.bookshelf.databaseprocess.DatabaseQueries
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query(DatabaseQueries.GET_ALL_CATEGORIES)
    fun getAllCategories(): Single<List<CategoryTable>>

    @Query(DatabaseQueries.GET_CATEGORY_COUNT)
    fun getCategoryCount(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryTable: CategoryTable): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categoryTableList: List<CategoryTable>)

    @Update
    fun update(categoryTable: CategoryTable)

    @Delete
    fun delete(categoryTable: CategoryTable)

    @Delete
    fun deleteAll(categoryTableList: List<CategoryTable>): Completable

    @Query(DatabaseQueries.DELETE_ALL_CATEGORIES)
    fun deleteAll()

}